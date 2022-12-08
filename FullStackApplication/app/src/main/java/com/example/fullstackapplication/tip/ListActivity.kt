package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {
    lateinit var adapter: ListAdapter // 전역변수로

    // 게시물의 uid 값이 들어갈 가변배열
    var keyData = ArrayList<String>()

    //bookmark 경로 설정을 위한 선언
    lateinit var bookmarkRef :DatabaseReference

    // bookmark 된 게시물의 정보가 들어갈 배열
    var bookmarkList = ArrayList<String>()

    var auth: FirebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // RealTime Database 에 필요한 객체 선언
        val database = Firebase.database
        // database 에 어떤 것을 참조할 것인지
        // Fragment2 에서 전체보기 클릭 -> 가져올 데이터 담기는 곳
        val allContent = database.getReference("content") // 전체보기에 해당하는 게시물들이 저장될 경로
        bookmarkRef = database.getReference("bookmarkList")

        // push() -> key 값으로 타임스탬프를 쩍어서 고유한 값을 만들어준다
//        allContent.push().setValue(
//            ListVO(
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png",
//                "미친아웃백투움바",
//                "https://philosopher-chan.tistory.com/1238"
//            )
//        )

        // Fragment2 에서 ImageView 를 클릭했을 때 넘어와서
        // 구현돼야할 View 들

        // TextView
        val tvCategory = findViewById<TextView>(R.id.tvCategory)
        // RecyclerView ---> Adapter, data(VO), template(xml)
        val rv = findViewById<RecyclerView>(R.id.rv)

        // Fragment2 에서 intent 를 통해 보낸 데이터를 꺼내주기
        val category = intent.getStringExtra("category")
        tvCategory.text = category


        // Adapter ---> ListAdapter
        // data(VO) ---> ListVO
        // template ---> layout폴더에 list_template.xml

        // 이미지의 id(Int), title(String) ---> VO 로
        val tipList = ArrayList<ListVO>()
        // 3개-4개 정도 임의로 만들어 놓기

        // FireBase 에서 데이터를 받아오는 Listener
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("snapshot", snapshot.toString())
                Log.d("snapshot2", snapshot.value.toString())
                for (model in snapshot.children) {
                    // model 에는 여러 게시물이 담겨있고
                    // 1개에 대한 게시물에 접근하기 위해 value 를 ListVO 로 받아온다
                    val item = model.getValue(ListVO::class.java) as ListVO
                    tipList.add(item)
                    keyData.add(model.key.toString())
                }
                // 데이터를 받아오는 속도가 adapter 가 실행되는 속도보다 느리기 때문에
                // 데이터를 받아온 직후 새로고침
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        allContent.addValueEventListener(postListener)

        getBookmarkData()

        // content -> category 구분하기 위한 값
        // uid -----> 게시물 구분할 수 있는 uid)
        //    imgId -> 게시물 데이터
        //    title
        //    url

/*        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png",
                "미친아웃백투움바",
                "https://philosopher-chan.tistory.com/1238"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
                "미친노른자장",
                "https://philosopher-chan.tistory.com/1236"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png",
                "미친참치맛나니",
                "https://philosopher-chan.tistory.com/1248"
            )
        )
        tipList.add(
            ListVO(
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png",
                "꿀호떡샌드위치",
                "https://philosopher-chan.tistory.com/1242"
            )
        )

        for (i in 0 until tipList.size) {
            allContent.push().setValue(
                tipList[i]
            )
        }*/

        // template.xml -> imageView 하나, TextView, 북마크 Image View

        // Adapter : RecyclerView

        // ViewHolder, OnCreateView, OnBindingView, getItemCount
        adapter = ListAdapter(this, tipList, keyData, bookmarkList)
        // ListActivity --> ListAdapter rv 에 적용
        rv.adapter = adapter

        // GridLayoutManager 사용 -> 두 줄로 쌓기
        rv.layoutManager = GridLayoutManager(this, 2)
        // 아래는 어플리케이션 전체 정보를 보내는 것이다
        // rv.layoutManager = GridLayoutManager(applicationContext, 2)
    }

    // bookmarkList 에 저장되어 있는 데이터를 가져오는 함수
    fun getBookmarkData(){
        val postListener2 = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarkList.clear() // ListActivity 를 다녀올 때마다 데이터가 누적되는 것을 방지
                for(model in snapshot.children){
                    Log.d("bookmark",model.toString())
                    Log.d("bookmark",model.key.toString())
                    Log.d("bookmark",model.value.toString())
                    //북마크 게시물의 uid 값을 bookmarkList에 저장
                    // 1. bookmarkList.add(model.value.toString())
                    bookmarkList.add(model.key.toString())
                }

                Log.d("dataSize", bookmarkList.size.toString())
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        // bookmarkList 경로에 있는 데이터를 snapshot으로 받아옴
//        bookmarkRef.addValueEventListener(postListener2) // snapshot 이 가져오는 정보를 변경시켜줄 수 있음
        bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2) // snapshot 이 가져오는 정보를 변경시켜줄 수 있음
        // 이를 통해 위에서 key 값만 받아와도 된다
    }

}