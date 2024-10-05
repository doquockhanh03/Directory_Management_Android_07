package com.example.buoi_07_bt

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    companion object{
        const val KEY = "KEY"
        const val TYPE_EDIT = "TYPE_EDIT"
        const val TYPE_ADD = "TYPE_ADD"
    }

    private var tvThem: TextView? = null
    private var rcvDocument: RecyclerView? = null
    private val arrayList: ArrayList<Document> = ArrayList()
    private var recyclerViewAdapter: RecyclerAdapter? = null

    @SuppressLint("NotifyDataSetChanged")
    private var startForReuslt = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result:ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val type = result.data?.extras?.getString(KEY)
            if(type == TYPE_ADD){
                val document = result.data?.extras?.getParcelable<Document>("Document")
                if(document != null){
                    arrayList.add(0, document)
                }
                recyclerViewAdapter?.notifyDataSetChanged()
            }else if(type == MainActivity.TYPE_EDIT){
                val document = result.data?.extras?.getParcelable<Document>("Document")
                if(document != null){
                    for(item in arrayList){
                        if(item.id == document.id){
                            item.title = document.title
                            item.content = document.content
                            break
                        }
                    }
                    recyclerViewAdapter?.notifyDataSetChanged()
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvThem = findViewById(R.id.tvThem)
        rcvDocument = findViewById(R.id.rcv_document)

        val linearLayoutManager = LinearLayoutManager(this)
        rcvDocument?.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvDocument?.addItemDecoration(dividerItemDecoration)
        arrayList.add(Document(1, "Căng thẳng Nga va Ucraina", "tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền"))
        arrayList.add(Document(2, "Căng thẳng Nga va Ucraina", "tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền"))
        arrayList.add(Document(3, "Căng thẳng Nga va Ucraina", "tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền"))
        arrayList.add(Document(4, "Căng thẳng Nga va Ucraina", "tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền"))
        arrayList.add(Document(5, "Căng thẳng Nga va Ucraina", "tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền"))
        recyclerViewAdapter = RecyclerAdapter(this, arrayList)
        rcvDocument?.adapter = recyclerViewAdapter

        // khi click vao thu muc se truyen du lieu sang man hinh thu 2
        recyclerViewAdapter?.onItemClick = {document, i ->
            val intent = Intent(this, Activity02::class.java)
                intent.putExtra("document", document)
                startForReuslt.launch(intent)
        }

        tvThem?.setOnClickListener{
            val intent = Intent(this, Activity03::class.java)
            startActivity(intent)
        }
    }
}