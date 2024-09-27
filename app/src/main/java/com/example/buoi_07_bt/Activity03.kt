package com.example.buoi_07_bt

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity03 : AppCompatActivity() {
    private var tvSave: TextView? = null
    private var tvHuy: TextView? = null
    private var edtTitle: EditText? = null
    private var edtContent: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_03)

        tvSave = findViewById(R.id.tvLuu)
        tvHuy = findViewById(R.id.tvHuy)
        edtTitle = findViewById(R.id.edtTitle)
        edtContent = findViewById(R.id.edtContent)

        tvSave?.setOnClickListener{
            val document = Document(
                id = System.currentTimeMillis().toInt(),
                title = edtTitle?.text.toString(),
                content = edtContent?.text.toString()
            )

            val intent : Intent = Intent()
            intent.putExtra("document", document)
            intent.putExtra(MainActivity.KEY, MainActivity.TYPE_ADD)
            setResult(RESULT_OK, intent)
            finish()
        }

        tvHuy?.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}