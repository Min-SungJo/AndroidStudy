package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Fragment2 에서 ImageView 를 클릭했을 때 넘어와서
        // 구현돼야할 View 들
        
        // TextView
        // RecyclerView ---> Adapter, data(VO), template(xml)

        // Adapter ---> ListAdapter
        // data(VO) ---> ListVO
        // template ---> layout폴더에 list_template.xml

    }
}