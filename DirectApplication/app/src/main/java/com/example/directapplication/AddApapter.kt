package com.example.directapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class AddApapter(val context:Context, val layout: Int, val data: ArrayList<DirectVO>):BaseAdapter() {

    var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    override fun getCount(): Int {
        return  data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view = p1

        if(view == null){
            view = inflater?.inflate(layout,p2,false)
        }

        val tvTitle = view?.findViewById<TextView>(R.id.tvTitle)
        val tvUrl = view?.findViewById<TextView>(R.id.tvUrl)
        val btnMove = view?.findViewById<Button>(R.id.btnMove)
        tvTitle?.text = data[p0].title
        tvUrl?.text = data[p0].url
        btnMove?.setOnClickListener {
            val url = Uri.parse(data[p0].url)
            Log.d("url", url.toString())
            val intent = Intent(Intent.ACTION_VIEW, url)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        return view!!
    }
}