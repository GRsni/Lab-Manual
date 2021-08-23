package uca.esi.manual.screens.survey.likert

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber
import uca.esi.manual.R
import uca.esi.manual.databinding.LikertSurveyFragmentBinding
import uca.esi.manual.utils.isDarkThemeOn
import uca.esi.manual.utils.showErrorDialog

/**
 * Likert survey fragment
 *
 * @constructor Create empty Likert survey fragment
 */
class LikertSurveyFragment : Fragment() {

    private lateinit var viewModel: LikertSurveyViewModel

    private lateinit var viewModelFactory: LikertSurveyViewModelFactory

    private lateinit var binding: LikertSurveyFragmentBinding
    private lateinit var rgList: List<RadioGroup>
    private var checkedIndexRbMap = mutableMapOf<Int, Int>()


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
            R.layout.likert_survey_fragment,
            container,
            false
        )

        viewModelFactory = LikertSurveyViewModelFactory(
            LikertSurveyFragmentArgs.fromBundle(requireArguments()).survey
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LikertSurveyViewModel::class.java)

        addEventCorrectDataObserver()
        addEventEmptyDataObserver()

        binding.likertSurveyViewModel = viewModel
        binding.lifecycleOwner = this

        rgList = loadRadioGroupsList()

        setSmileyIconsWithTheme()

        setExplanationAlertDialogs()

        loadRadioButtons()
        setCheckedRadioButtons()

        binding.buttonBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                LikertSurveyFragmentDirections.actionLikertSurveyFragmentToDichotomicSurveyFragment(
                    viewModel.survey
                )
            )
        }

        binding.buttonExit.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                LikertSurveyFragmentDirections.actionLikertSurveyFragmentToTitleFragment()
            )
        }

        return binding.root
    }

    /**
     * Add event correct data observer
     *
     */
    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, {
            if (it) {
                Timber.d("Survey values: ${viewModel.survey.likert}")
                NavHostFragment.findNavController(this).navigate(
                    LikertSurveyFragmentDirections.actionLikertSurveyFragmentToSuggestionSurveyFragment(
                        viewModel.survey
                    )
                )
                viewModel.onCorrectDataComplete()
            }
        })
    }

    /**
     * Add event empty data observer
     *
     */
    private fun addEventEmptyDataObserver() {
        viewModel.eventEmptyData.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(activity, R.string.error_respuestas_vacias, Toast.LENGTH_SHORT)
                    .show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

    /**
     * Set smiley icons with theme
     *
     */
    private fun setSmileyIconsWithTheme() {
        binding.sadFace.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                when (requireContext().isDarkThemeOn()) {
                    true -> R.drawable.sad_face_night
                    false -> R.drawable.sad_face
                }
            )
        )

        binding.neutralFace.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                when (requireContext().isDarkThemeOn()) {
                    true -> R.drawable.neutral_face_night
                    false -> R.drawable.neutral_face
                }
            )
        )

        binding.happyFace.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                when (requireContext().isDarkThemeOn()) {
                    true -> R.drawable.happy_face_night
                    false -> R.drawable.happy_face
                }
            )
        )
    }

    /**
     * Set explanation alert dialogs
     *
     */
    private fun setExplanationAlertDialogs() {
        binding.textEase.setOnClickListener {
            showErrorDialog(
                requireActivity(),
                R.string.facilidad_uso,
                R.string.explicacion_facilidad_uso
            )
        }
        binding.textHelp.setOnClickListener {
            showErrorDialog(
                requireActivity(),
                R.string.ayuda_aprendizaje,
                R.string.explicacion_ayuda_aprendizaje
            )
        }
        binding.textUnderstanding.setOnClickListener {
            showErrorDialog(
                requireActivity(),
                R.string.compresion_preguntas,
                R.string.explicacion_comprension_preguntas
            )
        }
        binding.textUi.setOnClickListener {
            showErrorDialog(
                requireActivity(),
                R.string.interfaz_usuario,
                R.string.explicacion_interfaz_usuario
            )
        }
        binding.textUx.setOnClickListener {
            showErrorDialog(
                requireActivity(),
                R.string.experiencia_usuario,
                R.string.explicacion_ux_feel
            )
        }
    }

    /**
     * Load radio groups list
     *
     * @return
     */
    private fun loadRadioGroupsList(): List<RadioGroup> {
        return listOf(
            binding.radioGroupEase,
            binding.radioGroupHelp,
            binding.radioGroupUnderstanding,
            binding.radioGroupUI,
            binding.radioGroupUX
        )
    }

    /**
     * Load radio buttons
     *
     */
    private fun loadRadioButtons() {
        val layoutParams = RadioGroup.LayoutParams(
            0, RadioGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            weight = 1f
            gravity = Gravity.CENTER_VERTICAL
        }

        /**
         * For each question in the likert survey, create 5 radio buttons
         * associated to their corresponding radio groups
         */
        for (likertIndex in viewModel.survey.likert.indices) {
            for (rbIndex in 1..5) {
                val rb = RadioButton(activity)
                rb.id = View.generateViewId()
                rb.layoutParams = layoutParams
                setOnclickListener(rb, likertIndex, rbIndex)

                //We save the checked button index to set it after all buttons are created
                if (viewModel.survey.likert[likertIndex] == rbIndex) {
                    checkedIndexRbMap[likertIndex] = rb.id
                }
                rgList[likertIndex].addView(rb)
            }
        }
    }

    /**
     * Set onclick listener
     *
     * @param rb
     * @param likertIndex
     * @param rbIndex
     */
    private fun setOnclickListener(rb: RadioButton, likertIndex: Int, rbIndex: Int) {
        rb.setOnClickListener {
            when (likertIndex) {
                0 -> viewModel.onEaseRadioButtonPress(rbIndex)
                1 -> viewModel.onHelpRadioButtonPress(rbIndex)
                2 -> viewModel.onUnderstandingRadioButtonPress(rbIndex)
                3 -> viewModel.onUIRadioButtonPress(rbIndex)
                4 -> viewModel.onUXRadioButtonPress(rbIndex)
                else -> Timber.e("Unknown likert index")
            }
        }
    }

    /**
     * Set checked radio buttons
     *
     */
    private fun setCheckedRadioButtons() {
        for (entry in checkedIndexRbMap.entries) {
            rgList[entry.key].check(entry.value)
        }
    }

}