package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    // FirebaseAuth 선언 - private
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw = findViewById<EditText>(R.id.etJoinPw)
        val etJoinPwCheck = findViewById<EditText>(R.id.etJoinPwCheck)
        val btnJoinJoin = findViewById<Button>(R.id.btnJoinJoin)

        // auth 초기화
        auth = Firebase.auth
        // Firebase.auth: 로그인, 회원가입, 인증 시스템에 대한 모든 기능이 담겨있다


        // btnJoinJoin 을 눌렀을 때
        btnJoinJoin.setOnClickListener {
            var isJoin = false // 조건을 만족했는지 안했는지 확인하는 용도
            val email = etJoinEmail.text.toString()
            val pw = etJoinPw.text.toString()
            val pwCheck = etJoinPwCheck.text.toString()
//            Toast.makeText(applicationContext,"email: $email, pw: $pw",Toast.LENGTH_SHORT).show()

            Log.d("joinInput", "email : $email, pw: $pw")
            // EditText 에 내용이 있는가
            if(email.isEmpty()){
                Toast.makeText(this,"이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            }else if(pw.isEmpty()){
                Toast.makeText(this,"비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }else if (pwCheck.isEmpty()){
                Toast.makeText(this,"비밀번호 재입력을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (pwCheck != pw){// 비밀번호와 재입력한 비밀번호가 일치한가?
                Toast.makeText(this,"비밀번호 일치여부를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else if(pw.length<8){// 비밀번호가 8자리 이상인가?
                Toast.makeText(this,"비밀번호를 8자리 이상으로 입력하세요", Toast.LENGTH_SHORT).show()
            } else{
                isJoin = true
            }
            // Firebase 에 규칙은 정해져 있지만
            // 그 규칙을 사용자에게 알려주기 위해

            if (isJoin) {
                // create 가 보내고 있는 전달인자 2개(email, pw)는
                // 실제 회원가입 정보 전달(firebase 로)
                auth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this) { task ->
                        // task --> 보낸 후 결과(성공/실패)
                        if (task.isSuccessful) {
                            // 성공 시 실행할 코드
                            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // 실패 시 실행할 코드
                            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

    }
}