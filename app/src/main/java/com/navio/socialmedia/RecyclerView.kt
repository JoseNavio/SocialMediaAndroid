package com.navio.socialmedia

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SuperHeroView() {
    val messages = listOf("Hello", "World", "This", "is", "Compose")
    LazyColumn {
        items(messages) { message ->
            MessageRow(message)
        }
    }
}

@Composable
fun ProgramItem(program: Program) {
    Text(text = program.name)
}


fun getPrograms(): List<Program> {
    return listOf(
        Program("MDT", "MDT", "Aplitop", R.drawable.icono_mdt),
        Program("TcpGps", "TCP", "Aplitop", R.drawable.icono_tcp),
        Program("Point Cloude", "PCE", "Aplitop", R.drawable.icono_pce)
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable()
fun RecyclerPreview() {
    SuperHeroView()
}

data class Program(
    val name: String,
    val fullName: String,
    val enterprise: String,
    val iconResource: Int
)


