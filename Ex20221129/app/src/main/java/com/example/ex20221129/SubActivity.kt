package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        // Main 에서 requestCode 와 함께 Intent 로 이동한 상태

        val etResult = findViewById<EditText>(R.id.etResult)
        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener{
            // 1. EditText 에 적힌 문구를 가져온다
            val str = etResult.text.toString()
            Log.d("sendStr", str)
            // 2. 문구를 변수에 저장
            // 3. intent 생성
            val intent = Intent()
            // 4. intent 에 데이터를 붙여준다 ->putExtra(key, value)
            intent.putExtra("content", str)
            // 5. setResult(resultCode, intent) 로 실행(StartActivity)
            setResult(RESULT_OK, intent)
            // resultCode : 결과값의 상태를 Main 에서 판단하기 위한 신호(코드)
            // 6. finish()
            finish()
        }
    }
}