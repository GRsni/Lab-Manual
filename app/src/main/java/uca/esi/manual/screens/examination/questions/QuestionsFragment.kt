package uca.esi.manual.screens.examination.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.R
import uca.esi.manual.databinding.QuestionsFragmentBinding

class QuestionsFragment : Fragment() {

    private lateinit var viewModel: QuestionsViewModel
    private lateinit var viewModelFactory: QuestionsViewModelFactory

    private lateinit var binding: QuestionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.questions_fragment,
            container,
            false
        )

        viewModelFactory = QuestionsViewModelFactory(
            QuestionsFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(QuestionsViewModel::class.java)

        binding.questionsViewModel = viewModel
        binding.lifecycleOwner = this

        loadQuestionsLinearLayout(viewModel.counter)

        addEventNextQuestionObserver()

        return binding.root
    }


    private fun loadQuestionsLinearLayout(counter: Int) {
        binding.textoEnunciado.text = viewModel.questionList[counter].title

        val radioButtonList = mutableListOf<RadioButton>()
        for (i in viewModel.questionList[counter].answers.indices) {
            radioButtonList.add(RadioButton(activity))
            radioButtonList[i].text = viewModel.questionList[counter].answers[i]
            radioButtonList[i].textSize = 14F
            radioButtonList[i].setOnClickListener {
                viewModel.onChangedAnswer(i)
            }

            binding.questionsRadioGroup.addView(radioButtonList[i])
        }
    }


    private fun addEventNextQuestionObserver() {
        viewModel.eventNextQuestion.observe(viewLifecycleOwner, { nextQuestion ->
            if (nextQuestion) {
                loadQuestionsLinearLayout(viewModel.counter)
                viewModel.onEventNextQuestionComplete()
            }
        })
    }

}