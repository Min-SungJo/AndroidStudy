package com.example.ex20221202

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

//    가로모드에 사용되는 요소들
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class Fragment1 : Fragment() {

//    private var param1: String? = null
//    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_1,container,false)
        // WebView 찾아오기 (초기화)
        val wv = view.findViewById<WebView>(R.id.wv)
        // WebView 에 원하는 웹페이지 띄우기
        val spf = requireActivity().getSharedPreferences(
            "mySPF",
            Context.MODE_PRIVATE
        )
        // 1. 주소준비
        val url =spf.getString("url", "http://www.google.co.kr")
        // 2. 설정변경(JavaScript 사용 가능하도록 '허용') -> Manifest.xml
        val ws = wv.settings
        ws.javaScriptEnabled=true
        // 3. WebView 에 Client 설정
        wv.webViewClient = WebViewClient()
        // 4. WebView 에 주소 적용
        // default Valur 가 있음
        wv.loadUrl(url!!)
        return view
    }
//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Fragment1().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}