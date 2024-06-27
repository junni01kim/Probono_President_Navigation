package com.example.probono_president_navigation

import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.probono_president_navigation.ui.theme.Probono_President_NavigationTheme
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberFusedLocationSource
import androidx.compose.runtime.setValue
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

class MainActivity : ComponentActivity() {
    private val markerIcon = OverlayImage.fromResource(com.naver.maps.map.R.drawable.navermap_location_overlay_icon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Probono_President_NavigationTheme {
                MainMap()
            }
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MainMap() {
    var searchText by remember { mutableStateOf("") }
    lateinit var naverMap:NaverMap

    Box(Modifier.fillMaxSize()) {
        val locationTrackingMode = LocationTrackingMode.None
        val isCompassEnabled = true
        val cameraPositionState = rememberCameraPositionState()

        val (selectedOption, onOptionSelected) = remember { mutableStateOf(2) }

        NaverMap(
            cameraPositionState = cameraPositionState,
            locationSource = rememberFusedLocationSource(
                isCompassEnabled = isCompassEnabled,
            ),
            properties = MapProperties(
                locationTrackingMode = locationTrackingMode,
            ),
            uiSettings = MapUiSettings(
                isLocationButtonEnabled = true,
                isCompassEnabled = isCompassEnabled,
            ),
            onOptionChange = {
                cameraPositionState.locationTrackingMode?.let {
                    onOptionSelected(it.ordinal)
                }
            },
        )


        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 35.dp, start = 10.dp, end = 10.dp) ,
            // 오류 : 정렬이 버튼이 우선적으로 된다.
            horizontalArrangement = Arrangement.End
        ) {
            // 검색 버튼
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(end = 5.dp)
                    .width(65.dp).height(55.dp),
                onClick = {
                    Log.d("explain", "clicked")
                    // Todo("Navigation 함수")
                }
            ) {
                Icon(Icons.Default.Search,
                    contentDescription = "출발 목적지 인자를 이용한 검색",
                    tint = Color.White
                )
            }

            // 검색 창
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(20.dp),
            )
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Preview(showBackground = true)
@Composable
fun NaverMapPreview() {
    Probono_President_NavigationTheme {
        NaverMap()
    }
}