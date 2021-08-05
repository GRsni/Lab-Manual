package uca.esi.manual.screens.calcs.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.CalculationsDataFragmentBinding
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.utils.printLabIfDebug

class CalculationsDataFragment : Fragment() {

    private lateinit var viewModel: CalculationsDataViewModel

    private lateinit var viewModelFactory: CalculationsDataViewModelFactory

    private lateinit var binding: CalculationsDataFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.calculations_data_fragment,
            container,
            false
        )

        viewModelFactory = CalculationsDataViewModelFactory(
            CalculationsDataFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CalculationsDataViewModel::class.java)

        viewModel.lab.observe(viewLifecycleOwner, {
            printLabIfDebug(it)
        })

        binding.valoresFormula.text = viewModel.dataText
        binding.textoIntroFormulas.text = viewModel.introText.resolve(requireContext())

        binding.buttonNext.setOnClickListener {
            launchCalculations()
        }
        return binding.root
    }

    private fun launchCalculations() {
        when (viewModel.lab.value!!.labType) {
            BaseLab.LabType.PANDEO -> {
                NavHostFragment.findNavController(this).navigate(
                    CalculationsDataFragmentDirections.actionCalculationsDataFragmentToCalculationsPandeoFragment(
                        viewModel.lab.value!!
                    )
                )
            }
            BaseLab.LabType.TORSION -> {
                NavHostFragment.findNavController(this).navigate(
                    CalculationsDataFragmentDirections.actionCalculationsDataFragmentToCalculationsTorsionFragment(
                        viewModel.lab.value!!
                    )
                )
            }
            else -> Toast.makeText(
                activity,
                R.string.error_practica_no_implementada,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}