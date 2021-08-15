package uca.esi.manual.screens.survey.likert

import android.os.Bundle
import android.view.Gravity.CENTER_VERTICAL
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

class LikertSurveyFragment : Fragment() {

    private lateinit var viewModel: LikertSurveyViewModel

    private lateinit var viewModelFactory: LikertSurveyViewModelFactory

    private lateinit var binding: LikertSurveyFragmentBinding

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


        setSmileyIconsWithTheme()

        setExplanationAlertDialogs()

        loadRadioGroups()

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

    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, {
            if (it) {
                Timber.d("Survey values: ${viewModel.survey.likert.asList()}")
                viewModel.onCorrectDataComplete()
            }
        })
    }

    private fun addEventEmptyDataObserver() {
        viewModel.eventEmptyData.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(activity, R.string.error_respuestas_vacias, Toast.LENGTH_SHORT)
                    .show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

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

    private fun loadRadioGroups() {
        val layoutParams = RadioGroup.LayoutParams(
            0, RadioGroup.LayoutParams.WRAP_CONTENT, 1f
        )
        val rgList = listOf(
            binding.radioGroupEase,
            binding.radioGroupHelp,
            binding.radioGroupUnderstanding,
            binding.radioGroupUI,
            binding.radioGroupUX
        )
        /**
         * For each question in the likert survey, create 5 radio buttons
         * associated to their corresponding radio groups
         */
        for (likertIndex in viewModel.survey.likert.indices) {
            for (rbIndex in 1..5) {
                val rb = RadioButton(activity)
                rb.layoutParams = layoutParams
                rb.gravity = CENTER_VERTICAL
                setOnclickListener(rb, likertIndex, rbIndex)
                if (viewModel.survey.likert[likertIndex] == rbIndex) {
                    Timber.d("rb: $rbIndex in radiogroup $likertIndex is checked")
                    rb.isChecked = true
                }
                rgList[likertIndex].addView(rb)
            }
        }
    }

    private fun setOnclickListener(rb: RadioButton, likertIndex: Int, rbIndex: Int) {
        rb.setOnClickListener {
            when (likertIndex) {
                0 -> viewModel.onEaseRadioButtonPress(rbIndex)
                1 -> viewModel.onHelpRadioButtonPress(rbIndex)
                2 -> viewModel.onUnderstandingRadioButtonPress(rbIndex)
                3 -> viewModel.onUIRadioButtonPress(rbIndex)
                4 -> viewModel.onUXRadioButtonPress(rbIndex)
                else -> Timber.e(IllegalArgumentException("Unknown likert index"))
            }
        }
    }
}