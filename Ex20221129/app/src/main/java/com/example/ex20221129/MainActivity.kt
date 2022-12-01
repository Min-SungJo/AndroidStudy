package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**1. MainActivity에서 버튼을 누르면 Sub한테 이동(요청)
         * StartActivityForResult(intent, requestCode)
         * 2. Sub에 버튼을 눌렀을 때 EditText에 담긴 닶을 들고 Main으로
         * 3. Main에서 값을 받는다
         * (OnActivityResult()오버라이딩 -> intent에서 데이터를 꺼내주는 메서드)
         * */

        // View id 값 찾아오기
        tvResult = findViewById<TextView>(R.id.tvResult)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // btnNext를 눌렀을 때
        btnNext.setOnClickListener {
            // intent 생성
            val intent = Intent(
                this@MainActivity, SubActivity::class.java
            )
            // intent 실행 (SubActivity 로 이동 및 요청)
//-            startActivityForResult(intent, 1004) // 메모리에 따라 데이터가 사라질 수도 있다
            // requestCode -> 결과 값을 보내줘야하는 Activity 를 구분하기 위해

            launcher.launch(intent)
            // 실행하려면 launch() 키워드가 붙어줘야 한다. 전달인자로 내가 생성한 intent 넣어주기

        }
    } // OnCreate 밖

    // Intent 를 통해서 받아온 결과 값을 처리할 수 있도록 도와주는 함수
//-    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        // 1) requestCode : (1004) 내가 보낸 요청 코드를 받아오는 매개변수
//        // 2) resultCode : (RESULT_OK) 받아온 결과값의 상태
//        // 3) Intent? data : str(문구)가 붙어있는 Intent 를 받아오는 매개변수
//
//        // 내가 보냈던 요청코드가 맞는지 판단
//        if (requestCode == 1004){
//            // resultCode 확인
//            if (resultCode == RESULT_OK){
//                // 둘 다 만족하면 값을 처리
//                // tvResult 의 text -> 받아온 str 로
//                // 1. str 꺼내기
//                // 2. TextView 내용을 str로 바꾸기
//                val str = data?.getStringExtra("content")
//                tvResult.text = str
//            }
//        }
//    }

    // callback 함수
    // 1. 다른 함수의 인자로 사용되느 ㄴ함수
    // 2. 어떤 이벤트에 의해 호출되어지는 함수
    // (예시 : 버튼 클릭하면 Intent 를 실행시키면서 호출)

    // ActivityResultLauncher
    // : 액티비티에서 데이터를 받아오기 위해 사용하는 함수
    // : Fragment(부분화면)에서도 데이터를 주고 받을 때 사용할 수 있음
    // 기존에 ActivityForResult 는 메모리가 너무 작음 -> Process, Activity 소멸 가능성 존재
    // Launcher -> 메모리 부족으로 소멸되어도 재생성 시 결과값을 받아온다

    val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // ActivityResultContracts 내부 함수
        // 1) createIntent --> StartActivityForResult 대체
        // 2) parseResult --> onActivityResult 대체 (resultCode, Intent 받아온다)
        // 런처를 사용하게 되면 요청을 보낸 Activity를 구분할 필요가 없다
        // requestCode 필요 X
        // it 에서 resultCode, data 추출
//        Log.d("data",it.data.toString())
//        Log.d("data",it.resultCode.toString())
        // ResultCode 가 RESULT_OK 인지 확인
        if (it.resultCode == RESULT_OK){
            var str = it.data?.getStringExtra("content").toString()
            tvResult.text = str
            Log.d("data", "받아온 값 : "+str)
        }
    }
}