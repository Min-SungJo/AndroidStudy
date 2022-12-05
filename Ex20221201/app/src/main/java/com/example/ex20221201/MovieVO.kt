package com.example.ex20221201
// data -> getter/setter 생성
// 작성하지 않으면, 사용될 때 getter/setter 생성
data class MovieVO(
    val rank: String,
    val rankOldAndNew: String,
    val movieNm: String,
    val audiAcc: String,
    val openDt: String
) {
}