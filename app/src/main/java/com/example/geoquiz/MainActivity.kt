package com.example.geoquiz


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var questionBank: List<Question>  = listOf(
        Question(R.string.question_cariberra, true, false, 10),
        Question(R.string.question_africa, false, false, 10),
        Question(R.string.question_ocean, true, false, 5),
    )
    private var currentIndex: Int = 0
    private var currentScore: Int = 0
    private val TAG:String = "MainActivityLogcat"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(savedInstanceState: Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @StringRes val questionTextResId: Int = questionBank[currentIndex].textResId
        binding.questionText.setText(questionTextResId)
        binding.scoreText.setText(currentIndex.toString())

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
        var questionAnsered = questionBank[currentIndex].answered
        if (questionAnsered) {
            binding.trueButton.visibility = View.GONE
            binding.falseButton.visibility = View.GONE
        }

        @StringRes val questionTextResId: Int = questionBank[currentIndex].textResId
        binding.questionText.setText(questionTextResId)

    }

    private fun updateAnsweredStatus(): Unit {
        Log.i(TAG, "updateAnsweredStatus called")
        var questionAnsered = questionBank[currentIndex].answered
        Log.i(TAG, "updateAnsweredStatus before Update "+ questionAnsered.toString())

        questionBank[currentIndex].answered = true
         questionAnsered = questionBank[currentIndex].answered

        Log.i(TAG, "updateAnsweredStatus after Update "+ questionAnsered.toString())
    }

    private fun checkAnswer(userAnswer: Boolean): Unit{
       if (userAnswer == questionBank[currentIndex].answer) {
           toastMessage(this,R.string.correct_toast_text,Toast.LENGTH_SHORT)
           currentScore = currentScore + questionBank[currentIndex].point
       }else{
           toastMessage(this,R.string.incorrect_toast_text, Toast.LENGTH_LONG)
       }

       if(currentIndex == ( questionBank.size - 1)){
           binding.scoreLabelText.setText("Total Score: ")
           var total: Int = 0 ;
           for(question in questionBank){
               total = total + question.point
           }
           val percentageScore:Double = ( (currentScore.toDouble() /total.toDouble())) * 100
           val totalScoreMessage: String = "You scored : "+ percentageScore + "%"

           binding.scoreText.setText(totalScoreMessage)
           binding.scoreLabelText.setText("")
           updateAnsweredStatus()
       }else{
           binding.scoreText.setText(currentScore.toString())
           updateAnsweredStatus()
       }

    }
}