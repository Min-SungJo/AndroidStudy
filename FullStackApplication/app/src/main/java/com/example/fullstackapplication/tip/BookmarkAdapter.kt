package com.example.fullstackapplication.tip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R

class BookmarkAdapter(
    val context: Context,
    val tipList: ArrayList<ListVO>,
    val keyData: ArrayList<String>,
    val bookmarkList: ArrayList<String>
) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgContent: ImageView
        val tvTitle: TextView
        val imgMark: ImageView

        init {
            imgContent = itemView.findViewById(R.id.imgContent)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            imgMark = itemView.findViewById(R.id.imgBookmark)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapter.ViewHolder {
        // list_template.xml 을 눈에 보이는 View ㄱ객체로 바꾼다
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_template, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 북마크데이터에 포함되어 있는지를 판단해서
        // view + data 작업 진행
//        if (bookmarkList.contains(keyData[position])){
            holder.tvTitle.text = tipList[position].title
            Glide.with(context)
                .load(tipList[position].imgId)
                .into(holder.imgContent)
            // Glide: 웹에 있는이미지를 가져와서 세팅
            // imgId ---> 이미지 주소값
//        }
//        if(bookmarkList.contains(keyData[position])){
            holder.imgMark.setImageResource(R.drawable.bookmark_color)
//        }
    }

    override fun getItemCount(): Int {
        return tipList.size
    }
}