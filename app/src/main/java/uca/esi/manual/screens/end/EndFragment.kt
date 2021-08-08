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
import uca.esi.manual.databinding.EndFragmentBinding

class EndFragment : Fragment() {

    private lateinit var viewModel: EndViewModel
    private lateinit var viewModelFactory: EndViewModelFactory

    private lateinit var binding: EndFragmentBinding

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

        viewModelFactory = EndViewModelFactory(
            EndFragmentArgs.fromBundle(requireArguments()).allCorrect
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(EndViewModel::class.java)

        binding.textoFinal.text = viewModel.introString.resolve(requireContext())

        binding.buttonReturn.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                EndFragmentDirections.actionEndFragmentToTitleFragment()
            )
        }

        return binding.root
    }
}