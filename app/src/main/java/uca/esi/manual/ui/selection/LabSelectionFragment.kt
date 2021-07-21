package uca.esi.manual.ui.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import uca.esi.manual.R
import uca.esi.manual.databinding.LabSelectionFragmentBinding

class LabSelectionFragment : Fragment() {

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

        return binding.root
    }
}