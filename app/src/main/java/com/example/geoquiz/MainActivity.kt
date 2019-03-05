package com.example.geoquiz

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var mTrueButton:Button
    lateinit var mFalseButton:Button
    lateinit var mNextButton: Button
    lateinit var mPrevButton: Button

    var mQuestionBank = arrayOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true)
    )


   @SuppressLint("MissingSuperCall")
   override fun onSaveInstanceState(savedInstanceState :Bundle){
        super.onSaveInstanceState(savedInstanceState)
       savedInstanceState.putInt(KEY_INDEX,mCurrentIndex)
    }

    var mCurrentIndex = 0

    final var KEY_INDEX: String = "index"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0)

        var mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        mQuestionTextView.setOnClickListener{_ :View? -> mCurrentIndex = (mCurrentIndex +1)%mQuestionTextView.length()
        var question = mQuestionBank[mCurrentIndex].mTextResId
        mQuestionTextView.setText(question)}
        var question = mQuestionBank[mCurrentIndex].mTextResId
        mQuestionTextView.setText(question)

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener {_ -> checkAnswer(true)
        mTrueButton.isClickable = false
        mFalseButton.isClickable = false
        }
        
        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener { _ ->
        checkAnswer(false)
            mTrueButton.isClickable = false
            mFalseButton.isClickable = false}

        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener{ _ : View? -> mCurrentIndex = (mCurrentIndex + 1)% mQuestionTextView.length()
        var question = mQuestionBank[mCurrentIndex].mTextResId
        mQuestionTextView.setText(question)
            mTrueButton.isClickable = true
            mFalseButton.isClickable = true
        }

        mPrevButton = findViewById(R.id.prev_button)
        mPrevButton.setOnClickListener{_:View? -> mCurrentIndex = (mCurrentIndex-1)% mQuestionTextView.length()
        var question = mQuestionBank[mCurrentIndex].mTextResId
        mQuestionTextView.setText(question)
            mTrueButton.isClickable = true
            mFalseButton.isClickable = true}
    }

    fun checkAnswer(userPressedTrue:Boolean){
        var answerIsTrue:Boolean = mQuestionBank[mCurrentIndex].mAnswerTrue

        var messageResId:Int = 0

        if(userPressedTrue == answerIsTrue)
            messageResId = R.string.correct_toast
        else
            messageResId = R.string.incorrect_toast
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
            .show()
    }
}
