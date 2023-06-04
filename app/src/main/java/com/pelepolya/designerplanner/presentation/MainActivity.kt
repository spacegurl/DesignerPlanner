package com.pelepolya.designerplanner.presentation

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.pelepolya.designerplanner.R
import com.pelepolya.designerplanner.data.db.models.UserRoles
import com.pelepolya.designerplanner.presentation.fragments.auth.SignInFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check session
        val sharedPrefRead: SharedPreferences = getPreferences(MODE_PRIVATE)
        val logined = sharedPrefRead.getString(SignInFragment.AUTH_SESSION, "")
        val role = sharedPrefRead.getString(SignInFragment.AUTH_ROLE, UserRoles.NONE.name)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragments_container) as NavHostFragment
        val graph = navHost.navController
            .navInflater.inflate(R.navigation.main_graph)
        if (logined != "") {
            if (role == UserRoles.USER.name) {
                graph.setStartDestination(R.id.menuFragment)
            } else {
                graph.setStartDestination(R.id.adminMenuFragment2)
            }
        } else {
            graph.setStartDestination(R.id.startFrameFragment2)
        }
        navHost.navController.graph = graph

    }
}