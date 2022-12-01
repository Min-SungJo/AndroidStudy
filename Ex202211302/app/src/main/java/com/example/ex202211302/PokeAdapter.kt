package com.example.ex202211302

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

// CustomAdapter
class PokeAdapter(val context: Context, val pokeList: ArrayList<PokeVO>) : BaseAdapter() {
    override fun getCount(): Int {
        //ItemView(항목 뷰)가 몇 번 만들어져야 하는지(포켓몬이 몇 마리인가?)
        return pokeList.size
    }

    override fun getItem(p0: Int): Any {
        //return pokeList.get(p0)
        return pokeList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    // 중요
    // p0: position
    // p1: itemView
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        // 0. xml -> Kotlin (xml을 Kotlin으로 변환 시켜주는 작업): inflater
        val layoutInflater = LayoutInflater.from(context)

        // 1. poke_list.xml -> code 로 접근할 수 있게 view 생성
        var view = layoutInflater.inflate(R.layout.poke_list, null)
        // inner Class 로 viewHolder 만들기
        var holder = ViewHolder()
        if (p1==null) { // itemView 가 만들어져 있지 않다면(null 이라면)

            Log.d("호출","1")

            // 항목 뷰(itemView)가 안 만들어졌을 때
            // 각 component 들을 초기화시켜주자
            holder.imgPoke = view.findViewById<ImageView>(R.id.imgPoke)
            holder.tvPokeLevel = view.findViewById<TextView>(R.id.tvPokeLevel)
            holder.tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)
            holder.tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)

            view.tag = holder // 객체가 있으면 객체에 tag 를 담을 수 있다 (모든 데이터 타입을 tag할 수 있다)

        } else{
            Log.d("호출","2")
            holder = p1.tag as ViewHolder // ViewHolder 라면 담아준다
            view = p1
        }
//        //초기화 -> 위에 itemView 만들어주었으니 필요없다
//        val imgPoke = view.findViewById<ImageView>(R.id.imgPoke)
//        val tvPokeLevel = view.findViewById<TextView>(R.id.tvPokeLevel)
//        val tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)
//        val tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)

        //set
        holder.imgPoke?.setImageResource(pokeList[p0].img)
        holder.tvPokeLevel?.setText(pokeList[p0].level)
        holder.tvPokeName?.setText(pokeList[p0].name)
        holder.tvPokeType?.setText(pokeList[p0].type)


        return view
    }

    // inner Class 만들기, 사용 이유
    // 1. 부모 클래스의 변수들을 다 사용할 수 있다
    // 2. 외부에서 이 inner Class 를 사용할 이유가 없을 때!
    // 예시 버튼.setOnClickListener(innerClass)

    // Design Pattern
    // EX) MVC

    // ViewHolder Pattern
    // getView 의 findViewById 로 접근한 정보들을
    // 저장하고 있는 class ViewHolder 를 만들어서
    // 메모리의 성능을 향상

    class ViewHolder() {
        //구별을 해야하니까 var로 만들겠습니다
        var imgPoke: ImageView? = null
        var tvPokeLevel: TextView? = null
        var tvPokeName: TextView? = null
        var tvPokeType: TextView? = null
    }

}