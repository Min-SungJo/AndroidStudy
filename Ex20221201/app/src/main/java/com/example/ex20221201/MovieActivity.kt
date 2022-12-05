package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {

    //Volley 에 필요한 객체 2가지
    /**요청을 가지고 서버로 이동하는 객체*/
    var queue: RequestQueue? = null
    /**요청/ 응답이 들어가는 객체*/
    lateinit var request: StringRequest

    /**영화  정보들이 담길 배열*/
    var movieList = ArrayList<MovieVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

//         Volley 를 통한 네트워크 통신 4단계
//         1. Volley 설정
//          - Volley 라이브러리 추가 (build.gradle)
//          - Manifest 에 Permission 추가 -> Internet
//         2. RequestQueue 생성
//         3. Request 생성
//         4. Request.Queue 에 Request 추가


        // 1) Container 결정
        val rvMovie = findViewById<RecyclerView>(R.id.rvMovie)
        val btnMovie = findViewById<Button>(R.id.btnMovie)
        val etInput = findViewById<EditText>(R.id.etInput)

        // 2) Template 결정
        // movie_list.xml

        // 3) Item 결정
        // movieList : ArrayList<MovieVO>
        
        // 4) Adapter 결정
        val adapter = MovieAdapter(this, movieList)
        // 5) Container 에 adapter 부착
        rvMovie.adapter = adapter
        rvMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // 버튼을 클릭하거나, 에뮬레이터 작동, onCreate 가 실행될 때마다
        // request 가 들어갈 장소를 만들고 있음
        // 따라서 queue 가 null 일 때만 만들라고 선언
        if (queue == null) {
            queue = Volley.newRequestQueue(this@MovieActivity)
        }

        // btnMovie 를 클릭했 때 영화정보를 response Log 로 확인
        btnMovie.setOnClickListener {
            movieList.clear() // 버튼을 눌렀을 때 리스트를 비워준다
            val date = etInput.text.toString()
            etInput.setText("20") // etInput 비워주기
            val url =
                "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=$date"
            request = StringRequest(
                // 1. get/post
                Request.Method.GET,
                // 2. url
                url,
                // 3. 응답 성공 시 Listener
                { response -> // response 를 안 쓰면 it 으로 사용된다(default)
                    /**응답받아온 response : String*/
                    val json1 = JSONObject(response)
                    Log.d("json1", json1.toString())
                    val json2 = json1.getJSONObject("boxOfficeResult")
                    Log.d("json2", json2.toString())
                    val json3 = json2.getJSONArray("dailyBoxOfficeList")
                    Log.d("json3", json3.toString())

//                    val movie = json3.getJSONObject(0)
//                    val rank = movie.getString("rank")
//                    Log.d("jsonRank", rank)

                    // JsonArray 에 순차적으로 접근하여 영화정보 꺼내오기
                    for (i in 0 until json3.length()) {
                        val movie = json3.getJSONObject(i)
                        // rank
                        val rank = movie.getString("rank")
                        // rankOldAndNew
                        val rankOldAndNew = movie.getString("rankOldAndNew")
                        // movieNm
                        val movieNm = movie.getString("movieNm")
                        Log.d("json영화", movieNm)
                        // openDt
                        val openDt = movie.getString("openDt")
                        // audiAcc
                        val audiAcc = movie.getString("audiAcc")
                        //MovieVO로 만들고 movieList에 add
                        movieList.add(MovieVO(rank, rankOldAndNew, movieNm, audiAcc, openDt))
                    }

                    adapter.notifyDataSetChanged()
                },
                { error ->
                    Log.d("error", error.toString())
//                    Toast.makeText(this,"error 발생", Toast.LENGTH_SHORT).show()
                }
            )
            request.setShouldCache(false)
            queue?.add(request)
        }
    }
}