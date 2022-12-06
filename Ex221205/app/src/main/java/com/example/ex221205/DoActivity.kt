package com.example.ex221205

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class DoActivity : AppCompatActivity() {
    var isPlaying : Boolean = true
    /**점수를 저장하는 변수*/
    var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val tvTime = findViewById<TextView>(R.id.tvTime)

        val imgViews = ArrayList<ImageView>()
        val tvScore = findViewById<TextView>(R.id.tvScore)

        for (i in 1..9) {
            val resId = resources.getIdentifier("imageView$i", "id", packageName)
            val imgView = findViewById<ImageView>(resId)
            imgViews.add(imgView)
            imgView.visibility = View.INVISIBLE // view 를 보여주는 속성 -> 잘 가져오면 안보이게 하기
        }
        btnStart.setOnClickListener {
            isPlaying = true
            val thread2 = TimeThread(tvTime)
            thread2.start()
            for (i in 0 until imgViews.size) {
                val imgView = imgViews.get(i)
                imgView.visibility = View.VISIBLE
                // 각 이미지 뷰의 tag 는 최초 0이다
                // 0은 두더지가 앉아 있는 상태로 정의
                imgView.tag = 0 // arg2를 사용하기 위해

                val thread = DoThread(imgView)
                thread.start()

                //img 눌렀을 때
                imgView.setOnClickListener {

                    if (imgView.tag == 1) {
                        score++
                    } else if (imgView.tag == 0&&score>0){
                        score--
                    }
                    tvScore.setText("현재 점수 : $score")
                }

            }
        }
    }

    // Handler
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            /**어떤 이미지뷰에 적용될 것인지?*/
            val imgView = msg.obj as ImageView

            /**on 이미지인지 off 이미지인지 이미지 정보를 담고 있는 리소스*/
            val img = msg.arg1

            /**현재 이미지의 상태, 1-> 일어남, 2-> 앉음*/
            val tag = msg.arg2
            imgView.setImageResource(img)
            imgView.tag = tag
        }
    }

    val handler2 = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1
            val tv = msg.obj as TextView
            tv.setText(time.toString())
        }
    }

    inner class TimeThread(val tv: TextView): Thread(){
        override fun run() {
            super.run()

            for(i in 30 downTo 0){
                val message = Message()
                message.arg1 = i
                message.obj = tv
                handler2.sendMessage(message)
                Thread.sleep(1000)
            }
            isPlaying = false
//            val intent= Intent(this@DoActivity,MainActivity::class.java)
//            startActivity(intent)
        }
    }

    // Thread class
    inner class DoThread(val imgView: ImageView) : Thread() {
        override fun run() {
            super.run()


            while (isPlaying) {
                var level = score*20
                if(score ==40){
                    level = 800
                }


                // 랜덤하게 0~ 5초 후에 일어나게
                val onTime = (1000..5000).random().toLong()
                Log.d("테스트", onTime.toString())

                Thread.sleep(onTime)
                val messageOn = Message()
                // R.drawble.on 은 Int 타입이기 때문에 arg1 으로 보내준다
                messageOn.arg1 = R.drawable.on
                messageOn.arg2 = 1 // 일어남
                messageOn.obj = imgView
                handler.sendMessage(messageOn)

//                val offTime = Random.nextLong(200..2500)
                val offTime = (1000-level).toLong()
                Log.d("테스트", offTime.toString())

                Thread.sleep(offTime)

                val messageOff = Message()
                messageOff.arg1 = R.drawable.off
                messageOff.arg2 = 0 // 앉음
                messageOff.obj = imgView
                handler.sendMessage(messageOff)
            }
        }
    }
}