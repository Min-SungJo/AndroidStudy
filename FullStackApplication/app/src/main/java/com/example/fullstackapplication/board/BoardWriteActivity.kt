package com.example.fullstackapplication.board

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.fullstackapplication.R

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val imgWrite = findViewById<ImageView>(R.id.imgWrite)

        val spf = getSharedPreferences("write",Context.MODE_PRIVATE)
        val editor =spf.edit()
        imgWrite.setOnClickListener{
            editor.putString("title",etTitle.text.toString()).commit()
            editor.putString("content",etContent.text.toString()).commit()
        }

    }
}