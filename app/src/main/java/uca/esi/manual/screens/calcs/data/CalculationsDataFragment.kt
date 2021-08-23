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

/**
 * Calculations data fragment
 *
 * @constructor Create empty Calculations data fragment
 */
class CalculationsDataFragment : Fragment() {

    private lateinit var viewModel: CalculationsDataViewModel

    private lateinit var viewModelFactory: CalculationsDataViewModelFactory

    private lateinit var binding: CalculationsDataFragmentBinding

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
            R.layout.calculations_data_fragment,
            container,
            false
        )

        viewModelFactory = CalculationsDataViewModelFactory(
            CalculationsDataFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CalculationsDataViewModel::class.java)

        printLabIfDebug(viewModel.lab)


        binding.valoresFormula.text = viewModel.dataText
        binding.textoIntroFormulas.text = viewModel.introText.resolve(requireContext())

        binding.buttonNext.setOnClickListener {
            launchCalculations()
        }
        return binding.root
    }

    /**
     * Launch calculations
     *
     */
    private fun launchCalculations() {
        when (viewModel.lab.labType) {
            BaseLab.LabType.PANDEO -> {
                NavHostFragment.findNavController(this).navigate(
                    CalculationsDataFragmentDirections.actionCalculationsDataFragmentToCalculationsPandeoFragment(
                        viewModel.lab
                    )
                )
            }
            BaseLab.LabType.TORSION -> {
                NavHostFragment.findNavController(this).navigate(
                    CalculationsDataFragmentDirections.actionCalculationsDataFragmentToCalculationsTorsionFragment(
                        viewModel.lab
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