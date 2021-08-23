package uca.esi.manual.screens.survey.dichotomic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.DichotomicSurveyFragmentBinding

/**
 * Dichotomic survey fragment
 *
 * @constructor Create empty Dichotomic survey fragment
 */
class DichotomicSurveyFragment : Fragment() {

    private lateinit var viewModel: DichotomicSurveyViewModel

    private lateinit var viewModelFactory: DichotomicSurveyViewModelFactory

    private lateinit var binding: DichotomicSurveyFragmentBinding

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
            R.layout.dichotomic_survey_fragment,
            container,
            false
        )

        viewModelFactory = DichotomicSurveyViewModelFactory(
            DichotomicSurveyFragmentArgs.fromBundle(requireArguments()).survey
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DichotomicSurveyViewModel::class.java)

        binding.dichotomicViewModel = viewModel

        binding.switchLike.setOnCheckedChangeListener { _, b ->
            viewModel.onLikeSwitchClick(b)
        }

        binding.switchLearn.setOnCheckedChangeListener { _, b ->
            viewModel.onLearnSwitchClick(b)
        }

        binding.switchOutside.setOnCheckedChangeListener { _, b ->
            viewModel.onOutsideSwitchClick(b)
        }

        binding.buttonNext.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                DichotomicSurveyFragmentDirections.actionDichotomicSurveyFragmentToLikertSurveyFragment(
                    viewModel.survey
                )
            )
        }

        binding.buttonExit.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                DichotomicSurveyFragmentDirections.actionDichotomicSurveyFragmentToTitleFragment()
            )
        }

        binding.buttonBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                DichotomicSurveyFragmentDirections.actionDichotomicSurveyFragmentToSurveyIntroFragment()
            )
        }
        return binding.root
    }
}