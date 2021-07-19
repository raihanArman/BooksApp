package id.co.booksapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import id.co.booksapp.R
import id.co.booksapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val toolbar = binding.layoutDashboard.toolbar
        val navView: NavigationView = binding.navigationView
        val navController = findNavController(R.id.nav_host_fragment_content_home)

        navView.setupWithNavController(navController)

        binding.layoutDashboard.navigationBar.setOnClickListener {
            drawerLayout.openDrawer(Gravity.RIGHT)
        }


        Glide.with(this)
            .load("https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90")
            .into(binding.layoutDashboard.ivUser)

//        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close)
//        drawerLayout.setDrawerListener(toggle);

        navController
            .addOnDestinationChangedListener{_, destination, _ ->
                when(destination.id){
                    R.id.nav_home,
                    R.id.nav_gallery,
                    R.id.nav_slideshow ->
                        binding.drawerLayout.closeDrawer(Gravity.RIGHT)
                    else ->
                        binding.drawerLayout.closeDrawer(Gravity.RIGHT)
                }
            }


    }

}