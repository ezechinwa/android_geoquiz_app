package com.example.geoquiz


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val questionBank: List<Question>  = listOf(
        Question(R.string.question_cariberra, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_ocean, true),
    )
    private var currentIndex: Int = 0
    private val TAG:String = "MainActivity"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @StringRes val questionTextResId: Int = questionBank[currentIndex].textResId
        binding.questionText.setText(questionTextResId)

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

    private fun toastMessage(context: Context, @StringRes resourceId: Int, duration: Int ):Unit {
        Toast.makeText(context, resourceId, duration).show()
    }

    private fun updateCounter():Unit {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    private fun updateQuestion(): Unit{
        @StringRes val questionTextResId: Int = questionBank[currentIndex].textResId
        binding.questionText.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean): Unit{
       if (userAnswer == questionBank[currentIndex].answer) {
           toastMessage(this,R.string.correct_toast_text,Toast.LENGTH_SHORT)
       }else{
           toastMessage(this,R.string.incorrect_toast_text, Toast.LENGTH_LONG)
       }
    }
}