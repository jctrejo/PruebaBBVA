package com.upax.bbvaprueba1

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.upax.bbvaprueba1.databinding.ActivityMainBinding
import com.upax.bbvaprueba1.ui.home.BHomeFragment
import com.upax.bbvaprueba1.ui.SecondFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigateBottom.setupWithNavController(navController)

/*
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
*/
        init()
    }

    private fun init() {
        setupNavController()
    }

    private fun setupNavController() {
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fragmentManager: FragmentManager,
                fragment: Fragment,
                view: View,
                savedInstanceState: Bundle?
            ) {
                TransitionManager.beginDelayedTransition(
                    binding.root as ViewGroup?,
                    Slide(Gravity.BOTTOM).excludeTarget(R.id.nav_host_fragment_content_main, true)
                )
                when (fragment) {
                    is BHomeFragment -> {  }
                    is SecondFragment -> { }
                }
            }
        }, true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.HomeFragment -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
