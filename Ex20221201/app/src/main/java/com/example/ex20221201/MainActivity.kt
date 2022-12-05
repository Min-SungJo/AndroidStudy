package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    // 서버에 요청 보내는 객체
    lateinit var queue: RequestQueue

    // 받아오는 객체 (응답이 문자열 입니다.)
    lateinit var request: StringRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Volley : 서버 통신을 위한 라이브러리
        // Request : 내가 하고 싶은 요청이 담기는 곳 / 응답이 들어오는 곳
        // RequestQueue : Request 에 담긴 요청을 가지고 서버로 이동하는 객체
        val btnReq = findViewById<Button>(R.id.btnReq)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // request 요청 -> 요청을 queue 에 적용 -> queue 가 들고 서버로 이동
        // -> 응답을 가지고 queue 가 돌아옴 -> 응답을 request 전달 -> 처리

        // btnReq 를 클릭했을 때
        // etUrl 에 적힌 값을 가져와서 request 를 생성
        // request 를 queue 에 적용

        // btnReq 를 클릭했을 때
        // queue 초기화 진행
        queue = Volley.newRequestQueue(this@MainActivity)

        btnReq.setOnClickListener {
            // etUrl 에 적힌 값을 가져와서 request 를 생성
            val url = etUrl.text.toString()

            // request 를 queue 에 적용

            // 실제 요청을 할 수 있는 객체 : Request
            // StringRequest 의 매개변수 4가지
            // 1) 데이터 전송 방식 (GET/POST)
            // 2) 요청할 서버주소 (URL)
            // 3) 응답성공 했을 경우 실행 시킬 코드 suc Listener
            // 4) 응답실패 했을 경우 실행 시킬 코드
            // 3), 4) Listener

            request = StringRequest(
                Request.Method.GET,
                url,
                // 응답에 성공했을 때 어떻게 할껀지 Listener
                {response ->
                    Log.d("response", response.toString())
                    tvResult.text = response
                },
                // 응답에 오류발생할때 어떻게 할껀지 Listener
                {error->
                    Log.d("error", error.toString())
                    Toast.makeText(this,"error 발생",Toast.LENGTH_SHORT).show()
                }
                // RequestQueue가 받아온 응답은 response 랑 error 매개변수를
                // 통해 확인이 가능합니다.
            )
            //여러번 요청을 하는 구조를 가지고 있으면 캐시가 누적된다.
            // : 캐시 저장소를 비워주는 기능
            request.setShouldCache(false)

            queue.add(request)

        }

    }
}