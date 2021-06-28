package com.example.quizzup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra(Constants.USER_NAME)
        val tv_username = findViewById<TextView>(R.id.tv_username)
        tv_username.text = username.toString()

        val totalQ = intent.getIntExtra(Constants.TOTAL_QUESTIONS,10)
        val correctAns = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        val tv_score = findViewById<TextView>(R.id.tv_score)
        tv_score.text = correctAns.toString() + "/" + totalQ.toString()

        val finBtn = findViewById<Button>(R.id.btn_finish)

        finBtn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}