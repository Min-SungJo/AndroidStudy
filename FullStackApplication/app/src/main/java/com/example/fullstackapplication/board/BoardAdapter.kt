package com.example.fullstackapplication.board

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R

// Fragment3에 있는 rvBoard 에 적용될 Adapter
class BoardAdapter(val context: Context, var boardList: ArrayList<BoardVO>) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    // 리스너 커스텀
    interface OnItemClickListener {
        // 내가 클릭한 RV의 view, position
        fun onItemClick(view: View, posotion: Int)

    }

    // 객체 저장 변수 선언
    lateinit var mOnItemClickListener: OnItemClickListener

    // 객체 전달 메서드 -> fragment3 에서 사용
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRvTitle: TextView
        val tvRvContent: TextView
        val tvRvTime: TextView

        init {
            tvRvTitle = itemView.findViewById(R.id.tvRvTitle)
            tvRvContent = itemView.findViewById(R.id.tvRvContent)
            tvRvTime = itemView.findViewById(R.id.tvRvTime)

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    // position 은 index 값을 반환한다 -> 이를 체크
                    mOnItemClickListener.onItemClick(itemView,position)
                }
            }
        }
    }

    //여기서 만든 xml 코드를 위의 ViewHolder 에서 받아준다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.board_template, null)
        return ViewHolder(view) //findById 작업을 해줘야하는 ViewHolder에 돌려준다
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvRvTitle.text = boardList[position].title
        holder.tvRvContent.text = boardList[position].content
        holder.tvRvTime.text = boardList[position].time
    }

    override fun getItemCount(): Int {
        return boardList.size // 항목의 개수
    }
}