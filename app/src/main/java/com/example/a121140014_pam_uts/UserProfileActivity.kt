package com.example.a121140014_pam_uts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a121140014_pam_uts.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, UserProfileFragment())
            .commit()
    }
}