package com.example.amphitians.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibians.ui.theme.AmphibiansTheme
import com.example.amphitians.model.Amphibian
import com.example.amphitians.ui.UiState

@Composable
fun HomeScreen(
    uiState: UiState,
    retryAction: () -> Unit,
    onclick: (Amphibian) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    ) {
  when (uiState) {
    is UiState.Success -> {
        AmphibiansList(amphibians = uiState.amphibians, modifier = modifier, onclick = onclick, contentPadding = contentPadding)
    }
    is UiState.Error -> {
    ErrorScreen(retryAction , modifier = modifier)
    }
    is UiState.Loading -> {
        LoadingScreen(modifier = modifier)
    }}

}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({})
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun ListScreenPreview() {
//    AmphibiansTheme {
//        AmphibiansList(
//            amphibians = listOf(
//                Amphibian(
//                    name = "name",
//                    type = "type",
//                    description = "description",
//                    img_src = "imageUrl"
//                )
//            )
//        )
//    }
//}