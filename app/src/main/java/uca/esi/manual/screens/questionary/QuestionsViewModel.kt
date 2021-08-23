package uca.esi.manual.screens.questionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uca.esi.manual.models.Question
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.screens.questionary.repository.QuestionsRepository
import uca.esi.manual.database.DatabaseHandler

/**
 * Questions view model
 *
 * @property lab
 * @constructor Create empty Questions view model
 */
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

    /**
     * On changed answer
     *
     * @param index
     */
    fun onChangedAnswer(index: Int) {
        _checkedAnswer.value = index
    }

    /**
     * On button press
     *
     */
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

    /**
     * Check answer
     *
     * @param index
     */
    private fun checkAnswer(index: Int) {
        Timber.i("Checked answer $index is: ${_checkedAnswer.value}, correct is ${questionList[index].correctIndex}")
        if (_checkedAnswer.value != null) {
            correctAnswers[index] = _checkedAnswer.value == questionList[index].correctIndex
        }
        // Reset the checkedAnswer index for the next question
        _checkedAnswer.value = -1
    }

    /**
     * Save answers
     *
     */
    private fun saveAnswers() {
        lab.isQ1 = correctAnswers[0]
        lab.isQ2 = correctAnswers[1]
        lab.isQ3 = correctAnswers[2]
        lab.isQ4 = correctAnswers[3]
    }

    /**
     * On event test begin
     *
     */
    private fun onEventTestBegin() {
        _eventTestBegin.value = true
    }

    /**
     * On event test begin complete
     *
     */
    fun onEventTestBeginComplete() {
        _eventTestBegin.value = false
    }

    /**
     * On event next question
     *
     */
    private fun onEventNextQuestion() {
        _eventNextQuestion.value = true
    }

    /**
     * On event next question complete
     *
     */
    fun onEventNextQuestionComplete() {
        _eventNextQuestion.value = false
    }

    /**
     * On event test done
     *
     */
    private fun onEventTestDone() {
        _eventTestDone.value = true
    }

    /**
     * On event test done complete
     *
     */
    fun onEventTestDoneComplete() {
        _eventTestDone.value = false
    }

    /**
     * On event finished
     *
     */
    private fun onEventFinished() {
        _eventFinished.value = true
    }

    /**
     * On event finished complete
     *
     */
    fun onEventFinishedComplete() {
        _eventFinished.value = false
    }
}