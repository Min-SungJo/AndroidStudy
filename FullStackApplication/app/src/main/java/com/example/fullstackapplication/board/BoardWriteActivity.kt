package com.example.fullstackapplication.board

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fullstackapplication.R
import com.example.fullstackapplication.utils.FBAuth
import com.example.fullstackapplication.utils.FBdatabase
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardWriteActivity : AppCompatActivity() {
    lateinit var imgLoad: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        imgLoad = findViewById<ImageView>(R.id.imgLoad)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val imgWrite = findViewById<ImageView>(R.id.imgWrite)

        // 갤러리로 이동해서 이미지를 받아온다
        imgLoad.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            launcher.launch(intent)
        }

        // 현재 Activity 에 있는 모든 값(title, content, time, uri)을 Firebase 에 저장
        imgWrite.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            // board
            //  -key(게시물의 고유한 uid: push()) -> push 는 타임스탬프를 찍어준다
            //    -BoardVO(title, content, uid, time)
//            FBdatabase.getBoardRef()
//                .push()
//                .setValue(BoardVO("1", "1", "1", "1"))
            // FBAuth
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            // 실제 데이터를 넣어주자
            // setValue 가 호출되기 전에 미리 BoardVO 가 저장될 key 값(uid)을 선언
            // push 할 거고 key 값으로 사용할 거다
            var key = FBdatabase.getBoardRef().push().key.toString()

            FBdatabase.getBoardRef().child(key).setValue(BoardVO(title, content, uid, time))
            imgUpload(key) // 이미지 이름을 key 로 세팅할 수 있게 key 보내주기
            finish() // 이전 페이지로 돌아가기
        }

    }

    // img 는 content 가 아니라 storage 에 저장된다.
    // uri 만 저장한다면 select 할 수 없다
    // -> img 이름을 게시물uid.png로 저장 (push()로 저장했었다 -> key 값으로 저장)

    fun imgUpload(key: String) {
        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("$key.png") // 실제 게시물이 저장되는 경로
        // Get the data from an ImageView as bytes
        imgLoad.isDrawingCacheEnabled = true
        imgLoad.buildDrawingCache()
        val bitmap = (imgLoad.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        // compress -> img 압축(JPEG 로 가져온다, 00의 퀄리티 ) -> png 는 투명값이 있다
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    val launcher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            imgLoad.setImageURI(it.data?.data)
        }
    }
}