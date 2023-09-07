package app.sample.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import app.sample.BR
import app.sample.R
import app.sample.base.BaseActivity
import app.sample.databinding.ActivityLoginBinding
import app.sample.ui.home.HomeActivity
import app.sample.utils.SharedPref
import app.sample.utils.errorToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding

    //field injection
    @Inject
    lateinit var sharedPref: SharedPref

    private val loginViewModel: LoginViewModel by viewModels()

    lateinit var eventJobs: Job
    var deviceToken = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.setVariable(BR.login, loginViewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setEventListener()
        binding.btLogin.setOnClickListener {

           //Blocked API Call
            /*showLoading()
            loginViewModel.login(
                binding.etUserNameValue.text.toString(),
                binding.etPasswordValue.text.toString(),
                ""
            )*/

            goToDashBoard()
        }


    }


    private fun setEventListener() {

        eventJobs = lifecycleScope.launchWhenStarted {
            loginViewModel.loginEvents.collect {
                when (it) {
                    is LoginViewModel.LoginEvents.ShowErrorMessage -> {
                        errorToast(it.msg)
                    }
                    is LoginViewModel.LoginEvents.LoginComplete -> {
                        sharedPref.saveData(SharedPref.IsLogin, true)
                        goToDashBoard()
                    }
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            loginViewModel.loginStates.collect {
                when (it) {
                    is LoginViewModel.LoginStates.Loading -> {
                        if (it.isLoading) {
                            showLoading()
                        } else {
                            hideLoading()
                        }
                    }
                }
            }
        }

    }

    private fun goToDashBoard() {
        startLeftAnimatedActivity(Intent(this, HomeActivity::class.java), true)
    }

}