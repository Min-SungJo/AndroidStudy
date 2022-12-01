package com.example.ex202211302

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter2(val context: Context, val pokeList: ArrayList<PokeVO>) :
    RecyclerView.Adapter<PokeAdapter2.ViewHolder>() { // 바로밑에서 만든 ViewHolder

    // Java 에서는 Interface 형태로  OnClickEvent 를 구현한다
    // inner class 명시해야
    // outer class 필드의 member 접근 가능
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPoke: ImageButton
        val tvPokeLevel: TextView
        val tvPokeName: TextView
        val tvPokeType: TextView

        init {
//            imgPoke = itemView.findViewById(R.id.imgPoke) 회색이면 생략가능하다
            imgPoke = itemView.findViewById(R.id.imgPoke)
            tvPokeLevel = itemView.findViewById(R.id.tvPokeLevel)
            tvPokeName = itemView.findViewById(R.id.tvPokeName)
            tvPokeType = itemView.findViewById(R.id.tvPokeType)

            // 1. ListView의 setOnItemClickListener
            itemView.setOnClickListener{ //itemView : 내가 클릭한 아이템
                // 해당 아이템의 position 정보가 필요함
                // -> adapterPosition
                val position: Int = adapterPosition
                Log.d("포지션delete",position.toString())
                pokeList.removeAt(position)
                notifyDataSetChanged() // 이 Adapter 새로고침

            }
            
            imgPoke.setOnClickListener{
                // 피카츄 클릭 -> 피카츄입니다
                // (성공 -> Lv : 1 / Name / 타입 : type)
                val position: Int = adapterPosition
                Log.d("포지션img",position.toString())
                val level:String = pokeList.get(position).level
                val name:String = pokeList.get(position).name
                val type:String = pokeList.get(position).type

                val result: String = "Lv : $level / $name / 타입 : $type"


                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            }
        }

    }

    // ItemView 가 없을 때, ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.poke_list,null)
        return ViewHolder(view)
    }

    // 만들어진 ViewHolder 가 있다면, 꺼내서 쓰는 곳
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgPoke.setImageResource(pokeList.get(position).img)
        holder.tvPokeLevel.setText("Level : "+pokeList.get(position).level)
        holder.tvPokeName.setText(pokeList.get(position).name)
        holder.tvPokeType.setText("타입 : "+pokeList.get(position).type)
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }
}