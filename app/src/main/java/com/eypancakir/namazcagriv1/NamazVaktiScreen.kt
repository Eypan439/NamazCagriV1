package com.eypancakir.namazcagriv1

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.eypancakir.namazcagriv1.viewmodel.NamazVaktiViewModel
import java.text.DateFormat
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NamazVaktiScreen(viewModel: NamazVaktiViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val namazVakti by viewModel.namazVakti.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (namazVakti != null) {
            namazVakti?.times?.toList()?.forEach { (dateString, timings) ->
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                val date = dateFormatter.parse(dateString)
                val dayOfWeek = android.text.format.DateFormat.format("EEEE", date).toString()

                if(dateString == LocalDate.now().toString()){

                    Text(
                        text = "$dayOfWeek - $dateString",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    timings.forEach { timing ->
                        Text(
                            text = timing,
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                        color = MaterialTheme.colors.onBackground
                    )

                }

            }
        } else {
            CircularProgressIndicator()
        }
    }
}
