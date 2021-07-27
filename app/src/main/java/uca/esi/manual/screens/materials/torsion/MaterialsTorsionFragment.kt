package uca.esi.manual.screens.materials.torsion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.MaterialsTorsionFragmentBinding

class MaterialsTorsionFragment : Fragment() {

    private lateinit var viewModel: MaterialsTorsionViewModel
    private lateinit var viewModelFactory: MaterialsTorsionViewModelFactory

    private lateinit var binding: MaterialsTorsionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.materials_torsion_fragment,
            container,
            false
        )

        viewModelFactory = MaterialsTorsionViewModelFactory(
            MaterialsTorsionFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MaterialsTorsionViewModel::class.java)

        binding.buttonAnt.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                MaterialsTorsionFragmentDirections.actionMaterialsTorsionFragmentToChestFragment(
                    viewModel.lab.value!!
                )
            )
        }

        return binding.root
    }

}