package com.areeb.widget.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.areeb.widget.R
import com.areeb.widget.ui.Widget.Companion.GLANCE_KEY
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val actionCallBack = ActionParameters.Key<String>(GLANCE_KEY)

class Widget : GlanceAppWidget() {
    companion object {
        const val GLANCE_KEY = "areeb"
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Content()
        }
    }
}

@Composable
private fun Content() {
    var currentTime by remember {
        mutableStateOf("")
    }

    Column(
        modifier = GlanceModifier.fillMaxSize().background(R.color.white),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
    ) {
        LaunchedEffect(true) {
            while (true) {
                currentTime = getCurrentTime()
                delay(1000)
            }
        }
        Text(
            text = currentTime,
            modifier = GlanceModifier.fillMaxWidth().fillMaxWidth(),
            style = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
        )
    }
}

private fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}
