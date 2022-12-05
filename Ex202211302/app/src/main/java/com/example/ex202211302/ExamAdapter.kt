package com.example.ex202211302

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExamAdapter(val context: Context, val pokeList : ArrayList<PokeVO>)
    : RecyclerView.Adapter<ExamAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgPoke : ImageView
        val tvPokeLevel : TextView
        val tvPokeName : TextView
        val tvPokeType : TextView

        init {
            imgPoke = itemView.findViewById(R.id.imgPoke)
            tvPokeLevel = itemView.findViewById(R.id.tvPokeLevel)
            tvPokeName = itemView.findViewById(R.id.tvPokeName)
            tvPokeType = itemView.findViewById(R.id.tvPokeType)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.poke_list,null)
        return ViewHolder(view)
    }

    // holder (ViewHolder 가 가지고 있는 필드 id 값을 다 사용할 수 있는 매개변수)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgPoke.setImageResource(pokeList.get(position).img)
        holder.tvPokeLevel.setText(pokeList.get(position).level)
        holder.tvPokeName.setText(pokeList.get(position).name)
        holder.tvPokeType.setText(pokeList.get(position).type)
    }

    override fun getItemCount(): Int {
        return  pokeList.size
    }

}