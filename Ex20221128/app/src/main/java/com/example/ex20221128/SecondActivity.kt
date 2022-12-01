package com.example.ex20221128

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnPre = findViewById<Button>(R.id.btnPre)
        val cl = findViewById<ConstraintLayout>(R.id.cl)
        // 제너릭 : 타입을 강하게 체크하는 기능


        // 1. intent 안에 color 키값을 통해 색깔을 꺼내자!
        // null 방지
        val color: String = intent.getStringExtra("color")!!
        Log.d("색깔잘넘어왔니",color)
        // 2. 배경색상을 color 로 변경하자
        //문자열 -> Color.parseColor
        cl.setBackgroundColor(Color.parseColor(color))

        //btnPre를 눌렀을 때 이전 페이지로 (FirstActivity 로 돌아간다-> finish method를 통해!)
        btnPre.setOnClickListener {
//            var intent = Intent(this,
//            FirstActivity::class.java)
//            startActivity(intent) // stack에 Activity를 넣는다
            finish() // stack 구조에서 out시키기
        }
    }
}