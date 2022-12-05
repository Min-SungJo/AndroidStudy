package com.example.ex221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tvTimer: TextView
    lateinit var tvTimer2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer = findViewById<TextView>(R.id.tvTimer)
        tvTimer2 = findViewById<TextView>(R.id.tvTimer2)

        val thread = TimerThread(tvTimer)
        thread.start() // 쓰레드 클래스 안의 run()을 실행함
        val thread2 = TimerThread(tvTimer2)
        thread2.start()

    }

    // Main Thread Queue(작업영역)에
    // 작업을 추가해주는 Handler 를 만들자
    // Looper.getMainLooper() -> Main 에 전달해준다는 의미
    // handleMessage() 오버라이딩!
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time: Int = msg.arg1
            val tv = msg.obj as TextView // Any -> TextView 로 다운 캐스팅
            tv.setText(time.toString())
            // 메인 UI 를 건드리는 작업
            // 직접적으로 view 정보를 수정하는 것이 아닌
            // 메인 작업 Queue 에 작업을 추가
        }
    }

    // 시간 관련 Thread class
    inner class TimerThread(val tv: TextView) : Thread() {
        // Thread 에는 
        // run() 메소드가 존재
        // : Thread 를 동작시키면 실행되는 메소드
        override fun run() {
            super.run()

            // 10 -> 0 (1초마다 1씩 감소)
            // sleep(밀리초 만큼)코드를 지연시킴 -> OnCreate 에서 하면 Main 자체가 멈추니 주의하도록

            for (i in 10 downTo 0) {
                Log.d("타이머", i.toString())
                //tvTimer.setText(i.toString())
                /**handler 에게 정보를 전달해주는 객체*/
                val message = Message()
                // member 변수(field)가 3개 존재
                // (arg1: int, arg2: int, obj: Any)
                message.arg1 = i
                message.obj = tv
                handler.sendMessage(message)
                val rdValue = (1..1000).random().toLong()
                Thread.sleep(rdValue) // Thread 생략해도 좋지만, 유지보수를 위해 쓰는 것이 나을 수 있다
//                Thread.sleep(1000) // Thread 생략해도 좋지만, 유지보수를 위해 쓰는 것이 나을 수 있다
            }
        }
    }
}