package com.example.ex202211302

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        
        // RecyclerView 를 쓰는 이유

        // 1. ListView 의 개선판 : ViewHolder 를 강제함

        // 2. layoutManager : 유연하다 (수직 수평 그리드 형태로 쌓을 수 있다)
        // - Liner
        // - Grid
        // -StraggleGrid
        
        // AdapterView 6단계
        // 1) Container 결정 -> xml 에서 rv 생성 및 초기화
        val rvPoke = findViewById<RecyclerView>(R.id.rvPoke)
        // 2) Template 결정 -> poke_list.xml
        // 3) Item 결정
        val pokeList = ArrayList<PokeVO>()
        pokeList.add(PokeVO(R.drawable.p1,"피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2,"꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3,"파이리", "불"))
        pokeList.add(PokeVO(R.drawable.p4,"이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5,"버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6,"구구", "비행"))
        pokeList.add(PokeVO(R.drawable.p1,"피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2,"꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3,"파이리", "불"))
        pokeList.add(PokeVO(R.drawable.p4,"이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5,"버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6,"구구", "비행"))
        
        // 4) Adapter 결정
        val adapter = PokeAdapter2(this,pokeList)

        // 5) Container 에 Adapter 부착
        rvPoke.adapter = adapter
        // 5-2) 내가 구현하고자 하는 모양에 맞는
        // LayoutManager 를 결정!!(recycleView)
        // 수직으로 쌓는 형태
        // true -> 오른쪽부터 시작 (false가 기본)
//        rvPoke.layoutManager = LinearLayoutManager(this@MainActivity2, LinearLayoutManager.VERTICAL, false)
        //수평
//        rvPoke.layoutManager = LinearLayoutManager(this@MainActivity2, LinearLayoutManager.HORIZONTAL, false)
        // 격자 모양 (spanCount : 열)
//        rvPoke.layoutManager = GridLayoutManager(this,2)
        // S.Grid
        rvPoke.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)



    }
}