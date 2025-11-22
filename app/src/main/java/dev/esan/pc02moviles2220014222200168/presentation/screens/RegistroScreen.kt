package dev.esan.pc02moviles2220014222200168.presentation.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class Team(
    val nombre: String = "",
    val fundacion: Int = 0,
    val titulos: Int = 0,
    val imagenUrl: String = ""
)

@Composable
fun RegistroScreen(navController: NavController) {

    val nombre = remember { mutableStateOf("") }
    val fundacion = remember { mutableStateOf("") }
    val titulos = remember { mutableStateOf("") }
    val imagenUrl = remember { mutableStateOf("") }

    val db = Firebase.firestore

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Registro Liga 1", fontSize = 28.sp)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre del equipo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fundacion.value,
            onValueChange = { fundacion.value = it },
            label = { Text("Año de fundación") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = titulos.value,
            onValueChange = { titulos.value = it },
            label = { Text("Número de títulos ganados") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imagenUrl.value,
            onValueChange = { imagenUrl.value = it },
            label = { Text("URL de la imagen del equipo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                val nuevoTeam = Team(
                    nombre = nombre.value,
                    fundacion = fundacion.value.toIntOrNull() ?: 0,
                    titulos = titulos.value.toIntOrNull() ?: 0,
                    imagenUrl = imagenUrl.value
                )

                db.collection("equipos_liga1")
                    .add(nuevoTeam)
                    .addOnSuccessListener {
                        navController.navigate("listado")
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
