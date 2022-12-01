package com.example.ex20221130

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class PhoneAdapter(val context: Context, val layout: Int, val data: ArrayList<PhoneVO>) :
    BaseAdapter() {
    // 프로퍼티 : 필드
    // 멤버 : 메서드

    // Activity 의 힘(Context)을 빌려서 Inflate  할 수 있는 Inflater 를 가져오자
    /**하드웨어 센서나 Inflater 추출할 수 있는 메서드*/
    var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    // getSystemService는 하드웨어(휴대폰)에 담겨있는 센서들이나, Inflater를
    // 추출할 수 있는 메서드
    // 많은 센서들이 담겨있고, 각각의 리턴값을 설정해주기 힘듬
    // -> Any 타입으로 리턴, Inflater를 빼면 Inflater로 형변환

    override fun getCount(): Int {
        // ListView 항목의 개수를 돌려준다 (몇 칸?)
        return data.size
    }

    override fun getItem(p0: Int): Any {
        // p0 : position
        // position 에 위치만 data 를 반환 (ArrayList index 값)
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        // position 에 위치한 data 의 id 반환
        return p0.toLong()
    }

    // 중요
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        // 처음에 적재했을 때는 p1이 null 이다(imflate 작업을 한 적이 없음)

        // data 와 템플릿을 합친 뷰를 return
        // findViewById, setContentView : Activity 만 할 수 있다
        // 여기는 Class (설계도), 뷰를 찾아오는 메서드 사용이 불가능
        // Activity 의 힘을 빌릴 수 있다 --> inflate (코드를 작업할 수 있게 만드는 것)
        // Inflate : 코드로 있는 xml 파일을 눈에 보이는 뷰로 바꿔주는 동작(행동, 작업)
        // inflater : Inflate 를 할 수 있는 용도

        // p0 : 항목의 번호(position)
        // p1 : 이전에 만들어진 View 를 의미(xml 을 눈에 보이는 형태로 바꾼 것)
        // p2 : ListView(모든 뷰를 가지고 있는 View, 또는 항목을 담고 있는 View)

        //p1 : value 라서 아래에서 다른 값을 넣을 수 없음
        /**val(상수)형태로 사용되고 있는 p1을 사용하기 위한 var(변수)*/
        var view = p1

        if (view == null) {
            view = inflater?.inflate(layout, p2, false)

        }
        /** Code 로 존재하는 layout 을 눈에 보이는 View 객체로 변환!*/
        // layout, 이 템플릿을 포함할 곳, false(Custom 했기 때문에 속성을 일치시키면 View 가 이상해진다)
        val tvName = view?.findViewById<TextView>(R.id.tvName)
        val tvTel = view?.findViewById<TextView>(R.id.tvTel)
        val img = view?.findViewById<ImageView>(R.id.img)
        val btnCall = view?.findViewById<Button>(R.id.btnCall)

        // ArrayList --> data ---> (id, name, tel)
        tvName?.text = data[p0].name
        tvTel?.text = data[p0].tel
        img?.setImageResource(data[p0].imgId)
        btnCall?.setOnClickListener { 
            // 전화번호를 가져와서 ACTION_DIAL 실행
            // 액션, 데이터 (Uri tel:)
            val tel = Uri.parse("tel:${tvTel?.text.toString()}")
            val intent = Intent(Intent.ACTION_DIAL, tel)

            // Class 에서 startActivity 는 불가능하기 때문에
            // 새로운 Task(Stack)을 만들어서 실행
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // --> Activity 의 힘(Context)을 빌려서 Start
            context.startActivity(intent)
        }
        
        
        // inflate 가 된 view 를 리턴(데이터+템플릿)
        return view!!

    }
}