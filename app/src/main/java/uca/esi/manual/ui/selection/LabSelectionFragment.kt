package uca.esi.manual.ui.selection

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.R
import uca.esi.manual.databinding.LabSelectionFragmentBinding

class LabSelectionFragment : Fragment() {

    private lateinit var viewModel: LabSelectionViewModel

    private lateinit var binding: LabSelectionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.lab_selection_fragment,
            container,
            false
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.explicacion.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        val args = LabSelectionFragmentArgs.fromBundle(requireArguments())

        viewModel = ViewModelProvider(this).get(LabSelectionViewModel::class.java)
        viewModel.userId = args.userId

        return binding.root
    }

    fun launchTorsionLab() {
        // TODO: implement torsion button handler
    }

    fun launchPandeoLab() {
        // TODO: implement pandeo button handler
    }

    fun launchTraccionLab() {
        Toast.makeText(context, R.string.error_practica_no_implementada, Toast.LENGTH_SHORT).show()
    }

    fun launchFlexionLab() {
        Toast.makeText(context, R.string.error_practica_no_implementada, Toast.LENGTH_SHORT).show()
    }
}