package uca.esi.manual.screens.questionary

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.QuestionsFragmentBinding


/**
 * Questions fragment
 *
 * @constructor Create empty Questions fragment
 */
class QuestionsFragment : Fragment() {

    private lateinit var viewModel: QuestionsViewModel
    private lateinit var viewModelFactory: QuestionsViewModelFactory

    private lateinit var binding: QuestionsFragmentBinding

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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

        binding.linearLayoutTipoTest.visibility = View.INVISIBLE

        addEventNextQuestionObserver()

        addEventTestBeginObserver()

        addEventTestDoneObserver()

        addEventFinishedObserver()

        return binding.root
    }


    /**
     * Load questions linear layout
     *
     * @param counter
     */
    private fun loadQuestionsLinearLayout(counter: Int) {
        binding.textoEnunciado.text = viewModel.questionList[counter].title

        //Clear the radio group of the previous radio buttons
        if (counter > 0) {
            binding.questionsRadioGroup.removeAllViews()
        }

        for (i in viewModel.questionList[counter].answers.indices) {
            val radioButton = RadioButton(activity)
            radioButton.text = viewModel.questionList[counter].answers[i]
            radioButton.textSize = 14F
            radioButton.setOnClickListener {
                viewModel.onChangedAnswer(i)
            }

            binding.questionsRadioGroup.addView(radioButton)
        }
    }

    /**
     * Show answers
     *
     */
    private fun showAnswers() {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(14, 4, 14, 4)
        for (i in viewModel.questionList.indices) {
            val textView = TextView(activity)
            textView.text = resources.getString(R.string.guion, viewModel.questionList[i].title)
            if (viewModel.correctAnswers[i]) {
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.rightAnswer))
            } else {
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.wrongAnswer))
            }
            textView.background = getBackground()
            textView.setPadding(8, 4, 8, 4)
            binding.linearLayoutTipoTest.addView(textView, layoutParams)
        }
    }

    /**
     * Get background
     *
     * @return
     */
    private fun getBackground(): Drawable {
        val typedValue = TypedValue()

        requireActivity().theme
            .resolveAttribute(R.attr.textView_background, typedValue, true)

        return ContextCompat.getDrawable(requireContext(), typedValue.resourceId)!!
    }

    /**
     * Add event next question observer
     *
     */
    private fun addEventNextQuestionObserver() {
        viewModel.eventNextQuestion.observe(viewLifecycleOwner, { nextQuestion ->
            if (nextQuestion) {
                loadQuestionsLinearLayout(viewModel.counter)
                viewModel.onEventNextQuestionComplete()
            }
        })
    }

    /**
     * Add event test begin observer
     *
     */
    private fun addEventTestBeginObserver() {
        viewModel.eventTestBegin.observe(viewLifecycleOwner, { testBegin ->
            if (testBegin) {
                binding.linearLayoutTipoTest.visibility = View.VISIBLE
                binding.buttonNext.text = resources.getText(R.string.boton_siguiente)
                loadQuestionsLinearLayout(viewModel.counter)
                viewModel.onEventTestBeginComplete()
            }
        })
    }

    /**
     * Add event test done observer
     *
     */
    private fun addEventTestDoneObserver() {
        viewModel.eventTestDone.observe(viewLifecycleOwner, { testDone ->
            if (testDone) {
                binding.linearLayoutTipoTest.removeAllViews()
                showAnswers()
                binding.buttonNext.text = resources.getString(R.string.boton_finalizar)
                binding.textoEnunciado.text = resources.getString(R.string.resultado_cuestionario)
                viewModel.onEventTestDoneComplete()
            }
        })
    }

    /**
     * Add event finished observer
     *
     */
    private fun addEventFinishedObserver() {
        viewModel.eventFinished.observe(viewLifecycleOwner, { finished ->
            if (finished) {
                NavHostFragment.findNavController(this).navigate(
                    QuestionsFragmentDirections.actionQuestionsFragmentToEndFragment(
                        viewModel.correctAnswers.all { b -> b }
                    )
                )
            }
        })
    }
}