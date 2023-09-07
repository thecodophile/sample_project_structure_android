package app.sample.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.sample.R
import app.sample.databinding.ActivityHomeBinding
import app.sample.databinding.ActivityLoginBinding
import app.sample.databinding.ActivitySplashBinding
import app.sample.ui.home.HomeActivity
import app.sample.ui.login.LoginActivity
import app.sample.utils.AppUtil
import app.sample.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    //field injection
    @Inject
    lateinit var sharedPref: SharedPref

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launchWhenStarted {
            delay(2000)
            if (AppUtil.isFirstLaunch(this@SplashActivity)) {
                goToOnBoarding()
            } else {

                if (sharedPref.getData(SharedPref.IsLogin, false) as Boolean) {
                    goToHome()

                } else {
                    gotoOnAuthenticationScreen()
                }

            }


        }
    }

    private fun goToHome() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        finish()

    }

    private fun goToOnBoarding() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

    private fun gotoOnAuthenticationScreen() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }
}