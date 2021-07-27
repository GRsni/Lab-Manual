package uca.esi.manual.screens.chest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import uca.esi.manual.R
import uca.esi.manual.databinding.ChestFragmentBinding
import uca.esi.manual.models.labs.BaseLab

class ChestFragment : Fragment() {

    private lateinit var viewModel: ChestViewModel

    private lateinit var viewModelFactory: ChestViewModelFactory

    private lateinit var binding: ChestFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.chest_fragment,
            container,
            false
        )

        viewModelFactory = ChestViewModelFactory(
            ChestFragmentArgs.fromBundle(requireArguments()).lab
        )

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ChestViewModel::class.java)

        binding.chestViewModel = viewModel
        binding.lifecycleOwner = this

        binding.textoBaul.text = viewModel.materialsText.resolve(requireContext())
        binding.imagenBaul.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                viewModel.chestImage
            )
        )

        viewModel.eventButtonPressed.observe(viewLifecycleOwner, {
            if (it) {
                launchMaterialSelector()
                viewModel.onButtonPressedComplete()
            }
        })

        return binding.root
    }

    private fun launchMaterialSelector() {
        when (viewModel.lab.value!!.labType) {
            BaseLab.LabType.PANDEO -> {
                NavHostFragment.findNavController(this).navigate(
                    ChestFragmentDirections.actionChestFragmentToMaterialsPandeo(
                        viewModel.lab.value!!
                    )
                )
            }
            BaseLab.LabType.TORSION -> {
                Toast.makeText(
                    activity,
                    "Lanzando materiales torsion",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Toast.makeText(
                activity,
                "Practica no implementada",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}