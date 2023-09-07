package app.sample.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.sample.R
import app.sample.base.BaseFragment
import app.sample.databinding.FragmentHomeBinding
import app.sample.response.profile.Data
import app.sample.utils.SharedPref
import app.sample.utils.errorToast
import app.sample.utils.successToast
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getProfileDetails(sharedPref.getData(SharedPref.ACCESS_TOKEN, "").toString())

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeState.collect {
                when (it) {
                    is HomeViewModel.HomeStates.Loading -> {
                        if (it.isLoading) {
                            showLoading()
                        } else {
                            hideLoading()
                        }
                    }
                    is HomeViewModel.HomeStates.Success -> {
                        hideLoading()
                        setData(it.data)
                    }
                    is HomeViewModel.HomeStates.Failure -> {
                        hideLoading()

                        errorToast(it.message)
                        if (it.message == "Authentication failed") {
                            forceLogout(sharedPref)
                        } else if (it.message == "Authentication required") {
                            forceLogout(sharedPref)
                        }
                    }
                    is HomeViewModel.HomeStates.NoInternet -> {
                        hideLoading()

                        showNoInternetAlert()
                    }
                }
            }
        }

    }

    private fun setData(data: Data?) {
        data?.let {
            //Do your stuff here
            Log.v("Profile", "Data -- ${Gson().toJson(data)}")
            successToast(resources.getString(R.string.welcome_message) + data.firstName)


        }


    }

}