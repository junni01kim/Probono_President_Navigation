package com.example.probono_president_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.probono_president_navigation.ui.theme.Probono_President_NavigationTheme
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalNaverMapApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Probono_President_NavigationTheme {
                val seoul = LatLng(37.532600, 127.024612)
                val cameraPositionState: CameraPositionState = rememberCameraPositionState {
                    // 카메라 초기 위치를 설정합니다.
                    position = CameraPosition(seoul, 11.0)
                }

                Box(Modifier.fillMaxSize()) {
                    NaverMap(
                        cameraPositionState = cameraPositionState,
                        modifier = Modifier.fillMaxSize(),
                        uiSettings = MapUiSettings(isLocationButtonEnabled = true),
                        locationSource = rememberFusedLocationSource(isCompassEnabled = true),
                        properties = MapProperties(
                            locationTrackingMode = LocationTrackingMode.Face,
                        )
                    )
                    Button(onClick = {
                        // 카메라를 새로운 줌 레벨로 이동합니다.
                        cameraPositionState.move(CameraUpdate.zoomIn())
                    }) {
                        Text(text = "Zoom In")
                    }
                }
            }
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