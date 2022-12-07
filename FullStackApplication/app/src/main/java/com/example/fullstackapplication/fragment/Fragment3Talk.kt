package com.example.fullstackapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.board.BoardWriteActivity

class Fragment3Talk : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment3_talk, container, false)
        val btnWrite = view.findViewById<Button>(R.id.btnWrite)
        // btnWrite 를 클릭하면 BoardWriteActivity 로 이동
        btnWrite.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}