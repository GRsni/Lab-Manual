package uca.esi.manual.screens.calcs.calculation.torsion

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
import uca.esi.manual.R
import uca.esi.manual.databinding.CalculationsTorsionFragmentBinding
import uca.esi.manual.screens.calcs.data.CalculationsDataFragmentArgs

class CalculationsTorsionFragment : Fragment() {

    private lateinit var viewModel: CalculationsTorsionViewModel

    private lateinit var viewModelFactory: CalculationsTorsionViewModelFactory

    private lateinit var binding: CalculationsTorsionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.calculations_torsion_fragment,
            container,
            false
        )

        viewModelFactory = CalculationsTorsionViewModelFactory(
            CalculationsDataFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CalculationsTorsionViewModel::class.java)


        binding.calculationsTorsionViewModel = viewModel
        binding.lifecycleOwner = this

        addEventCorrectDataObserver()

        addEventEmptyDataObserver()

        addEventWrongAmpliDataObserver()

        addEventWrongMomentDataObserver()

        binding.campoMomento.addTextChangedListener {
            if (it != null) {
                viewModel.setMomentValue(it)
            }
        }

        binding.campoAmplificador.addTextChangedListener {
            if (it != null) {
                viewModel.setAmpliValue(it)
            }
        }

        return binding.root
    }

    private fun addEventCorrectDataObserver() {
        viewModel.eventCorrectData.observe(viewLifecycleOwner, { dataIsCorrect ->
            if (dataIsCorrect) {
                Toast.makeText(activity, R.string.datos_correctos, Toast.LENGTH_SHORT).show()
                viewModel.onCorrectDataComplete()
            }
        })
    }

    private fun addEventWrongAmpliDataObserver() {
        viewModel.eventWrongAmpliData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                showErrorDialog(
                    R.string.titulo_mensaje_error_ampli_torsion,
                    R.string.cuerpo_mensaje_error_ampli_torsion
                )
                viewModel.onWrongAmpliDataComplete()
            }
        })
    }

    private fun addEventWrongMomentDataObserver() {
        viewModel.eventWrongMomentData.observe(viewLifecycleOwner, { dataIsWrong ->
            if (dataIsWrong) {
                showErrorDialog(
                    R.string.titulo_mensaje_error_momento_torsion,
                    R.string.cuerpo_mensaje_error_momento_torsion
                )
                viewModel.onWrongMomentDataComplete()
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

    private fun showErrorDialog(titleId: Int, messageId: Int) {
        AlertDialog.Builder(activity)
            .setTitle(titleId)
            .setMessage(messageId)
            .setNeutralButton(R.string.boton_aceptar, null)
            .show()
    }

}