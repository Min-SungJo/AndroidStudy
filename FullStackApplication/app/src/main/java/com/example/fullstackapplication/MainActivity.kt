package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.fullstackapplication.auth.IntroActivity
import com.example.fullstackapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 어떤 item 을 클릭했는지에 따라서
        // FrameLayout 에 Fragment 를 갈아 끼운다

        auth = Firebase.auth

        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val fl = findViewById<FrameLayout>(R.id.fl)
        val imgLogout = findViewById<ImageView>(R.id.imgLogout)

        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            ContactFragment()
        ).commit()
//        supportFragmentManager.beginTransaction().replace(
//            R.id.fl,
//            Fragment1Home()
//        ).commit()

        imgLogout.setOnClickListener{
            auth.signOut()
            // 로그아웃 후, IntroActivity 로 이동
            val intent = Intent(this, IntroActivity::class.java)
            // 이전에 쌓여있던 Activity 를 모두 날리기
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // auth 에 담겨있는 기능
        // createUsersWithEmailAndPassword : 회원가입(email, pw)
        // SignInWithEmailAndPassword : 로그인(email, pw)
        // SignInAnonymous : 익명로그인()
        // signOut() : 로그아웃(페이지를 이동하는 기능x) -> 따로 구현을 해줘야 함
        
        bnv.setOnItemSelectedListener { item ->
            // item -> 내가 선택한 item의 정보
            Log.d("id", item.itemId.toString())
            when(item.itemId){
                R.id.tap1 ->{
                    // Fragment1Home 부분화면으로 갈아끼워준다
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1Home()
                    ).commit()
                }
                R.id.tap2 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2Tip()
                    ).commit()
                }
                R.id.tap3 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3Talk()
                    ).commit()
                }
                R.id.tap4 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4Bookmark()
                    ).commit()
                }
                R.id.tap5 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        ChatFragment()
                    ).commit()
                }
            }
            // false : 이벤트가 끝나지 않음 -> 클릭 작동 X
            // true : 이벤트 종료를 감지해야함
            true
        }
    }
}