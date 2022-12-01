package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etId = findViewById<EditText>(R.id.etId)
        val etPw = findViewById<EditText>(R.id.etPw)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            //EditText에 있는 내용을 ㄱ가져온다
            val id = etId.text.toString()
            val pw = etPw.text.toString()
            Log.d("login","id: "+id)
            Log.d("login","pw: "+pw)
            /**로그인 ResultCode*/
            val loginResult =
                if(id=="smhrd"&&pw=="1234")
                    RESULT_OK
                else
                    RESULT_CANCELED
            Log.d("loginCode",loginResult.toString())
            val intent = Intent()
//            intent.putExtra("login", loginResult)
            setResult(loginResult, intent)
            finish()
        }

    }
}