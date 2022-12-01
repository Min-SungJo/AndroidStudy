package com.example.ex20221129

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class BoardActivity : AppCompatActivity() {

    lateinit var tvContent: TextView
    lateinit var btnWrite: Button
    lateinit var btnBoard: Button

    var content: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        tvContent = findViewById<TextView>(R.id.tvContent)
        btnWrite = findViewById<Button>(R.id.btnWrite)
        val btnLogin1 = findViewById<Button>(R.id.btnLogin1)
        val lv = findViewById<ListView>(R.id.lv)
        val etBoard = findViewById<EditText>(R.id.etBoard)
        btnBoard = findViewById<Button>(R.id.btnBoard)
        btnBoard.isEnabled = false

        btnLogin1.setOnClickListener {
            val intent = Intent(
                this@BoardActivity,
                LoginActivity::class.java
            )
            launcher.launch(intent)
        }

        lv.setOnItemClickListener { adapterView, view, i, l ->
            //adapterView : ListView 에 대한 정보
            // view : ListView 가 있는 현재 페이지에 대한 정보
            // i (position) -> 내가 클릭한 item 위치(index 0~
            // l (id): long -> 내가 클릭한 item 고유 값
            if (i != null) {
                Toast.makeText(
                    this@BoardActivity, adapterView.adapter.getItem(i).toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // onCreate()안쪽에
        //Adapter View 를 사용하기 위한 6단계
        // 1. Container 결정
        // -> ListView 의 위치를 결정 (BoardActivity)
        
        // 2. Template 결정
        // -> 각 항목(Item)에 적용될 디자인을 결정!!
        // -> layout 패키지에 xml 형태로 생성
        // board_list.xml 최상위 레이아웃 : TextView
        // 이 때, id 는 tvBoard

        // 3. Item 결정
        val data = ArrayList<String>()
        //알아보기 쉽게 가상의 데이터 생성
        data.add("1. 아침")
        data.add("2. 점심")
        data.add("3. 저녁")
        // -> VO (ValueObject) 생성할 수 도 있다

        // 4. Adapter 결정
        // Adapter : 디자인(항목 뷰, 템플릿) + Item 결합해서
        // Adapter View 에 동적으로 뿌려주는 역할

        // ArrayAdapter 를 사용
        // 조건 2가지) 템플릿이 TextView, 아이템 요소가 String
        // 생성자 요소 4개
        // 1) 페이지 정보 (페이지 정보를 가지고 있는 context객체)
        // 2) 템플릿(항목 뷰),(아이템 디자인)
        // 3) 내가 적용할 TextView 의 id
        // 4) item (디자인과 연결할 아이템)
        val adapter = ArrayAdapter<String>(this@BoardActivity,R.layout.board_list,R.id.tvBoard,data)

        // 5. Container 에 Adapter 부착
        lv.adapter = adapter

        // 6. Event 처리

        // 1)btnBoard 클릭
        btnBoard.setOnClickListener {
        // 2) etBoard 값을 가져온다
        // 3) val input 에 저장
            val input = etBoard.text.toString()
        // 4) data 에 input 추가
            data.add(input)
        // 5) adapter 새로고침
            adapter.notifyDataSetChanged()
            etBoard.text = null
        }

        lv.setOnItemClickListener { adapterView, view, i, l ->

            // AlertDialog 옵션 정보를 담고 있는 builder 생성
            val builder = AlertDialog.Builder(this)
            // 삭제
            builder
                .setTitle("게시글 삭제")
                .setMessage("정말 삭제하시겠습니까?")
                .setPositiveButton("삭제",
                    DialogInterface.OnClickListener { p0, id ->
                        data.removeAt(i)
                        adapter.notifyDataSetChanged()
                    })
            // 취소
            builder
                .setNegativeButton("취소",null)
            // 주의
            // builder 를 통해 옵션을 달아준다
            // 맨 마지막에는 show()를 붙인다
                .show()

        }

        // AdapterView 만드는 법
        // 1. Container 결정 -> L:istView 위치 결정(내가 어디에 보여줄 것인지)
        // 2. Template 결정 -> 항목 뷰 디자인
        // 3. Item 결정 -> ArrayList<String>
        // 4. Adapter 결정 -> ArrayAdapter (조건 : Template 이 TextView, 아이템 요소가 String)
        // 5. Container 에 Adapter 부착
        // 6. Event 처리

    }

    //intent 데이터 받아주는 콜백함수 생성
    val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // it : resultCode, Intent
        // resultCode 상태 확인 ok/canceled
        Log.d("loginResult", "loginResultCode : " + it.resultCode.toString())
        val result: String
        if (it.resultCode == RESULT_OK) {
            //btnWrite.isEnable
            btnWrite.isEnabled = true
            btnBoard.isEnabled = true
            //tvContent 에 로그인 성공
            result = "로그인 성공"
        } else {
            //tvContent 에 로그인 실패
            result = "로그인 실패"
        }
        tvContent.text = result
    }
}