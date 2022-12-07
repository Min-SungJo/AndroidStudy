package com.example.fullstackapplication.tip

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R
tg
class ListAdapter(val context: Context, val tipList: ArrayList<ListVO>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    // BaseAdapter --> 일반 ListView
    // RecyclerView -> RecyclerViewAdapter 상속

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val imgContent: ImageView
        val imgMark:ImageView
        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            imgContent = itemView.findViewById(R.id.imgContent)
            imgMark = itemView.findViewById(R.id.imgMark)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate : xml 코드를 눈에 보이는 View 객체로 바꿔서 ViewHolder 로 보내주는 역할
        val layoutInflater = LayoutInflater.from(context)
        // getSystemService <--- 하드웨어가 가지고 있는 많은 센서 서비스들이 담겨있음
        val view = layoutInflater.inflate(R.layout.list_template, null)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = tipList.get(position).title
//        holder.imgContent.setImageResource(tipList[position].imgId)
        Glide.with(context)
            .load(tipList[position].imgId)
            .into(holder.imgContent)

        // imgView 클릭
        // url 값을 가지고 WebViewActivity 로
        holder.imgContent.setOnClickListener {
//            val spf =context.getSharedPreferences("cook",Context.MODE_PRIVATE)
//            val editor = spf.edit()
//            editor.putString("url", tipList[position].url).commit()
            val intent = Intent(context,WebViewActivity::class.java)
            intent.putExtra("url", tipList[position].url)
            context.startActivity(intent)
        }

        holder.imgMark.setOnClickListener {
            holder.imgMark.setImageResource(R.drawable.bookmark_color)
        }

    }

    override fun getItemCount(): Int {
        return tipList.size
    }
}