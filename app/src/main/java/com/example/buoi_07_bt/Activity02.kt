 package com.example.buoi_07_bt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity02 : AppCompatActivity() {
    private var tvHuy: TextView? = null
    private var tvSave: TextView? = null
    private var edtTitle: EditText? = null
    private var edtContent: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_02)

        tvHuy = findViewById(R.id.tvHuy)
        tvSave = findViewById(R.id.tvLuu)
        edtTitle = findViewById(R.id.edtTitle)
        edtContent = findViewById(R.id.edtContent)

        //Nhan du lieu tu man hinh 1
        val document = intent.extras?.get("document") as? Document
        if(document != null){
            edtTitle?.setText(document.title)
            edtContent?.setText(document.content)
        }

        tvSave?.setOnClickListener{
            // neu edtTitle?.text?.toString() bi null thi tra ve title va content rong
            document?.title = edtTitle?.text?.toString() ?: ""
            document?.content = edtContent?.text?.toString() ?: ""

            val intent : Intent = Intent()
            intent.putExtra("Document", document)
            intent.putExtra(MainActivity.KEY, MainActivity.TYPE_EDIT)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        tvHuy?.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}