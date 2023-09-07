package app.sample.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import app.sample.R
import app.sample.base.BaseActivity
import app.sample.databinding.ActivityHomeBinding
import app.sample.utils.SharedPref
import app.sample.utils.warningToast
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    //field injection
    @Inject
    lateinit var sharedPref: SharedPref
    private lateinit var binding: ActivityHomeBinding
    var navHostFragment: NavHostFragment? = null
    var doubleBackToExitPressedOnce = false
    private lateinit var appBarConfiguration: AppBarConfiguration

    var status = false


    override fun onBackPressed() {
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
        when (backStackEntryCount) {
            0 -> {
                minimizeApp()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.appBarNav.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_nav)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.myGallery,
                R.id.mySettings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        init()
        var versionName = this.applicationContext.packageManager.getPackageInfo(
            this.applicationContext.packageName,
            0
        ).versionName

        binding.tvAppVersionName.text =
            resources.getString(R.string.app_name) + " | V" + versionName


    }


    private fun minimizeApp() {
        if (doubleBackToExitPressedOnce) {
            val startMain = Intent(Intent.ACTION_MAIN)
            startMain.addCategory(Intent.CATEGORY_HOME)
            startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(startMain)
            return
        }

        this.doubleBackToExitPressedOnce = true
        warningToast("Please click BACK again to close the app")

        Handler().postDelayed(
            kotlinx.coroutines.Runnable { doubleBackToExitPressedOnce = false },
            2000
        )

    }

    private fun init() {


        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_nav) as NavHostFragment?
        NavigationUI.setupWithNavController(
            binding.appBarNav.contentNavigation.bottomNavigationView,
            navHostFragment!!.navController
        )

        navHostFragment?.navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.label) {
                resources.getString(R.string.menu_home) -> {
                    showBottomNavigation()
                }
                resources.getString(R.string.menu_settings) -> {
                    showBottomNavigation()
                }
                resources.getString(R.string.menu_gallery) -> {
                    showBottomNavigation()
                }

                else -> {
                    hideBottomNavigation()
                }
            }
        }

    }

    private fun showBottomNavigation() {
        binding.appBarNav.toolbar.visibility = View.VISIBLE
        binding.appBarNav.contentNavigation.bottomNavigationView.visibility = View.VISIBLE

    }

    private fun hideBottomNavigation() {
        binding.appBarNav.contentNavigation.bottomNavigationView.visibility = View.GONE
        binding.appBarNav.toolbar.visibility = View.GONE
    }

    /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
         // Inflate the menu; this adds items to the action bar if it is present.
         menuInflater.inflate(R.menu.nav, menu)
         return true
     }*/

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_nav)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}