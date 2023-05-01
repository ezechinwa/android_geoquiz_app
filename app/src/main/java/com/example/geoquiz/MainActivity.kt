package com.example.geoquiz


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.example.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    private val TAG:String = "MainActivityLog"
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(TAG,"Got a QuizViewModel: $quizViewModel")

        binding.questionText.setText(quizViewModel.currentQuestionText)

        binding.questionText.setOnClickListener{
            updateCounter()
            updateQuestion()
        }

        binding.trueButton.setOnClickListener {
            // Do something when the trueButton is clicked
            checkAnswer(true)
            updateCounter()
            updateQuestion()
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            updateCounter()
            updateQuestion()
         }

        binding.nextButton.setOnClickListener {
            updateCounter()
            updateQuestion()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy called : $quizViewModel")
    }

    private fun toastMessage(context: Context, @StringRes resourceId: Int, duration: Int ):Unit {
        Toast.makeText(context, resourceId, duration).show()
    }

    private fun updateCounter():Unit {
      quizViewModel.moveToNext()
    }
    private fun updateQuestion(): Unit{
        binding.questionText.setText(quizViewModel.currentQuestionText)
    }

    private fun checkAnswer(userAnswer: Boolean): Unit{
       if (userAnswer == quizViewModel.currentQuestionAnswer) {
           toastMessage(this,R.string.correct_toast_text,Toast.LENGTH_SHORT)
       }else{
           toastMessage(this,R.string.incorrect_toast_text, Toast.LENGTH_LONG)
       }
    }
}