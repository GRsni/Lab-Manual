package uca.esi.manual.screens.questionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uca.esi.manual.models.Question
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.screens.questionary.repository.QuestionsRepository
import uca.esi.manual.database.DatabaseHandler

class QuestionsViewModel(var lab: BaseLab) : ViewModel() {
    private val repository = QuestionsRepository()
    private val dbHandler = DatabaseHandler()

    val questionList: List<Question>

    var correctAnswers: BooleanArray

    var counter = -1

    private val _checkedAnswer = MutableLiveData<Int>()

    private val _eventTestBegin = MutableLiveData<Boolean>()
    val eventTestBegin: LiveData<Boolean>
        get() = _eventTestBegin

    private val _eventNextQuestion = MutableLiveData<Boolean>()
    val eventNextQuestion: LiveData<Boolean>
        get() = _eventNextQuestion

    private val _eventTestDone = MutableLiveData<Boolean>()
    val eventTestDone: LiveData<Boolean>
        get() = _eventTestDone

    private val _eventFinished = MutableLiveData<Boolean>()
    val eventFinished: LiveData<Boolean>
        get() = _eventFinished

    init {
        _checkedAnswer.value = -1
        questionList = repository.getQuestionList(lab.labType)
        correctAnswers = BooleanArray(questionList.size) { false }
    }

    fun onChangedAnswer(index: Int) {
        _checkedAnswer.value = index
    }

    fun onButtonPress() {
        /**
         * Counter starts at -1, showing the introduction text
         * Then, as the user presses the continue button, the
         * answers is checked, the counter increments, and the
         * new question is loaded.
         *
         * From 0 to 3, the questions are shown, and the user
         * selects one of the possible answers
         *
         * Once the counter gets to 4, the last question is checked
         * and the answers are shown
         *
         * Counter [-1 -> 4]
         */
        counter++
        when {
            counter == 0 -> {
                onEventTestBegin()
            }
            counter <= questionList.size -> {
                checkAnswer(counter - 1)
                if (counter < questionList.size) {
                    onEventNextQuestion()
                } else {
                    saveAnswers()
                    onEventTestDone()
                    dbHandler.uploadData(lab)
                }
            }
            else -> {
                onEventFinished()
            }
        }
    }

    private fun checkAnswer(index: Int) {
        Timber.i("Checked answer $index is: ${_checkedAnswer.value}, correct is ${questionList[index].correctIndex}")
        if (_checkedAnswer.value != null) {
            correctAnswers[index] = _checkedAnswer.value == questionList[index].correctIndex
        }
        // Reset the checkedAnswer index for the next question
        _checkedAnswer.value = -1
    }

    private fun saveAnswers() {
        lab.isQ1 = correctAnswers[0]
        lab.isQ2 = correctAnswers[1]
        lab.isQ3 = correctAnswers[2]
        lab.isQ4 = correctAnswers[3]
    }

    private fun onEventTestBegin() {
        _eventTestBegin.value = true
    }

    fun onEventTestBeginComplete() {
        _eventTestBegin.value = false
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

    private fun onEventFinished() {
        _eventFinished.value = true
    }

    fun onEventFinishedComplete() {
        _eventFinished.value = false
    }
}