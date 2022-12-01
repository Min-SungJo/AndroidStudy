package com.example.ex20221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var phoneList = ArrayList<PhoneVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 화면에서 ListView 의 위치 정해주기 (xml)
        // 2. ListView 한 칸에 들어갈 디자인 뷰 정하기 (xml)
        // 3. ListView 에 들어갈 data 만들기 (PhoneVO)
        // 이미지 뷰에 들어갈 img의 id 값 (Int)
        // 이름, 전화번호 (String)

        val p1 = PhoneVO(R.drawable.img1, "조자연", "010-1234-5678")
        val p2 = PhoneVO(R.drawable.img2, "강예진", "010-1234-5678")
        val p3 = PhoneVO(R.drawable.img3, "나예호", "010-1234-5678")
        val p4 = PhoneVO(R.drawable.img4, "채수민", "010-1234-5678")
        val p5 = PhoneVO(R.drawable.img5, "선영표", "010-1234-5678")
        val lv = findViewById<ListView>(R.id.lv)

        phoneList.add(p1) // 이렇게 따로 할당 X, VO로 생성할 때, 바로 값을 넣음
        phoneList.add(p2)
        phoneList.add(p3)
        phoneList.add(p4)
        phoneList.add(p5)

        // 4. Adapter 만들기**(중요) -(data와 템플릿을 함쳐줌)
        // 데이터의 자료형이 내가 만든 자료형(VO) 이기 때문에
        // 안드로이드에서 기본적으로 제공하는 ArrayAdapter 는 사용 불가

        /**data 와 템플릿을 합쳐서 ListView 에 적재*/
        val adapter = PhoneAdapter(applicationContext, R.layout.phone_list, phoneList)

        // 5. ListView 에 Adapter 적용
        lv.adapter = adapter
        // 6. 이벤트  달아주기
    }
}