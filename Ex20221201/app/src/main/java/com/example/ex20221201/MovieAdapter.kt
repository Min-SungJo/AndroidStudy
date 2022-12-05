package com.example.ex20221201

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val context: Context, val movieList: ArrayList<MovieVO>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRank: TextView
        val tvRankOldAndNew: TextView
        val tvMovieNm: TextView
        val tvAudiAcc: TextView
        val tvOpenDt: TextView

        init {
            tvRank = itemView.findViewById(R.id.tvRank)
            tvRankOldAndNew = itemView.findViewById(R.id.tvRankOldAndNew)
            tvMovieNm = itemView.findViewById(R.id.tvMovieNm)
            tvAudiAcc = itemView.findViewById(R.id.tvAudiAcc)
            tvOpenDt = itemView.findViewById(R.id.tvOpenDt)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.movie_list, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvRank.setText(movieList.get(position).rank)
        holder.tvRankOldAndNew.setText(movieList.get(position).rankOldAndNew)
        holder.tvMovieNm.setText(movieList.get(position).movieNm)
        holder.tvAudiAcc.setText(movieList.get(position).audiAcc)
        holder.tvOpenDt.setText(movieList.get(position).openDt)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}