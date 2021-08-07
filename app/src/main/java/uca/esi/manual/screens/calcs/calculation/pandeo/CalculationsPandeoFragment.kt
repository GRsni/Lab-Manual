package uca.esi.manual.screens.calcs.calculation.pandeo

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
import uca.esi.manual.databinding.CalculationsPandeoFragmentBinding
import uca.esi.manual.utils.showErrorDialog

class CalculationsPandeoFragment : Fragment() {

    private lateinit var viewModel: CalculationsPandeoViewModel
    private lateinit var viewModelFactory: CalculationsPandeoViewModelFactory

    private lateinit var binding: CalculationsPandeoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.calculations_pandeo_fragment,
            container,
            false
        )

        viewModelFactory = CalculationsPandeoViewModelFactory(
            CalculationsPandeoFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CalculationsPandeoViewModel::class.java)

        binding.calculationsPandeoViewModel = viewModel
        binding.lifecycleOwner = this

        addEventCorrectDataObserver()
        addEventEmptyDataObserver()
        addEventWrongDataObserver()
        addEventAutocompleteDataObserver()

        binding.campoCargaCritica.addTextChangedListener {
            if (it != null) {
                viewModel.setLoadValue(it)
            }
        }

        binding.buttonBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                CalculationsPandeoFragmentDirections.actionCalculationsPandeoFragmentBackwards(
                    viewModel.lab
                )
            )
        }

        return binding.root
    }


    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, { dataIsCorrect ->
            if (dataIsCorrect) {
                NavHostFragment.findNavController(this).navigate(
                    CalculationsPandeoFragmentDirections.actionCalculationsPandeoFragmentToQuestionsFragment(
                        viewModel.lab
                    )
                )
                viewModel.onCorrectDataComplete()
            }
        })
    }

    private fun addEventWrongDataObserver() {
        viewModel.eventWrongData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                showErrorDialog(
                    requireActivity(),
                    R.string.titulo_mensaje_error_carga_pandeo,
                    R.string.cuerpo_mensaje_error_carga_pandeo
                )
                viewModel.onWrongDataComplete()
            }
        })
    }

    private fun addEventEmptyDataObserver() {
        viewModel.eventEmptyData.observe(viewLifecycleOwner, { dataIsEmpty ->
            if (dataIsEmpty) {
                Toast.makeText(
                    activity,
                    R.string.error_valores_vacios,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.onEmptyDataComplete()
            }
        })
    }

    private fun addEventAutocompleteDataObserver() {
        viewModel.eventAutocompleteData.observe(viewLifecycleOwner, { dataAutocompleted ->
            if (dataAutocompleted) {
                AlertDialog.Builder(activity)
                    .setTitle(R.string.datos_experimento)
                    .setMessage(R.string.datos_automaticos)
                    .setPositiveButton(
                        R.string.boton_aceptar
                    ) { _, _ -> viewModel.onAutocompleteDataComplete() }
                    .show()
            }
        })
    }

}