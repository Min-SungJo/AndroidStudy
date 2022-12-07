package com.example.fullstackapplication.tip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fullstackapplication.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        // WebView 에 원하는 웹페이지 띄우기
        // 1. 주소준비
//        val spf = getSharedPreferences("cook",Context.MODE_PRIVATE)
        val address = intent.getStringExtra("url") // String? 타입임
//        val url =spf.getString("url","http://www.google.co.kr")

        // 2. 설정 변경(JavaScript 지원)
        val wv = findViewById<WebView>(R.id.wv)
        val ws =wv.settings
        ws.javaScriptEnabled = true
        // 3. WebView 에 Client 설정
        wv.webViewClient = WebViewClient()
        // 4. 주소적용
        // defalut 값이 있음 --> spf 시
        // intent 로 부터 값을 받아왔음 -> 값이 없으면 RecycleView 에서 안보임(실행불가)
        wv.loadUrl(address!!)

    }
}