package uca.esi.manual.screens.survey.suggestion

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.activities.main.MainActivityViewModel
import uca.esi.manual.databinding.SuggestionSurveyFragmentBinding

class SuggestionSurveyFragment : Fragment() {

    private lateinit var viewModel: SuggestionSurveyViewModel

    private lateinit var viewModelFactory: SuggestionSurveyViewModelFactory

    private lateinit var binding: SuggestionSurveyFragmentBinding

    private lateinit var activityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.suggestion_survey_fragment,
            container,
            false
        )

        activityViewModel =
            ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        viewModelFactory = SuggestionSurveyViewModelFactory(
            SuggestionSurveyFragmentArgs.fromBundle(requireArguments()).survey
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SuggestionSurveyViewModel::class.java)

        addEventOverflowObserver()
        addEventSendSurveyObserver()

        binding.suggestionSurveyViewModel = viewModel
        binding.lifecycleOwner = this

        binding.textSuggestionEdittext.addTextChangedListener {
            if (it != null) {
                viewModel.setSuggestionText(it)
            }
        }


        binding.buttonBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                SuggestionSurveyFragmentDirections.actionSuggestionSurveyFragmentToLikertSurveyFragment(
                    viewModel.survey
                )
            )
        }

        binding.buttonExit.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                SuggestionSurveyFragmentDirections.actionSuggestionSurveyFragmentToTitleFragment()
            )
        }

        return binding.root
    }

    private fun addEventOverflowObserver() {
        viewModel.eventOverflow.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(
                    activity,
                    R.string.error_demasiados_caracteres,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun addEventSendSurveyObserver() {
        viewModel.eventSendSurvey.observe(viewLifecycleOwner, {
            if (it) {
                AlertDialog.Builder(activity)
                    .setTitle(R.string.titulo_confirmar_encuesta)
                    .setMessage(R.string.cuerpo_confirmar_encuesta)
                    .setPositiveButton(R.string.boton_enviar) { _, _ ->
                        viewModel.uploadSurvey()
                        activityViewModel.surveyDone = true
                        viewModel.onEventSendSurveyComplete()
                        NavHostFragment.findNavController(this).navigate(
                            SuggestionSurveyFragmentDirections.actionSuggestionSurveyFragmentToEndFragment(
                                allCorrect = false, surveyDone = true
                            )
                        )
                    }
                    .setNeutralButton(R.string.boton_cancelar, null)
                    .show()

            }
        })
    }
}