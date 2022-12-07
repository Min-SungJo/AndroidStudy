package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Fragment2 에서 ImageView 를 클릭했을 때 넘어와서
        // 구현돼야할 View 들

        // TextView
        val tvCategory = findViewById<TextView>(R.id.tvCategory)
        // RecyclerView ---> Adapter, data(VO), template(xml)
        val rv = findViewById<RecyclerView>(R.id.rv)

        // Fragment2 에서 intent 를 통해 보낸 데이터를 꺼내주기
        val category = intent.getStringExtra("category")
        tvCategory.text = category


        // Adapter ---> ListAdapter
        // data(VO) ---> ListVO
        // template ---> layout폴더에 list_template.xml

        // 이미지의 id(Int), title(String) ---> VO 로
        val tipList = ArrayList<ListVO>()
        // 3개-4개 정도 임의로 만들어 놓기
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png",
                "미친아웃백투움바",
                "https://philosopher-chan.tistory.com/1238"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
                "미친노른자장",
                "https://philosopher-chan.tistory.com/1236"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png",
                "미친참치맛나니",
                "https://philosopher-chan.tistory.com/1248"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png",
                "꿀호떡샌드위치",
                "https://philosopher-chan.tistory.com/1242"
            )
        )

        // template.xml -> imageView 하나, TextView, 북마크 Image View

        // Adapter : RecyclerView
        val adapter = ListAdapter(this, tipList)
        // ViewHolder, OnCreateView, OnBindingView, getItemCount

        // ListActivity --> ListAdapter rv 에 적용
        rv.adapter = adapter

        // GridLayoutManager 사용 -> 두 줄로 쌓기
        rv.layoutManager = GridLayoutManager(this, 2)
        // 아래는 어플리케이션 전체 정보를 보내는 것이다
        // rv.layoutManager = GridLayoutManager(applicationContext, 2)
    }
}