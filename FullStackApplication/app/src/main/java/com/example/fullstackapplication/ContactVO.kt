package com.example.fullstackapplication

data class ContactVO(
    val name: String,
    val age: Int,
    val tel: String
) {
    
    // 만약, Firebase 의 RealTime Database 용이면
    // 기본 생성자 정의 필수
    constructor() : this("",0,"")
}