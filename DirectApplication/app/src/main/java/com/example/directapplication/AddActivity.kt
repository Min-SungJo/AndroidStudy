package com.example.directapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {
            //EditText 내용 가져온다
            val title = etTitle.text.toString()
            val url = etUrl.text.toString()
            // Intent 생성
             val intent = Intent() // 데이터 전달 객체용으로 생성
            // Intent에 내용을 달아주자 (key, value)
            intent.putExtra("title", title)
            intent.putExtra("url", url)
            //실행
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}