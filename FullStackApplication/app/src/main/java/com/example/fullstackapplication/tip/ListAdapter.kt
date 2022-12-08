package com.example.fullstackapplication.tip

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListAdapter(
    val context: Context,
    val tipList: ArrayList<ListVO>,
    val keyData: ArrayList<String>,
    val bookmarkList: ArrayList<String>
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    val database = Firebase.database
    val auth: FirebaseAuth = Firebase.auth // 사용자 uid 값을 가져오기 위해 만든 객체

    // BaseAdapter --> 일반 ListView
    // RecyclerView -> RecyclerViewAdapter 상속

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val imgContent: ImageView
        val imgBookmark: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            imgContent = itemView.findViewById(R.id.imgContent)
            imgBookmark = itemView.findViewById(R.id.imgBookmark)

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

            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", tipList[position].url)
            context.startActivity(intent)
        }

        // 클릭했을 때 색깔을 바꾸면, 기존에 있던 북마크는 색이 안 칠해져 있음
        // -> adapter 가 실행될 때, 북마크로 있던 데이터들은 바로 색칠될 수 있게 만들기
        if(bookmarkList.contains(keyData[position])){
            holder.imgBookmark.setImageResource(R.drawable.bookmark_color)
        } else{
            holder.imgBookmark.setImageResource(R.drawable.bookmark_white)
        }

        // 북마크 모양의 이미지를 클릭햇을 때
        // 해당 게시물의 uid 값이 bookmarkList 경로로 들어가야함
        holder.imgBookmark.setOnClickListener {

            // Firebase 에 있는 bookmarkList 경로로 접근
            val bookmarkRef = database.getReference("bookmarkList")

            // 이미 저장되어 있는 게시물인지
            // bookmarkList 에 해당 게시물이 포함되어 있는지
            if (bookmarkList.contains(keyData[position])) {
                // 북마크를 취소
                // database 에서 해당 keyData 를 삭제
                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).removeValue()

                // imgBookmark 를 하얗게 만들자
//                holder.imgBookmark.setImageResource(R.drawable.bookmark_white)
                Toast.makeText(context, "북마크를 제거했습니다", Toast.LENGTH_SHORT).show()
            } else {
                // 북마크를 추가
                // database 에서 해당 keyData 를 추가
                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")
                // imgBookmark 를 까맣게 만들자
//                holder.imgBookmark.setImageResource(R.drawable.bookmark_color)
                Toast.makeText(context, "북마크를 추가했습니다", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return tipList.size
    }
}