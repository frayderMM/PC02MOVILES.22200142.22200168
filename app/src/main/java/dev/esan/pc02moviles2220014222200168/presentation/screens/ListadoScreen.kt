package dev.esan.pc02moviles2220014222200168.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


@Composable
fun ListadoScreen(navController: NavController) {

    val equipos = remember { mutableStateOf(listOf<Team>()) }
    val db = Firebase.firestore

    LaunchedEffect(Unit) {
        db.collection("equipos_liga1")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    equipos.value = snapshot.toObjects(Team::class.java)
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Equipos", fontSize = 28.sp)

        Spacer(Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            items(equipos.value) { equipo ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(equipo.imagenUrl),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )

                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(equipo.nombre, fontSize = 20.sp)
                        Text(equipo.fundacion.toString(), fontSize = 14.sp)
                    }

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = equipo.titulos.toString(),
                        fontSize = 28.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("registro")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nuevo Registro")
        }
    }
}
