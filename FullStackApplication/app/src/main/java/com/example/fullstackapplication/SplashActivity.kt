package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.fullstackapplication.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // OnCreate 가 실행,
        // 3초 뒤 IntroActivity 로
        // Thread, Intent
        // 프로그램에는 MainThread 무조건 한 개
        // 내가 만든 작업처리는 SubThread
        // SubThread 가 MainThread 에 끼어들려면
        // Handler 가 필요하다(메시지 객체, Thread 실행시킬 수 있는 객체)

        // 1. Handler 를 가져온다
        // postDelayed({실행할 코드}, 지연시킬 시간)
        Handler().postDelayed({
            // Intent 생성
            val intent = Intent(this, IntroActivity::class.java)
            // Intent 실행
            startActivity(intent)
        }, 3000)


    }
}