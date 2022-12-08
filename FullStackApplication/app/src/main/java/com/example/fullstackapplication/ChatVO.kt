package com.example.fullstackapplication

import android.widget.ImageView
import android.widget.TextView

data class ChatVO(val name: String, val msg: String) {
    
    // 반드시!! Firebase DataBase 사용하기 위해 기본 생성자 추가
    constructor() : this("","")
    
}