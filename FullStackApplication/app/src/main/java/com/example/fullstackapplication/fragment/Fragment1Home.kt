package com.example.fullstackapplication.fragment

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fullstackapplication.BuildConfig
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class Fragment1Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fragment1_home, container, false)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)
        val clHome = view.findViewById<ConstraintLayout>(R.id.clHome)

        val etName = view.findViewById<EditText>(R.id.etName)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        val etContactName = view.findViewById<EditText>(R.id.etContactName)
        val etContactAge = view.findViewById<EditText>(R.id.etContactAge)
        val etContactTel = view.findViewById<EditText>(R.id.etContactTel)
        val btnContact = view.findViewById<Button>(R.id.btnContact)


        val yehoUrl = "https://iotchat-188fe-default-rtdb.firebaseio.com/"

        val yehoDb = Firebase.database(yehoUrl)
        val msj = yehoDb.getReference("민성조")

        btnSend.setOnClickListener {
            val input = etName.text.toString()
            msj.setValue(input)
            Toast.makeText(context, "보내기 완료", Toast.LENGTH_SHORT).show()
        }


        // firebase 에서 데이터를 실시간으로 읽고 쓸 수 있는
        // RealTime DataBase
        // NoSQL 형식
        // - Key : Value

        // Write a message to the database
        // val database = Firebase.database // 연결된 firebase 계정의 RealTime DB 연결
        //val myRef = database.getReference("message")

        val url = "https://full-stack-6c7bf-default-rtdb.firebaseio.com/"
//        PackageManager.GET_META_DATA
        val db = Firebase.database(url)

        val contact = db.getReference("contact2")

        btnContact.setOnClickListener {
            val name = etContactName.text.toString()
            val age = etContactAge.text.toString().toInt()
            val tel = etContactTel.text.toString()

//            contact.setValue(ContactVO(name, age, tel))
            contact.push().setValue(ContactVO(name, age, tel))
            Toast.makeText(context, "추가완료", Toast.LENGTH_SHORT).show()
        }


        val color = db.getReference("color")

        color.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val bgColor = snapshot.getValue<String>()
                if (bgColor != null) {
                    try {
                        clHome.setBackgroundColor(Color.parseColor(bgColor))
                    } catch (e: IllegalArgumentException) { // 색 오타
                        clHome.setBackgroundColor(Color.parseColor("yellow"))
                    } catch (e: StringIndexOutOfBoundsException) { // 빈 문자열
                        clHome.setBackgroundColor(Color.parseColor("blue"))
                    }
                } else {
                    clHome.setBackgroundColor(Color.parseColor("white"))
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        val myRef = db.getReference("message")
        myRef.setValue("Hello, World!")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val message = snapshot.getValue<String>()
                tvMsg.setText(message)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

//        contact.addValueEventListener()
        contact.addChildEventListener(object : ChildEventListener {
            // 하위 값 중에 추가가 발생하면 동작
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val webContact = snapshot.getValue<ContactVO>()
                Log.d("연락처",webContact.toString())
            }
            // 하위 값 중에 변화가 발생하면 동작
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }
            // 하위 값 중에 삭제가 발생하면 동작
            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }
            // 하위 값 중에 이동이 발생하면 동작
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }
            // 하위 값 중에 취소가 발생하면 동작
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return view
    }

}