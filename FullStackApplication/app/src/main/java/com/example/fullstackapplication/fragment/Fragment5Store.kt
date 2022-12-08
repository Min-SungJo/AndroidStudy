package com.example.fullstackapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.ContactAdapter
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class Fragment5Store : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_fragment5_store, container, false)

        // AdapterView 6단계
        // 1. Container 결정
        val rvContact = view.findViewById<RecyclerView>(R.id.rvContact)

        // 2. Template.xml 결정
        //  contact_list.xml

        // 3. Item 결정
        // ContactVO

        val contactList = ArrayList<ContactVO>()
        val db = Firebase.database // 생성자 없으면, 지금 연동된(Json file) db 로!


        // 4. Adapter 결정
        // requireContext() : Fragment 의 페이지정보 호출 시 사용
        val adapter = ContactAdapter(requireContext(), contactList)
        // 5. Container 에 Adapter 부착
        rvContact.adapter = adapter
        rvContact.layoutManager = LinearLayoutManager(requireContext())



        val contact2 = db.getReference("contact2") // 보통 key 랑 동일하게 작성하기도 한다
        contact2.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val contact = snapshot.getValue<ContactVO>() as ContactVO
                contactList.add(contact)
                // 추가가 완료된 이후
                // Adapter 새로고침
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return view
    }

}