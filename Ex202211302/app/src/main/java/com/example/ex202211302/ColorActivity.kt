package com.example.ex202211302

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        // 1. Container
        val rvColor = findViewById<RecyclerView>(R.id.rvColor)

        // 2.Template
        // color_list.xml

        // 3.Item
        val colorList = ArrayList<ColorVO>()
        var color: String
        // "#" + red(16) + green(16) + blue(16)
        for (i in 0..255 step 4) { // red
            var red: String = Integer.toHexString(i)

            for (j in 0 .. 255 step 4) { // green
                var green: String = Integer.toHexString(j)

                    var blue: String = Integer.toHexString(j)
//                for (k in 0 ..  255 step 4) { // blue
//                    var blue: String = Integer.toHexString(k)

                    if(red.length ==1){red = "0"+red}
                    if(green.length ==1){green = "0"+green}
                    if(blue.length ==1){blue = "0"+blue}

                    color = "#$red$green$blue"
                    colorList.add(ColorVO(color))
//                }
            }
        }

        // 4. Adapter
        val adapter = ColorAdapter(this, colorList)

        // 5. Container 에 Adapter 를 부착
        rvColor.adapter = adapter
//        rvColor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvColor.layoutManager = GridLayoutManager(this,40)

    }
}