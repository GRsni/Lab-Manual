package uca.esi.manual.screens.chest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uca.esi.manual.R

class ChestFragment : Fragment() {

    private lateinit var viewModel: ChestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chest_fragment, container, false)
    }
}