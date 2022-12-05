package com.example.ex202211302

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class ExamActivity : AppCompatActivity() {
    lateinit var clExam:ConstraintLayout
    override fun onRestart() {
        super.onRestart()
        val sharedPreferences = getSharedPreferences("sp1", Context.MODE_PRIVATE)
        val color = sharedPreferences.getString("bgColor","white")
        clExam.setBackgroundColor(Color.parseColor(color))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

        // 로그인 기능!!
        // 자동 로그인
        // Application 종료해도
        // 정보가 저장될 필요성 있다
        // ->DataBase
        // -> RDB (Relational DataBase)
        // - RDBMS
        //  -> Oracle
        //  -> MySQL
        //  -> MariaDB
        // 특징 -> SQL 언어를 사용
        // NoSQL -> (Key, Value)

        // SQLite -> 실제 DB 안드로이드에 내장하는 형태
        // SharedPreference -> 환경설정 정보들을 공유하기 위해 (NoSQL)
        // NoSQL -> (Key, Value)
        // 버튼 -> 그리드 -> 리니어 -> 그리드

        val btnRed = findViewById<Button>(R.id.btnRed)
        val btnPink = findViewById<Button>(R.id.btnPink)
        val btnBlack = findViewById<Button>(R.id.btnBlack)
        val btnOther = findViewById<Button>(R.id.btnOther)



        clExam = findViewById<ConstraintLayout>(R.id.clExam)

        var color:String="white"

        // tvResult : 변수  var
        // PI = 3.141592 : 상수 val (전부 대문자)
        val sharedPreferences = getSharedPreferences("sp1", Context.MODE_PRIVATE)
        /** 환경설정을 저장하기 위해 만든 변수*/
        val editor = sharedPreferences.edit()
        // MODE_PRIVATE : 생성한 application 내에서만 공유 가능
        // MODE_WORLD_READABLE : 다른 application 에서 읽을 수 있다
        // MODE_WORLD_WRITEABLE : 다른 application 에서 읽고 쓸 수 있음

        val bgColor: String? =
            sharedPreferences.getString("bgColor", "white") // as String -> ? 와 같은 의미
        // null 방지하여 초기값을 white 로
        clExam.setBackgroundColor(Color.parseColor(bgColor))

        btnRed.setOnClickListener {
//            /** 환경설정을 저장하기 위해 만든 변수*/
//            val editor = sharedPreferences.edit()
            color = "#FF0000"
            // 위에 설정한 key값과 같아야 한다
            editor.putString("bgColor",color)
            // RGB (#RR GG BB) 0~255까지의 숫자를 16진수로 표현
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnPink.setOnClickListener {
            color = "#E91E63"
            editor.putString("bgColor",color)
            editor.commit()
            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnBlack.setOnClickListener {
            color = "#000000"
            editor.putString("bgColor",color)
            editor.commit()
            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnOther.setOnClickListener {
            val intent = Intent(this@ExamActivity,ColorActivity::class.java)
            startActivity(intent)
        }

    }

}