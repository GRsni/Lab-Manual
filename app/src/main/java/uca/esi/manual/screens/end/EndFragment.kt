package uca.esi.manual.screens.end

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.activities.main.MainActivityViewModel
import uca.esi.manual.databinding.EndFragmentBinding

class EndFragment : Fragment() {

    private lateinit var viewModel: EndViewModel
    private lateinit var viewModelFactory: EndViewModelFactory

    private lateinit var binding: EndFragmentBinding

    private lateinit var activityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.end_fragment,
            container,
            false
        )

        activityViewModel =
            ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        val args = EndFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = EndViewModelFactory(
            args.allCorrect, args.surveyDone
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(EndViewModel::class.java)

        binding.textoFinal.text = viewModel.introString.resolve(requireContext())

        binding.buttonNext.text = viewModel.buttonNextString.resolve(requireContext())

        binding.buttonReturn.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                EndFragmentDirections.actionEndFragmentToTitleFragment()
            )
        }

        binding.buttonNext.setOnClickListener {
            if (activityViewModel.surveyDone) {
                //Exit the app from button
                requireActivity().moveTaskToBack(true)
                requireActivity().finish()
            } else {
                NavHostFragment.findNavController(this).navigate(
                    EndFragmentDirections.actionEndFragmentToSurveyIntroFragment()
                )
            }
        }
        return binding.root
    }
}