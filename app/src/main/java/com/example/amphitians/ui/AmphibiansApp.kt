@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.amphitians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphitians.ui.screens.HomeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(scrollBehavior = scrollBehavior)
        }
        ){
        val amphibiansViewModel : AmphibiansViewModel =
            viewModel(factory = AmphibiansViewModel.Factory)
        Surface (modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            HomeScreen(
                uiState = amphibiansViewModel.uiState,
                retryAction =  amphibiansViewModel::getAmphibians ,
                onclick = { },
                modifier = Modifier.fillMaxSize(),
                contentPadding = it)

        }
    }
}

@Composable
fun TopBar(scrollBehavior: TopAppBarScrollBehavior, modifier : Modifier = Modifier) {
CenterAlignedTopAppBar(title = {
    Text(text = "Amphibians",
        style = MaterialTheme.typography.headlineMedium
    )
},
    scrollBehavior = scrollBehavior,
    modifier = modifier
)}