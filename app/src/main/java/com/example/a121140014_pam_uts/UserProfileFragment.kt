package com.example.a121140014_pam_uts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a121140014_pam_uts.databinding.FragmentUserProfileBinding
import com.google.firebase.auth.FirebaseAuth

class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        // Ambil data pengguna yang sedang login
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Atur informasi pengguna di layout
            binding.userInitialsImageView.text = currentUser.displayName?.take(1)
            binding.userNameTextView.text = currentUser.displayName
            binding.userGitHubTextView.text = "GitHub: ${currentUser.displayName}"
            binding.userEmailTextView.text = currentUser.email

            // Tambahkan listener untuk tombol logout
            binding.logoutButton.setOnClickListener {
                auth.signOut()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}