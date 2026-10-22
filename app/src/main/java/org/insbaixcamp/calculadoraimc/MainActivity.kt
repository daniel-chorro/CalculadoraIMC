package org.insbaixcamp.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// PALETA D'ALT DISSENY FIT-TECH
val FonsGradientStart = Color(0xFF0F172A)
val FonsGradientEnd = Color(0xFF1E1B4B)
val VioletaNeon = Color(0xFF8B5CF6)
val BlauElectric = Color(0xFF06B6D4)
val RosaGlow = Color(0xFFEC4899)
val CardDarkGlass = Color(0xFF1E293B).copy(alpha = 0.8f)
val TextBlanc = Color(0xFFF8FAFC)
val TextGrisSuau = Color(0xFF94A3B8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
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

    // Fons amb degradat fosc futurista
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(FonsGradientStart, FonsGradientEnd)
                )
            )
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Capçalera estil Pro Dashboard
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "BODY INDEX PRO",
                        color = BlauElectric,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    )
                    Text(
                        text = "Calculadora d'IMC",
                        color = TextBlanc,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                listOf(VioletaNeon, RosaGlow)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("⚡", fontSize = 20.sp)
                }
            }

            // Entrada del nom amb Disseny Glassmorphism
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, VioletaNeon.copy(alpha = 0.3f), RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = CardDarkGlass),
                shape = RoundedCornerShape(20.dp)
            ) {
                OutlinedTextField(
                    value = nomUsuari,
                    onValueChange = { nomUsuari = it },
                    label = { Text("Nom de l'usuari", color = TextGrisSuau) },
                    placeholder = { Text("Escriviu el teu nom...", color = TextGrisSuau.copy(alpha = 0.5f)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BlauElectric,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = TextBlanc,
                        unfocusedTextColor = TextBlanc
                    )
                )
            }

            // Selector de pes amb Ring de Control i Botons Neomòrfics
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BlauElectric.copy(alpha = 0.2f), RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = CardDarkGlass),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "PES CORPORAL",
                        color = TextGrisSuau,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Anell de pes centralitzat
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        VioletaNeon.copy(alpha = 0.2f),
                                        Color.Transparent
                                    )
                                )
                            )
                            .border(3.dp, VioletaNeon, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$pesKg",
                                fontSize = 38.sp,
                                fontWeight = FontWeight.Black,
                                color = TextBlanc
                            )
                            Text(
                                text = "KG",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = BlauElectric
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botons d'ajust ràpid de pes
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { if (pesKg > 15) pesKg -= 5 },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A)),
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("-5", color = RosaGlow, fontWeight = FontWeight.Bold) }

                        Button(
                            onClick = { if (pesKg > 10) pesKg-- },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A)),
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("-1", color = TextBlanc, fontWeight = FontWeight.Bold) }

                        Button(
                            onClick = { if (pesKg < 250) pesKg++ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A)),
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("+1", color = TextBlanc, fontWeight = FontWeight.Bold) }

                        Button(
                            onClick = { if (pesKg < 250) pesKg += 5 },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A)),
                            shape = RoundedCornerShape(12.dp)
                        ) { Text("+5", color = BlauElectric, fontWeight = FontWeight.Bold) }
                    }
                }
            }

            // Selector d'alçada amb disseny de barra Neó
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, RosaGlow.copy(alpha = 0.2f), RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = CardDarkGlass),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ALÇADA",
                            color = TextGrisSuau,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )
                        Text(
                            text = "${alcadaCm.toInt()} cm",
                            color = RosaGlow,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Slider(
                        value = alcadaCm,
                        onValueChange = { alcadaCm = it },
                        valueRange = 80f..230f,
                        colors = SliderDefaults.colors(
                            thumbColor = RosaGlow,
                            activeTrackColor = VioletaNeon,
                            inactiveTrackColor = Color(0xFF0F172A)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Botó per calcular l'IMC (Gradient i Ombra Glow)
            Button(
                onClick = {
                    if (nomUsuari.isBlank()) {
                        errorText = "⚠️ Si us plau, escriu el teu nom per continuar."
                        imcCalculat = null
                    } else {
                        errorText = ""
                        val alcadaMetres = alcadaCm / 100.0
                        imcCalculat = pesKg / (alcadaMetres * alcadaMetres)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(12.dp, RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(VioletaNeon, RosaGlow)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CALCULAR IMC",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 1.sp
                    )
                }
            }

            // Mostrar missatge d'error si el nom està buit
            if (errorText.isNotEmpty()) {
                Text(
                    text = errorText,
                    color = RosaGlow,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            // Targeta de resultat condicional (Panell VIP de Salut)
            if (imcCalculat != null) {
                val imc = imcCalculat!!
                val (categoria, recomanacio, colorAccent) = when {
                    imc < 18.5 -> Triple("SOTA PES", "Es recomana augmentar la ingesta calòrica.", BlauElectric)
                    imc < 25.0 -> Triple("PES NORMAL", "Mantingues els teus hàbits saludables!", Color(0xFF10B981))
                    imc < 30.0 -> Triple("SOBREPÈS", "Es recomana activitat física moderada.", Color(0xFFF59E0B))
                    else -> Triple("OBESITAT", "Es recomana consultar amb un professional de la salut.", RosaGlow)
                }

                val animatedProgress by animateFloatAsState(
                    targetValue = (imc / 40.0).toFloat().coerceIn(0f, 1f),
                    label = "IMC Progress"
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, colorAccent, RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(containerColor = CardDarkGlass),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "INFORME DE SALUT // $nomUsuari",
                            color = TextGrisSuau,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "%.2f".format(imc),
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Black,
                                    color = TextBlanc
                                )
                                Text(
                                    text = "Índex IMC",
                                    fontSize = 12.sp,
                                    color = TextGrisSuau
                                )
                            }

                            // Badge de diagnòstic vibrant
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(colorAccent.copy(alpha = 0.2f))
                                    .border(1.dp, colorAccent, RoundedCornerShape(12.dp))
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = categoria,
                                    color = colorAccent,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 12.sp,
                                    letterSpacing = 1.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Barra de nivell d'IMC avançada
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF0F172A))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(animatedProgress)
                                    .height(10.dp)
                                    .clip(CircleShape)
                                    .background(colorAccent)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = recomanacio,
                            fontSize = 13.sp,
                            color = TextGrisSuau
                        )
                    }
                }
            }
        }
    }
}