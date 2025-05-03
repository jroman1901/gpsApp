package com.example.gpsapp.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.gpsapp.LocationService
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@Composable
fun LocationScreen(locationService: LocationService) {
    var locationText by remember { mutableStateOf("Ubicación no disponible") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = locationText)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            locationService.requestLocationUpdates  { location ->
                location?.let {
                    locationText = "Lat: ${it.latitude}, Lon: ${it.longitude}"
                    Log.d("GPS_APP", "Ubicación obtenida: $it.latitude, $it.longitude")
                } ?: run {
                    locationText = "No se pudo obtener la ubicación"
                }
            }
        }) {
            Text("Obtener ubicación")
        }
    }
}
