package com.example.wordle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answer = "apple"

        // AdapterView 사용 6단계
        // RecyclerView (ListView 는 권장X)
        // 1. Container 결정
        val rvGame = findViewById<RecyclerView>(R.id.rvGame)

        // 2. Template 결정
        // game_list.xml

        // 3. Item 결정
        val gameList = ArrayList<GameVO>()
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))

        // 4. Adapter 결정
        // GameAdapter
        val adapter = GameAdapter(this,gameList, answer)

        // 5. Container-Adapter 부착
        rvGame.adapter = adapter
        rvGame.layoutManager = LinearLayoutManager(this)
    }
}