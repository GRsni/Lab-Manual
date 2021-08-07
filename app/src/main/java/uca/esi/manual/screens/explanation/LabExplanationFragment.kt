package uca.esi.manual.screens.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.LabExplanationFragmentBinding
import uca.esi.manual.utils.printLabIfDebug

class LabExplanationFragment : Fragment() {

    private lateinit var viewModel: LabExplanationViewModel
    private lateinit var viewModelFactory: LabExplanationViewModelFactory

    private lateinit var binding: LabExplanationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.lab_explanation_fragment,
            container,
            false
        )
        val args = LabExplanationFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = LabExplanationViewModelFactory(
            args.userId,
            args.inLab,
            args.labType
        )
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LabExplanationViewModel::class.java)

        binding.labExplanationViewModel = viewModel
        binding.lifecycleOwner = this

        binding.textoIntroManual.text = viewModel.introText.resolve(requireContext())

        viewModel.eventButtonPress.observe(viewLifecycleOwner, { buttonIsPressed ->
            if (buttonIsPressed) {
                launchChest()
                viewModel.onButtonPressComplete()
            }
        })

        printLabIfDebug(viewModel.lab)

        return binding.root
    }


    private fun launchChest() {
        NavHostFragment.findNavController(this).navigate(
            LabExplanationFragmentDirections.actionLabExplanationFragmentToChestFragment(
                viewModel.lab
            )
        )
    }
}