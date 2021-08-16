package uca.esi.manual.screens.survey.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.SurveyIntroFragmentBinding
import uca.esi.manual.models.Survey
import java.util.*

class SurveyIntroFragment : Fragment() {

    private lateinit var binding: SurveyIntroFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.survey_intro_fragment,
            container,
            false
        )

        binding.buttonNext.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                SurveyIntroFragmentDirections.actionSurveyIntroFragmentToDichotomicSurveyFragment(
                    Survey(
                        Date().toString(), MutableList(5) { 0 }
                    ))
            )
        }

        binding.buttonExit.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                SurveyIntroFragmentDirections.actionSurveyIntroFragmentToTitleFragment()
            )
        }

        return binding.root
    }

}