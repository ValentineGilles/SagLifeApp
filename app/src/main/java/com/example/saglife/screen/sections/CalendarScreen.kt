package com.example.saglife.screen.sections

import androidx.compose.foundation.background
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saglife.Event
import com.example.saglife.R
import com.example.saglife.models.EventItem
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.Date

@Composable
fun CalendarScreen(events : List<EventItem>) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(events) { event ->
            EventComposant(event = event)
        }
    }
}

@Composable
fun EventComposant(event : EventItem){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp), onClick = {
            val intent = Intent(this, Event::class.java)
            context.startActivity(intent)}) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ){
            Image(
            painter = painterResource(id = R.drawable.event),
            contentDescription = "null", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth()
        )
            ElevatedCard (modifier = Modifier.fillMaxWidth().height(80.dp).padding(8.dp)) {
                Row(verticalAlignment= Alignment.CenterVertically){
                Box(modifier = Modifier.width(80.dp)){
                    Text(text = " 17 Nov.", fontSize = 24.sp, modifier = Modifier.align(
                    Alignment.Center), textAlign= TextAlign.Center)}
                    Spacer(
                        Modifier
                            .width(8.dp)
                    )
                    Divider(
                    modifier = Modifier
                        .fillMaxHeight()  //fill the max height
                        .width(1.dp)
                        .padding(top = 8.dp, bottom = 8.dp)

                )
                    Spacer(
                        Modifier
                            .width(8.dp)
                    )
                Column { Text(text = event.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "17h - 22h")}
            } }

            }

    }

}

@Preview
@Composable
fun Preview() {
    EventComposant(EventItem("Party Barbie", Date(), Date(), "blabla", "photo"))
}
