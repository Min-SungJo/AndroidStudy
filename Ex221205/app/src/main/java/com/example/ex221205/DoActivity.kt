package com.example.ex221205

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
    lateinit var tvTime:TextView
    lateinit var tvScore:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        tvScore = findViewById<TextView>(R.id.tvScore)
        val btnStart = findViewById<Button>(R.id.btnStart)
        tvTime = findViewById<TextView>(R.id.tvTime)

        val imgViews = ArrayList<ImageView>()
        for (i in 1..9) {
            val resId = resources.getIdentifier("imageView$i","id",packageName)
            val imgView = findViewById<ImageView>(resId)
            imgViews.add(imgView)
            imgView.visibility = View.INVISIBLE // view 를 보여주는 속성 -> 잘 가져오면 안보이게 하기
        }
        btnStart.setOnClickListener {
            for (i in 0 until imgViews.size) {
                var imgView = imgViews.get(i)
                imgView.visibility = View.VISIBLE
                val thread = DoThread(imgView)
                thread.start()
            }
        }
    }



    // Handler
    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val imgView = msg.obj as ImageView
            imgView.setImageResource(R.drawable.on)
        }
    }

    // Thread class
    inner class DoThread(val imgView: ImageView) : Thread(){
        override fun run() {
            super.run()

            // 랜덤하게 0~ 5초 후에 일어나게
            val onTime = Random.nextLong(5000)
            Log.d("테스트",onTime.toString())

            Thread.sleep(onTime)
            val message = Message()
            message.obj = imgView
            handler.sendMessage(message)
        }
    }
}