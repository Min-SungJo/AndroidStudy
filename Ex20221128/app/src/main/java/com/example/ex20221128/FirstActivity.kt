package com.example.ex20221128

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class FirstActivity : AppCompatActivity() {

    var color: String = "white"
    // 배경 색상을 저장 -> SecondActivity 로!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

//        val btnNext = findViewById<Button>(R.id.btnNext)
//        //btnNext 클릭 -> SecondActivity로 이동
//        btnNext.setOnClickListener {
//            // Activity 로 이동하는 Intent
//            // (시작Activity, 도착Activity)
//            // (this, java class)
//            // Kclass로 만들어진 Activity를 java class로 변환
//            // 액티비티명 ::class.java ->> java class로 변환됨
//            var intent = Intent(this,
//                SecondActivity::class.java
//            )
//            // 실행
//            startActivity(intent)
//        }

        val btnNext = findViewById<Button>(R.id.btnNext)
        val lv = findViewById<ListView>(R.id.lv)

        // 버튼 클릭을 감지하는 리스너를 장착
        // setOnClickListener -> 각 각 따로 따로 아이템을 클릭하는 것을 못함
        lv.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            //                  (p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
//            if(p2 == 0){
//                color = "purple"
//            }else if(p2 ==1){
//                color = "yellow"
//            }else if(p2 == 2){
//                color = "pink"
//            }
            if(p0 != null) { // null 체크
                color = p0!!.adapter.getItem(p2).toString()
            }
            Log.d("찍어보자",p2.toString())
            }
        })

        // inner class
        // 익명 클래스 anonymous class
        // 한 번 쓰이면 버려진다(마치 지역변수처럼)
//        btnNext.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?){ // view 를 다룰 때 사용 v
//                TODO()
//            }
//        })
//
//        btnNext.setOnClickListener { v-> // view 를 다룰 때 사용 v
//        }

        // Android 4대 구성요소
        // Activity 화면을 구성
        // Service (Background 에서 동작) Activity 에서 화면만 뺀 것
        // BR(Broadcast Receiver) 외부 상황을 감지 (-> 이어폰 연결)
        // CP(Content Provider) 다른 어플리케이션에서 정보를 받아오는 정보 제공자
        // 카톡에서 다른 사람에게 연락처를 전송하고자 할 때,
        // 연락처 어플리케이션에서 연락처 정보를
        // 카톡으로 넘김

        // 4대 구성요소 간 정보를 매개하는 객체
        // Intent
        
        // 명시적, 묵시적
        // explicit, implicit


        // btnNext를 누르면 SecondActivity로 color코드를 가지고 넘어간다
        btnNext.setOnClickListener {
            var intent = Intent(
                this@FirstActivity,
                SecondActivity::class.java
            )
            intent.putExtra("color",color) // 대문자 혹은 변수명
            // 단방향 호출 -> second 에서 정보를 가지고 돌아올 수 없기 때문
            startActivity(intent)
        }
    }

    // ctrl o 를 통해서 override 할 수 있음
    // 우클릭 -> generate -> override methods
    override fun onStart() {
        super.onStart()
        Log.d("생명주기", "onStart 입니다")
    }

    override fun onStop() {
        super.onStop()
        Log.d("생명주기", "onStop 입니다")
    }

    override fun onResume() {
        super.onResume()
        Log.d("생명주기", "onResume 입니다")
    }

    override fun onPause() {
        super.onPause()
        Log.d("생명주기", "onPause 입니다")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("생명주기", "onRestart 입니다")
    }
}