package com.example.akhilglobeandmail.presentation.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.akhilglobeandmail.presentation.theme.AkhilGlobeAndMailTheme
import com.example.akhilglobeandmail.presentation.viewmodel.GlobeAndMailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<GlobeAndMailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            AkhilGlobeAndMailTheme {
                val state = viewModel.globeAndMailPosts.collectAsStateWithLifecycle().value
                HomeScreen(
                    state = state
                )
            }
        }
    }
}