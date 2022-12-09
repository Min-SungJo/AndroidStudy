package com.example.fullstackapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.board.BoardAdapter
import com.example.fullstackapplication.board.BoardInsideActivity
import com.example.fullstackapplication.board.BoardVO
import com.example.fullstackapplication.board.BoardWriteActivity
import com.example.fullstackapplication.utils.FBdatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Fragment3Talk : Fragment() {

    // getBoardData 를 통해 받아온 item(BoardVO)을 관리하는 배열
    var boardList = ArrayList<BoardVO>()
    lateinit var adapter: BoardAdapter

    var keyData = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment3_talk, container, false)
        val rvBoard = view.findViewById<RecyclerView>(R.id.rvBoard)
        val btnWrite = view.findViewById<Button>(R.id.btnWrite)
        // btnWrite 를 클릭하면 BoardWriteActivity 로 이동
        btnWrite.setOnClickListener {
            val intent = Intent(requireContext(), BoardWriteActivity::class.java)
//            val intent = Intent(requireActivity(), BoardWriteActivity::class.java)
//            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        // 1. 한칸에 들어갈 디자인 만들기(board_template.xml)
        // 2. Adapter 에 보낼 데이터 가져오기 -> 함수로!
        //    Firebase 의 board 경로에 있는 데이터를 가져오기
        getBoardData()
        // 3. Adapter 만들기( data )
        adapter = BoardAdapter(requireContext(), boardList)

        // BoardAdapter 에서 커스텀한 클릭 이벤트를 호출
        adapter.setOnItemClickListener(object :BoardAdapter.OnItemClickListener{
            override fun onItemClick(view: View, posotion: Int) {
                // 복잡하니 흐름을 잘 잡자
                // BoardInsideActivity 로 넘어가자
                val intent = Intent(requireContext(),
                BoardInsideActivity::class.java)
                intent.putExtra("title",boardList[posotion].title)
                intent.putExtra("time",boardList[posotion].time)
                intent.putExtra("content",boardList[posotion].content)

                // 이름이 될 key 값
                intent.putExtra("key",keyData[posotion])


                startActivity(intent)


            }

        })

        // 4. rvBoard 에 adapter 적용
        rvBoard.adapter=adapter
        rvBoard.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    /**board 에 있는 데이터 전부를 가져오는 작업을 할 것*/
    fun getBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                boardList.clear()
                // Firebase 에서 snapshot 으로 데이터를 받아온 경우 실행되는 메서드
                // 게시물의 uid
                //      - BoardVO
                for (model in snapshot.children) { // snapshot 이 가져온 children 에 넣어줄 것
                    val item = model.getValue(BoardVO::class.java) as BoardVO
                    // 여기서 ArrayList 를 선언하면 다른 곳에서 사용불가 -> onCreateView 밖에 선언!(전역변수)
                    boardList.add(item)

                    //img 가 key 값으로 이름이 설정되어 있음
                    keyData.add(model.key.toString())

                }
                //adapter 를 새로고침
                boardList.reverse()
                keyData.reverse()
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // 오류가 발생했을 경우 실행되는 함수
            }

        }
        // board 에 있는 모든 데이터가 들어간다
        FBdatabase.getBoardRef().addValueEventListener(postListener)
    }

}