package id.co.booksapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.R
import id.co.booksapp.databinding.ActivityMainBinding
import id.co.booksapp.datastore.UserDataStore
import id.co.booksapp.ui.login.LoginActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController

    @Inject
    lateinit var userDataStore: UserDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val toolbar = binding.layoutDashboard.toolbar
        val navView: NavigationView = binding.navigationView
        navController = findNavController(R.id.nav_host_fragment_content_home)

        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        binding.layoutDashboard.navigationBar.setOnClickListener {
            drawerLayout.openDrawer(Gravity.RIGHT)
        }



//        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close)
//        drawerLayout.setDrawerListener(toggle);

        navController
            .addOnDestinationChangedListener{_, destination, _ ->
                when(destination.id){
                    R.id.nav_home,
                    R.id.nav_gallery,
                    R.id.nav_slideshow -> {
                        binding.layoutDashboard.ivBack.visibility = View.GONE
                        binding.layoutDashboard.ivUser.visibility = View.VISIBLE
                        binding.drawerLayout.closeDrawer(Gravity.RIGHT)
                    }
                    else -> {
                        binding.drawerLayout.closeDrawer(Gravity.RIGHT)
                        binding.layoutDashboard.ivUser.visibility = View.GONE
                        binding.layoutDashboard.ivBack.visibility = View.GONE
                    }
                }
            }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home ->{
                navController.navigate(R.id.nav_home)
            }
            R.id.profileFragment ->{
                navController.navigate(R.id.profileFragment)
            }
            R.id.historyFragment ->{
                navController.navigate(R.id.historyFragment)
            }
            R.id.logout ->{
                logoutProses()
            }

        }

        if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.RIGHT)
        }
        return true
    }

    private fun logoutProses() {
        GlobalScope.launch {
            userDataStore.storeStatusLogin(false)
            userDataStore.storeUser("")
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

}