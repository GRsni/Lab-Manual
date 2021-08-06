package uca.esi.manual.screens.examination.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uca.esi.manual.models.Question
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.screens.examination.questions.repository.QuestionsRepository

class QuestionsViewModel(labIN: BaseLab) : ViewModel() {
    private val repository = QuestionsRepository()

    private val _lab = MutableLiveData<BaseLab>()
    val lab: LiveData<BaseLab>
        get() = _lab

    val questionList: List<Question>

    private lateinit var correctAnswers: BooleanArray

    var counter = 0

    private val _checkedAnswer = MutableLiveData<Int>()

    private val _eventNextQuestion = MutableLiveData<Boolean>()
    val eventNextQuestion: LiveData<Boolean>
        get() = _eventNextQuestion

    private val _eventTestDone = MutableLiveData<Boolean>()
    val eventTestDone: LiveData<Boolean>
        get() = _eventTestDone


    init {
        _lab.value = labIN
        _checkedAnswer.value = -1
        questionList = repository.getQuestionList(labIN.labType)
        correctAnswers = BooleanArray(questionList.size) { false }
    }

    fun onChangedAnswer(index: Int) {
        _checkedAnswer.value = index
    }

    fun checkAnswer() {
        Timber.i("Checked answer is: ${_checkedAnswer.value}, correct is ${questionList[counter].correctIndex}")
        if (_checkedAnswer.value != null) {
            correctAnswers[counter] = _checkedAnswer.value == questionList[counter].correctIndex
        }
        if (counter < questionList.size) {
            counter++
        } else {
            onEventTestDone()
        }

        // Reset the checkedAnswer index for the next question
        _checkedAnswer.value = -1
        onEventNextQuestion()
    }

    private fun onEventNextQuestion() {
        _eventNextQuestion.value = true
    }

    fun onEventNextQuestionComplete() {
        _eventNextQuestion.value = false
    }

    private fun onEventTestDone() {
        _eventTestDone.value = true
    }

    fun onEventTestDoneComplete() {
        _eventTestDone.value = false
    }
}