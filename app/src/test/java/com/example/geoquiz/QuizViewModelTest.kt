package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.After
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before

class QuizViewModelTest {

    private lateinit var currentIndex: Map<String, Int>
    private lateinit var  savedStateHandle: SavedStateHandle
    private lateinit var quizViewModel: QuizViewModel
    @Before
    fun setUp(){
        currentIndex= mapOf(CURRENT_INDEX_KEY to 2)
        savedStateHandle =  SavedStateHandle(currentIndex)
        quizViewModel = QuizViewModel(savedStateHandle)
    }

    @After
    fun tearDown(){
        currentIndex= mapOf(CURRENT_INDEX_KEY to 0)
        savedStateHandle =  SavedStateHandle(currentIndex)
        quizViewModel = QuizViewModel(savedStateHandle)
    }


    @Test
    fun providesExpectedQuestionText() {
        assertEquals(R.string.question_ocean, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_cariberra, quizViewModel.currentQuestionText)
    }

    @Test
    fun providesExpectedAnswerIsNotNull() {
          assertEquals(R.string.question_ocean, quizViewModel.currentQuestionText)
           assertNotNull(quizViewModel.currentQuestionAnswer)
    }
}