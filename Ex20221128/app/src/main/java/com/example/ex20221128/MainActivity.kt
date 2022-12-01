package com.example.ex20221128

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val btnGoogle = findViewById<Button>(R.id.btnGoogle)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnSms = findViewById<Button>(R.id.btnSms)
        val btnPhoto = findViewById<Button>(R.id.btnPhoto)

        // 암묵적 intent
        // : 안드로이드 내부에 있는 어플리케이션을 실행 (권한이 필요)
        // Chrome, Camera, Message, Call

        // Intent 용도
        // 1-1 액션, 데이터
        // 1-2 액션 -> Camera
        // 2. Android 4대 구성 요소 간의 데이터 주고 받을 때

        btnCall.setOnClickListener {
            // btnCall을 누르면 전화가 간다
            // 데이터 : 전화번호
            // 데이터 가공 
            // -> URi : key,value 형태로
            // "tel:010-1234-5678" -> http: 예시
            var uri = Uri.parse("tel:010-1234-5678")
            // val intent = Intent(액션,데이터)
//            val intent = Intent(Intent.ACTION_DIAL,uri)
            val intent = Intent(Intent.ACTION_CALL, uri)
            // permission : 권한
            // 사용자에게 권한을 허용할 것인지 물어본다

            // ActivityCompat
            // checkSelfPermission() : 지금 현재 권한이 부여되어 있는지
            // (현재 페이지 정보, 어떤 권한인지)
            // 결과값으로 승인이 되어있는지, 안 되어 있는지 받아온다
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CALL_PHONE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                // 승인이 안 되어있는 상태라면 알림창을 띄워서 승인할 수 있도록
                // ActivityCompat에는 확인하는 기능과 요청하는 기능, 둘 다 들어있음
                ActivityCompat.requestPermissions(
                    this,
                    //여러 권한을 요청할 수 있기 때문에 arrayOf로!
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    // requestCode : 내가 뭘 요청한 건지 구분하기 위한 숫자
                    // (16진수 id 기입 불가)
                    0
                )
                // 생명주기를 안 벗어나게 label 로 관리
                return@setOnClickListener
            }
            // intent 실행
            startActivity(intent)
        }

        // btnWeb을 클릭하면 구글 홈페이지가 보이게 만들자
        btnWeb.setOnClickListener {
            // 데이터 : 구글 주소 (http://www.google.co.kr)
            // chrome app 에 이미 permission 이 있다
            var uri = Uri.parse("http://www.google.co.kr")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        
        // btnGoogle을 클릭하면 구글 맵을 보이게 만들어 주자
        btnGoogle.setOnClickListener { 
            // 액션, 데이터
            // 데이터 : 구글 맵은 get 방식
            // 구글 맵 주소/경도,위도
            var uri = Uri.parse("https://google.com/maps?q=35.14670147841655,126.92215633785938")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        // 클릭했을 때, 해당 키워드로 구글 검색
        btnSearch.setOnClickListener {
            // 1. 검색하는 intent를 하나 생성한다
//            var intent = Intent(액션)
            var intent = Intent(Intent.ACTION_WEB_SEARCH)
//            intent.putExtra(key,value)
            intent.putExtra(SearchManager.QUERY,"Connecting to the Emulator Err")
            // 2. 검색하고 싶은 키워드를 인텐트에 넣어준다
            // 3. intent 실행
            startActivity(intent)
        }

        // 문자보내기
        // 문자를 보내는 페이지로 이동한 다음
        // 내용을 꺼내올 예정
        btnSms.setOnClickListener {
            var intent = Intent(Intent.ACTION_SENDTO)
            // 문자 내용
            // "sms_body" 라는 key 값이 value 가 문자내용임을 구별한다
            intent.putExtra("sms_body","hello world")
            //대상에 대한 데이터 필요 (tel:)
            intent.data = Uri.parse("smsto:"+Uri.encode("010-1234-5678"))
            startActivity(intent)

        }

        // 사진찍기
        //MediaStore : Emulator 에서 동작 가능한 카메라, 저장소 제공
        btnPhoto.setOnClickListener { 
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
    }
}