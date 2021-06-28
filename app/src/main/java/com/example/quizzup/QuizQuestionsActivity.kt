package com.example.quizzup

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 0
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var mCountCorrectAns: Int = 0
    private var mUserName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()
       // Log.i("Questions Size","${questionsList.size}")

        setQuestion()

        // load the option objects

        val tv_option1 = findViewById<TextView>(R.id.tv_option_one)
        val tv_option2 = findViewById<TextView>(R.id.tv_option_two)
        val tv_option3 = findViewById<TextView>(R.id.tv_option_three)
        val tv_option4 = findViewById<TextView>(R.id.tv_option_four)

        tv_option1.setOnClickListener(this)
        tv_option2.setOnClickListener(this)
        tv_option3.setOnClickListener(this)
        tv_option4.setOnClickListener(this)

        val submitBtn = findViewById<Button>(R.id.submit)
        submitBtn.setOnClickListener(this)



        /*val currPos = 0
        val question: Question? = questionsList[currPos]


        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = currPos+1


        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = "${currPos+1}/" + progressBar.max

        val tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = question!!.question

        val iv_image = findViewById<ImageView>(R.id.iv_image)
        iv_image.setImageResource(question!!.image)

        // load the option objects

        val tv_option1 = findViewById<TextView>(R.id.tv_option_one)
        val tv_option2 = findViewById<TextView>(R.id.tv_option_two)
        val tv_option3 = findViewById<TextView>(R.id.tv_option_three)
        val tv_option4 = findViewById<TextView>(R.id.tv_option_four)

        tv_option1.text = question!!.optionOne
        tv_option2.text = question!!.optionTwo
        tv_option3.text = question!!.optionThree
        tv_option4.text = question!!.optionFour*/
    }

    private fun setQuestion()
    {
        defaultOptionsView()

        val submitBtn = findViewById<Button>(R.id.submit)
        submitBtn.setOnClickListener(this)

        if(mCurrentPosition==mQuestionsList!!.size){
            submitBtn.text = "Finish!"
        }
        else
        {
            submitBtn.text = "Submit"
        }


        val currPos = mCurrentPosition
        val question: Question? = mQuestionsList!![currPos]


        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = currPos+1


        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = "${currPos+1}/" + progressBar.max

        val tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = question!!.question

        val iv_image = findViewById<ImageView>(R.id.iv_image)
        iv_image.setImageResource(question!!.image)

        // load the option objects

        val tv_option1 = findViewById<TextView>(R.id.tv_option_one)
        val tv_option2 = findViewById<TextView>(R.id.tv_option_two)
        val tv_option3 = findViewById<TextView>(R.id.tv_option_three)
        val tv_option4 = findViewById<TextView>(R.id.tv_option_four)

        tv_option1.text = question!!.optionOne
        tv_option2.text = question!!.optionTwo
        tv_option3.text = question!!.optionThree
        tv_option4.text = question!!.optionFour
    }

    private fun defaultOptionsView()
    {

        val tv_option1 = findViewById<TextView>(R.id.tv_option_one)
        val tv_option2 = findViewById<TextView>(R.id.tv_option_two)
        val tv_option3 = findViewById<TextView>(R.id.tv_option_three)
        val tv_option4 = findViewById<TextView>(R.id.tv_option_four)

        val options = ArrayList<TextView>()

        options.add(0,tv_option1)
        options.add(1,tv_option2)
        options.add(2,tv_option3)
        options.add(3,tv_option4)

        for(i in options)
        {
            i.setTextColor(Color.parseColor("#7A8089"))
            i.typeface = Typeface.DEFAULT
            i.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }



    }

    override fun onClick(v: View?) {

        val tv_option1 = findViewById<TextView>(R.id.tv_option_one)
        val tv_option2 = findViewById<TextView>(R.id.tv_option_two)
        val tv_option3 = findViewById<TextView>(R.id.tv_option_three)
        val tv_option4 = findViewById<TextView>(R.id.tv_option_four)

        val submitBtn = findViewById<Button>(R.id.submit)
        submitBtn.setOnClickListener(this)

        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option1, 1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option2, 2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option3, 3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option4, 4)
            }
            R.id.submit -> {
                if (mSelectedOption == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition < mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCountCorrectAns)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                            startActivity(intent)
                        }
                    }
                }
                else
                {
                    val question = mQuestionsList?.get(mCurrentPosition)
                    if(question!!.correctAnswer!=mSelectedOption){
                        ansView(mSelectedOption,R.drawable.wrong_option_border_bg)
                        ansView(question!!.correctAnswer,R.drawable.correct_option_border_bg)
                    }
                    else{
                        ansView(question!!.correctAnswer,R.drawable.correct_option_border_bg)
                        mCountCorrectAns++
                    }

                    if(mCurrentPosition==mQuestionsList!!.size-1)
                    {
                        submitBtn.text = "Finish!"
                    }
                    else
                    {
                        submitBtn.text = "Go to next Question"
                    }
                    mSelectedOption = 0
                }
            }
        }
    }

    private fun ansView(ans: Int, drawableView: Int)
    {
        val tv_option1 = findViewById<TextView>(R.id.tv_option_one)
        val tv_option2 = findViewById<TextView>(R.id.tv_option_two)
        val tv_option3 = findViewById<TextView>(R.id.tv_option_three)
        val tv_option4 = findViewById<TextView>(R.id.tv_option_four)

        when(ans)
        {
            1-> {
                tv_option1.background = ContextCompat.getDrawable(this,drawableView)
            }
            2-> {
                tv_option2.background = ContextCompat.getDrawable(this,drawableView)
            }
            3-> {
                tv_option3.background = ContextCompat.getDrawable(this,drawableView)
            }
            4-> {
                tv_option4.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int)
    {
        defaultOptionsView()
        mSelectedOption = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}