package com.example.bottomnavigation41

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnavigation41.databinding.ActivityMainBinding
import com.example.bottomnavigation41.services.FireBaseNotification
import com.example.bottomnavigation41.utils.Preferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.e("ololo", "onCreate: " + it.result)
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.newTaskFragment,
                R.id.navigation_profile,
                R.id.authFragment
            )
        )


        if (Preferences(applicationContext).isBoardingShowed()) {
            navController.navigate(R.id.navigation_home)
        } else if(auth.currentUser == null){
            navController.navigate(R.id.authFragment)
        } else {
            navController.navigate(R.id.onBoardFragment)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.newTaskFragment || destination.id == R.id.onBoardFragment || destination.id == R.id.authFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
            if (destination.id == R.id.onBoardFragment || destination.id == R.id.authFragment) {
                supportActionBar?.hide()
            }
        }



        navController.navigate(R.id.onBoardFragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }
}