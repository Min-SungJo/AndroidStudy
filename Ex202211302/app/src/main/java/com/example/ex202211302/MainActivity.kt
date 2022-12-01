package com.example.ex202211302

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // activity_main -> Kotlin 에서 접근 가능하게 해준다 (일종의 inflater)
        setContentView(R.layout.activity_main)

        // Adapter View
        // - ListView
        // 안쪽에 사용하고 있는 Adapter 에서
        // findViewById()가 많이 호출된다
        // -> 메모리 리소스를 많이 잡아먹는 함수 중 하나
        // 개발 -> 유지보수가 중요하다

        // RecyclerView
        
        // AdapterView 만드는 6단계
        // 1) Container 결정 -> activity_main.xml 에서 lv 생성
        val lvPoke = findViewById<ListView>(R.id.lvPoke) // lv 초기화

        // 2) Template 결정(item 디자인) -> layout 에 poke_list.xml 만들기
        // poke_list.xml

        // 3) Item 결정
        // PokeVo class 생성(Int, String, String, String) -> pokeList
        val pokeList = ArrayList<PokeVO>()
        pokeList.add(PokeVO(R.drawable.p1,"피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2,"꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3,"파이리", "불"))
        pokeList.add(PokeVO(R.drawable.p4,"이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5,"버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6,"구구", "비행"))

        // 4) Adapter 결정
         // ArrayAdapter : BaseAdapter
        // PokeAdapter 생성
        // ArrayAdapter(페이지정보, 템플릿, tvId, data)
        val adapter = PokeAdapter(this@MainActivity, pokeList)

        // 5) Container 에 adapter 부착
        lvPoke.adapter = adapter
        // 6) Event 처리
        
    }
}