package com.example.directapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var urlList = ArrayList<DirectVO>()
    lateinit var adapter: AddApapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. btnAdd(추가)를 누르면 AddActivity(Sub)로 이동 (EmptyActivity)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val lv = findViewById<ListView>(R.id.lv)


        // 단, 받아올 데이터 값이 있음 : 양방향 인텐트
        // 2. AddActivity 에서 btnSend(Button)을 누르면,
        // EditText 에 적힌 title, url 값을 가지고 MainActivity 로
        // (finish())

        // 3. AddActivity 에서 보낸 값(intenet 안에 들어있는)을 받아준다
        // 런처 사용
        
        // 4. ListView 만들기
        // 4-1. ListView 위치 정해주기
        // 4-2. ListView 한 칸에 들어갈 디자인 (템플릿 만들기 -> xml)
        // 4-3. AddActivity 에서 받아온 결과 값으로 ListView 에 들어갈 데이터
        // (title, url ---> 하나의 자료형으로 묶기 DirectVO)

        // 4-4. Adapter Custom
        // 4-5. Adapter ListView 에 적용
        btnAdd.setOnClickListener {
            val intent = Intent(
                this@MainActivity,AddActivity::class.java
            )
            launcher.launch(intent)
        }
        adapter = AddApapter(applicationContext, R.layout.direct_list, urlList)

        lv.adapter = adapter

    }

    //AddActivity 로 부터 받아온 데이터를 꺼내주자
    val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        //it : resultCode, intent(title, url)
        if (it.resultCode == RESULT_OK) {

            val title = it.data?.getStringExtra("title").toString()
            val url = it.data?.getStringExtra("url").toString()

            urlList.add(DirectVO(title, url))

            Log.d("content",title)
            Log.d("content",url)

            adapter.notifyDataSetChanged()

        }
    }

}