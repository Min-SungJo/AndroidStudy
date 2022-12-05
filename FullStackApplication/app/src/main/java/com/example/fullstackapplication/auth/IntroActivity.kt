package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fullstackapplication.MainActivity
import com.example.fullstackapplication.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnIntroJoin = findViewById<Button>(R.id.btnIntroJoin)
        val btnIntroLogin = findViewById<Button>(R.id.btnIntroLogin)
        val btnIntroNo = findViewById<Button>(R.id.btnIntroNo)

        // LoginActivity 로 이동
        btnIntroLogin.setOnClickListener {
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // JoinActivity 로 이동
        btnIntroJoin.setOnClickListener {
            val intent = Intent(this@IntroActivity, JoinActivity::class.java)
            startActivity(intent)
        }

        // Firebase 에서 익명으로 접속할 수 있는 권한 받아와서
        // 성공하면
        // MainActivity 로 이동
        btnIntroNo.setOnClickListener {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}