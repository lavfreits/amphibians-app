package com.example.amphitians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphitians.R
import com.example.amphitians.model.Amphibian

@Composable
fun AmphibianListItem (
    amphibian: Amphibian, onclick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onclick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Green.copy(alpha = 0.2f)),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),


        ) {
        Column (Modifier.padding(16.dp)){
            Text(text = "${amphibian.name} (${amphibian.type})",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(4.dp))
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.img_src)
//                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = amphibian.description,
                contentScale = ContentScale.FillWidth,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = amphibian.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify)
        }
    }
}

@Composable
fun AmphibiansList(
    amphibians: List<Amphibian>,
    onclick: (Amphibian) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = amphibians,
            key = { amphibian ->
                amphibian.name
            }
        ) { amphibian ->
            AmphibianListItem(amphibian = amphibian, onclick = { onclick(amphibian) }, modifier = Modifier.fillMaxSize())
        }
//        items(amphibians) { amphibian ->
//            AmphibianListItem(amphibian = amphibian)
//        }
    }
}