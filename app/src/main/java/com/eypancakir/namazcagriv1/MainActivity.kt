package com.eypancakir.namazcagriv1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.eypancakir.namazcagriv1.model.LocationDetails
import com.eypancakir.namazcagriv1.ui.theme.NamazCagriV1Theme
import com.eypancakir.namazcagriv1.viewmodel.ApplicationViewModel

class MainActivity : ComponentActivity() {

    private val applicationViewModel: ApplicationViewModel by viewModels<ApplicationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val locations by applicationViewModel.getLocationLiveData().observeAsState()

            NamazCagriV1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /* Greeting("Android")*/
                    locations?.let { ShowLocation(it) }
                }
            }

            prepLocationUpdates()
        }
    }

    private fun prepLocationUpdates() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationUpdates()
        } else {
            requestSinglePermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }
    }

    private val requestSinglePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                requestLocationUpdates()
            } else {
                Toast.makeText(this, "GPS Unvailable", Toast.LENGTH_LONG).show()
            }
        }

    private fun requestLocationUpdates() {
        applicationViewModel.startLocationUpdates()
    }
}

@Composable
fun ShowLocation(
    location: LocationDetails
) {
    Column{
        Text(text = "Latitude ${location.latitude}")
        Text(text = "Longitude ${location.longitude}")
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NamazCagriV1Theme {

    }
}