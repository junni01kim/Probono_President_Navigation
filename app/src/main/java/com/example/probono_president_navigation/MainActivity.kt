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
                NaverMap(
                    // 카메라 초기 위치 설정
                    cameraPositionState = cameraPositionState,

                    // 화면 내 지도 크기 설정
                    modifier = Modifier.fillMaxSize(),

                    // 화면 제스처 세팅(현위치 버튼이 생성)
                    uiSettings = MapUiSettings(isLocationButtonEnabled = true),

                    // 위치 관련 세팅
                    locationSource = rememberFusedLocationSource(isCompassEnabled = true),

                    // 위치 추적 모드
                    properties = MapProperties(
                        locationTrackingMode = LocationTrackingMode.Follow
                    )
                )
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