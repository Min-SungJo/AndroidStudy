package com.example.fullstackapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.tip.BookmarkAdapter
import com.example.fullstackapplication.tip.ListVO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Fragment4Bookmark : Fragment() {

    // 함수를 사용하기 위해 전역변수로 만든다

    // Firebase
    val auth: FirebaseAuth = Firebase.auth
    val database = Firebase.database
    val contentRef = database.getReference("content")
    val bookmarkRef = database.getReference("bookmarkList")

    // ArrayList
    var data = ArrayList<ListVO>()
    var keyData = ArrayList<String>() // ListVO 를 포함하는 게시물의 uid
    var bookmarkList = ArrayList<String>() // 내가 선택한 게시물의 uid

    // Adapter 선언
    lateinit var adapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fragment4_bookmark, container, false)
        val rvBookmark = view.findViewById<RecyclerView>(R.id.rvBookmark)

        // 1. 전체 컨텐츠 데이터들을 다 가져오자
//        getContentData()
        // 2. 사용자가 북마크한 정보를 다 가져오자
        getBookmarkData() // bookList 를 모두 가져온 뒤쪽에 getContent 가 실행되고 있음
        // getContentData()가 하고 있는 일은 전체 데이터를 가져오는 것이 아니라
        // bookmarkList 에 있는 것만 가져오는 역할
        // 3. 전체 컨텐츠 중에서 사용자가 북마크한 정보만 recyclerView 에 반영
        adapter = BookmarkAdapter(requireActivity(), data, keyData, bookmarkList)
        // 4. adapter 적용
        rvBookmark.adapter = adapter
        rvBookmark.layoutManager = GridLayoutManager(requireContext(), 2)
        return view
    }

    // 전체보기에 있는 게시물 중, 북마크가 찍힌 것(VO)을 가져와야 한다
    // -> 전부 가져오는 것이 아니라 북마크가 찍혀있는 것만 if 로 가져온다
    fun getContentData() {
        // content 경로에 있는 데이터를 다 가지고 오자
        // uid --> keyData 에 담아준다
        //      - ListVO --> data 에 담아준다

        val posterListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (model in snapshot.children) {
                    val item = model.getValue(ListVO::class.java) as ListVO
                    // bookmarkList 에 값이 채워져 있어야 확인 가능
                    if (bookmarkList.contains(model.key.toString())) {
                        data.add(item)
                    }
                    keyData.add(model.key.toString())
                }
                adapter.notifyDataSetChanged()
                // adapter 새로고침


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        contentRef.addValueEventListener(posterListener)

    }

    fun getBookmarkData() {
        // bookmarkList 경로에 있는 데이터를 다 가지고 오자
        // 게시물의 uid 값 --> bookmarkList 에 담아준다
        val postListener2 = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarkList.clear()
                for (model in snapshot.children) {
                    bookmarkList.add(model.key.toString())
                }
                // adapter 새로고침 하기
                adapter.notifyDataSetChanged()
                //bookmarkList 에 있는 데이터만 가져와서 data(ArrayList<VO>)에 담고있다
                getContentData()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)

    }
    // 반복되는 코드들이 ---> Class 함수로 사용

}