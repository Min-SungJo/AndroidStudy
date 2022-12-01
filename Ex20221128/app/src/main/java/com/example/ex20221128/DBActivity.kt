package com.example.ex20221128

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class DBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbactivity)

        val tvResult = findViewById<TextView>(R.id.tvResult)

        val loginId : String = intent.getStringExtra("loginId")!!
        val loginPw : String = intent.getStringExtra("loginPw")!!

        Log.d("로그인로그인",loginId+" : "+loginPw)
        if(loginId=="kyj"&&loginPw=="1234"){
            tvResult.text = "로그인 성공"
        }else{
            tvResult.text = "로그인 실패"
        }
    }
}