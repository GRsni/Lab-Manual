package uca.esi.manual.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import timber.log.Timber
import uca.esi.manual.R
import uca.esi.manual.databinding.LoginFragmentBinding
import uca.esi.manual.models.UserListResponse

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.getUserListResponse().observe(viewLifecycleOwner, {
            print(it)
        })

        binding.loginViewModel = viewModel

        binding.buttonLoginAnon.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLabSelectionFragmentAnon())
        }

        return binding.root
    }

    private fun print(response: UserListResponse) {
        response.users?.let { users ->
            users.forEach { user ->
                Timber.i("%s: %s", user.username, user.password)
            }
        }
    }

}