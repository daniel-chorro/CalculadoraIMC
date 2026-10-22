package org.insbaixcamp.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                CalculadoraIMCSimple()
            }
        }
    }
}

@Composable
fun CalculadoraIMCSimple() {
    // ESTATS DE LA UI
    var nomUsuari by remember { mutableStateOf("") }
    var pesKg by remember { mutableIntStateOf(68) }
    var alcadaCm by remember { mutableFloatStateOf(175f) }

    var imcCalculat by remember { mutableStateOf<Double?>(null) }
    var errorText by remember { mutableStateOf("") }

    // ESTRUCTURA PRINCIPAL
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(text = "Càlcul d'Índex de Massa Corporal", style = MaterialTheme.typography.titleLarge)

        // Entrada del nom
        OutlinedTextField(
            value = nomUsuari,
            onValueChange = { nomUsuari = it },
            label = { Text("Nom de l'usuari") },
            placeholder = { Text("Introdueix el teu nom") },
            modifier = Modifier.fillMaxWidth()
        )

        // Selector de pes amb botons
        Column {
            Text(text = "Pes actual: $pesKg kg", fontSize = 16.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Button(onClick = { if (pesKg > 10) pesKg-- }) { Text("-1 kg") }
                Button(onClick = { if (pesKg > 15) pesKg -= 5 }) { Text("-5") }
                Button(onClick = { if (pesKg < 250) pesKg += 5 }) { Text("+5") }
                Button(onClick = { if (pesKg < 250) pesKg++ }) { Text("+1 kg") }
            }
        }

        // Selector d'alçada amb barra lliscant
        Column {
            Text(text = "Alçada: ${alcadaCm.toInt()} cm", fontSize = 16.sp)
            Slider(
                value = alcadaCm,
                onValueChange = { alcadaCm = it },
                valueRange = 80f..230f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Botó per calcular l'IMC
        Button(
            onClick = {
                if (nomUsuari.isBlank()) {
                    errorText = "Si us plau, escriu el teu nom per continuar."
                    imcCalculat = null
                } else {
                    errorText = ""
                    val alcadaMetres = alcadaCm / 100.0
                    imcCalculat = pesKg / (alcadaMetres * alcadaMetres)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular IMC")
        }

        // Mostrar missatge d'error si el nom està buit
        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error
            )
        }

        // Targeta de resultat condicional
        if (imcCalculat != null) {
            val imc = imcCalculat!!
            val (categoria, recomanacio) = when {
                imc < 18.5 -> Pair("Sota pes", "Es recomana augmentar la ingesta calòrica.")
                imc < 25.0 -> Pair("Pes normal", "Mantingues els teus hàbits saludables!")
                imc < 30.0 -> Pair("Sobrepès", "Es recomana activitat física moderada.")
                else -> Pair("Obesitat", "Es recomana consultar amb un professional de la salut.")
            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Resultat per a $nomUsuari:", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Valor IMC: %.2f".format(imc), fontSize = 18.sp)
                    Text(text = "Diagnòstic: $categoria")
                    Text(text = recomanacio, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}