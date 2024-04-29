package com.navio.socialmedia

import android.health.connect.datatypes.units.Length
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecyclerViewScreen() {
    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(64.dp)
    ) {
        items(getApplications()) { application ->
            ItemApplication(application)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecyclerStickyViewScreen() {
    val context = LocalContext.current
    val applications: Map<Int, List<Application>> = getApplications().groupBy { application ->
        application.year
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(64.dp)
    ) {
        applications.forEach { (year, applicationsOrdered) ->

            stickyHeader {
                Text(text = year.toString(), color = Color.White)
            }

            items(applicationsOrdered) { application ->
                ItemApplication(application)
            }
        }
    }
}

@Composable
fun RecyclerGridViewScreen() {
    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
        columns = GridCells.Adaptive(100.dp),
        content = {
            items(getApplications()) { application ->
                ItemApplication(application)
            }
        },
//        contentPadding = PaddingValues(16.dp)//Padding on borders
    )
}


@Composable
fun ItemApplication(application: Application) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(4.dp, Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(
                        context,
                        "${application.name} version ${application.version}",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
    ) {
        Column() {
            Image(
                painter = painterResource(id = application.icon),
                contentDescription = "Application logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                //Name
                Text(
                    text = application.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.Bottom)
                )
                //Version
                Text(
                    text = application.version,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.Bottom)
                )
            }

            //Description
            Text(
                text = application.description,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable()
fun RecyclerViewPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF15202B)
    ) {
//        RecyclerViewScreen()
        RecyclerStickyViewScreen()
//        RecyclerGridViewScreen()
    }
}

private fun getApplications(): List<Application> {
    return listOf(
        Application("PCE", "3.0", "Point cloud data", 2018, R.drawable.icon_pce),
        Application("GPS", "2.7", "Land surveying with GNSS", 2016, R.drawable.icon_tcp),
        Application("MDT", "9.4", "Surveying and civil engineering", 2007, R.drawable.icon_mdt),
        Application("Scancyr", "4.8", "Tunel", 2007, R.drawable.icon_scancyr)
    )
}

data class Application(
    val name: String,
    val version: String,
    val description: String,
    val year: Int,
    val icon: Int
)