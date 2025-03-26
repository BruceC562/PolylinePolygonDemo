package com.example.polylinedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polylinedemo.ui.theme.PolylineDemoTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PolylineDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PolylineDemo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolylineDemo(modifier: Modifier = Modifier) {
    //List of points for Big Piney Trail based off of All Trails GPX file
    val polylinePoints = listOf(
        LatLng(37.52318, -92.12475),
        LatLng(37.52318, -92.12475),
        LatLng(37.52327, -92.12438),
        LatLng(37.52339, -92.12371),
        LatLng(37.52354, -92.12286),
        LatLng(37.52383, -92.12301),
        LatLng(37.52433, -92.12304),
        LatLng(37.52472, -92.12298),
        LatLng(37.52498, -92.1229),
        LatLng(37.52532, -92.1229),
        LatLng(37.52549, -92.12284),
        LatLng(37.52571, -92.12299),
        LatLng(37.52584, -92.1229),
        LatLng(37.52592, -92.12267),
        LatLng(37.52592, -92.12267),
        LatLng(37.52601, -92.12243),
        LatLng(37.52622, -92.12226),
        LatLng(37.52667, -92.12233),
        LatLng(37.52737, -92.12259),
        LatLng(37.52765, -92.12278),
        LatLng(37.52774, -92.1227),
        LatLng(37.52785, -92.12213),
        LatLng(37.52804, -92.12199),
        LatLng(37.52872, -92.12183),
        LatLng(37.52989, -92.12171),
        LatLng(37.53015, -92.12147),
        LatLng(37.53072, -92.12032),
        LatLng(37.53048, -92.11983),
        LatLng(37.53019, -92.11964),
        LatLng(37.53006, -92.11922),
        LatLng(37.5301, -92.11905),
        LatLng(37.53008, -92.11858),
        LatLng(37.52979, -92.1182),
        LatLng(37.52961, -92.11774),
        LatLng(37.52949, -92.1173),
        LatLng(37.52927, -92.11713),
        LatLng(37.52886, -92.11712),
        LatLng(37.52849, -92.1176),
        LatLng(37.52827, -92.11778),
        LatLng(37.52783, -92.1175),
        LatLng(37.52779, -92.11717),
        LatLng(37.52774, -92.11692),
        LatLng(37.52779, -92.11627),
        LatLng(37.52779, -92.11596),
        LatLng(37.5279, -92.11568),
        LatLng(37.528, -92.11515),
        LatLng(37.52805, -92.11438),
        LatLng(37.52818, -92.11411),
        LatLng(37.52834, -92.11368),
        LatLng(37.52863, -92.11331),
        LatLng(37.52894, -92.11265),
        LatLng(37.5291, -92.11187),
        LatLng(37.5292, -92.1116),
        LatLng(37.52921, -92.11146),
        LatLng(37.52901, -92.11134),
        LatLng(37.52821, -92.1113),
        LatLng(37.52856, -92.11088),
        LatLng(37.5287, -92.11054),
        LatLng(37.5289, -92.11017),
        LatLng(37.52873, -92.10961),
        LatLng(37.52875, -92.10938),
        LatLng(37.52869, -92.10918),
        LatLng(37.5285, -92.10909),
        LatLng(37.52841, -92.10879),
        LatLng(37.52825, -92.1086),
        LatLng(37.52849, -92.10824),
        LatLng(37.52876, -92.10781),
        LatLng(37.52882, -92.1074),
        LatLng(37.52888, -92.10697),
        LatLng(37.52869, -92.10649),
        LatLng(37.52866, -92.1061),
        LatLng(37.52865, -92.10528),
        LatLng(37.52852, -92.10515),
        LatLng(37.52836, -92.10518),
        LatLng(37.52828, -92.10509),
        LatLng(37.52828, -92.10487),
        LatLng(37.52818, -92.10466),
        LatLng(37.52828, -92.10429),
        LatLng(37.5283, -92.1039),
        LatLng(37.5284, -92.10352),
        LatLng(37.52832, -92.10275),
        LatLng(37.52839, -92.10223),
        LatLng(37.5285, -92.10172),
        LatLng(37.52866, -92.10116),
        LatLng(37.52865, -92.10091),
        LatLng(37.52873, -92.10033),
        LatLng(37.52885, -92.10002),
        LatLng(37.52897, -92.09962),
        LatLng(37.52891, -92.09897),
        LatLng(37.52856, -92.09859),
        LatLng(37.52838, -92.09811),
        LatLng(37.52843, -92.09777),
        LatLng(37.52837, -92.09753),
        LatLng(37.52842, -92.09721),
        LatLng(37.52894, -92.09675),
        LatLng(37.52919, -92.09631),
        LatLng(37.52932, -92.09583),
        LatLng(37.52963, -92.09546),
        LatLng(37.53009, -92.09475),
        LatLng(37.53063, -92.09412),
        LatLng(37.53081, -92.09392),
        LatLng(37.53089, -92.09359),
        LatLng(37.53064, -92.09325),
        LatLng(37.53049, -92.09294),
        LatLng(37.53047, -92.09276),
        LatLng(37.5306, -92.09244),
        LatLng(37.53067, -92.092),
        LatLng(37.53072, -92.09129),
        LatLng(37.53108, -92.09073),
        LatLng(37.53128, -92.09052),
        LatLng(37.53125, -92.09036),
        LatLng(37.53135, -92.09004),
        LatLng(37.53165, -92.08993),
        LatLng(37.53164, -92.08973),
        LatLng(37.53143, -92.0894),
        LatLng(37.53146, -92.08926),
        LatLng(37.53132, -92.08903),
        LatLng(37.53147, -92.08867),
        LatLng(37.53166, -92.08846),
        LatLng(37.53171, -92.0882),
        LatLng(37.53153, -92.08784),
        LatLng(37.53127, -92.08765),
        LatLng(37.53093, -92.08776),
        LatLng(37.53041, -92.08748),
        LatLng(37.53007, -92.08692),
        LatLng(37.53, -92.08632),
        LatLng(37.52982, -92.08601),
        LatLng(37.52988, -92.0857),
        LatLng(37.52987, -92.08529),
        LatLng(37.52976, -92.08455),
        LatLng(37.52973, -92.08405),
        LatLng(37.52967, -92.08376),
        LatLng(37.52946, -92.083)
    )
    val polylineCenter = LatLng(37.52876, -92.10781)
    val trailInfo = listOf(
        "Big Piney Trail",
        "Location: Paddy Creek Wilderness in Roby, Missouri",
        "Length: 16.1 miles",
        "Difficulty: Hard",
        "Estimated time: 6h 31m"
    )

    //List of points for Mark Twain National Forest
    val polygonPoints = listOf(
        LatLng(38.001255, -91.258297),
        LatLng(38.001255, -90.924588),
        LatLng(37.737822, -90.910855),
        LatLng(37.725875, -90.774899),
        LatLng(37.600856, -90.772152),
        LatLng(37.605208, -90.936947),
        LatLng(37.413480, -90.946560),
        LatLng(37.420024, -91.421719),
        LatLng(37.703061, -91.423092),
        LatLng(37.701974, -91.259671),
        LatLng(38.001255, -91.258297)
    )
    val polygonCenter = LatLng(37.659734, -91.056658)
    val forestInfo = listOf(
        "Mark Twain National Forest",
        "Location: Rolla, Missouri",
        "Area: 1,491,840 acres",
        "Established: September 23, 1939"
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(polygonCenter, 9f) // Center on the US
    }

    var polylineColor by remember { mutableStateOf(Color.Black) }
    var polylineWidth by remember { mutableFloatStateOf(5f) }
    var polygonColor by remember { mutableStateOf(Color.Red) }
    var polygonWidth by remember { mutableFloatStateOf(5f) }
    val optionList = listOf(polylineColor, polylineWidth, polygonColor, polygonWidth)

    var showSheet by remember { mutableStateOf(false) }
    var sheetName by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //Title banner
        Text(
            text = "Polyline and Polygon Demo",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .background(Color.DarkGray)
        )
        Spacer(modifier = Modifier.height(0.dp))
        Box (
            modifier = modifier.fillMaxSize()
        ) {
            GoogleMap(
                modifier = modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Polyline(
                    points = polylinePoints,
                    color = polylineColor,
                    width = polylineWidth,
                    clickable = true,
                    onClick = {
                        showSheet = true
                        sheetName = "trailInfo"
                    }
                )
                Polygon(
                    points = polygonPoints,
                    fillColor = polygonColor.copy(alpha = .3f),
                    strokeWidth = polygonWidth,
                    clickable = true,
                    onClick = {
                        showSheet = true
                        sheetName = "forestInfo"
                    }
                )
            }
            FloatingActionButton(
                modifier = modifier.padding(10.dp).align(Alignment.TopEnd).size(75.dp),
                onClick = {
                    showSheet = true
                    sheetName = "options"
                }
            ) { Icon(Icons.Default.Settings, contentDescription = "Map Options", modifier = modifier.size(50.dp)) }
            FloatingActionButton(
                modifier = modifier.padding(10.dp).align(Alignment.TopStart).size(75.dp),
                onClick = { cameraPositionState.position = CameraPosition.fromLatLngZoom(polylineCenter, 13f) }
            ) { Icon(painter = painterResource(id = R.drawable.trail_foreground), contentDescription = "Center Trail") }
            FloatingActionButton(
                modifier = modifier.padding(10.dp).align(Alignment.TopCenter).size(75.dp),
                onClick = { cameraPositionState.position = CameraPosition.fromLatLngZoom(polygonCenter, 9f) }
            ) { Icon(painter = painterResource(id = R.drawable.forest_foreground), contentDescription = "Center Forest") }
        }
    }

    //Brings up the bottom modal sheet where the user can change the options
    if (showSheet) {
        when (sheetName) {
            "options" ->
                MapOptions(
                modifier,
                sheetState,
                optionList,
                onDismiss = { showSheet = false },
                onPolylineColorChange = { polylineColor = it },
                onPolylineWidthChange = { polylineWidth = it },
                onPolygonColorChange = { polygonColor = it },
                onPolygonWidthChange = { polygonWidth = it })
            "trailInfo" ->
                DisplayInfo(
                    modifier,
                    sheetState,
                    trailInfo,
                    onDismiss = { showSheet = false }
                )
            "forestInfo" ->
                DisplayInfo(
                    modifier,
                    sheetState,
                    forestInfo,
                    onDismiss = { showSheet = false }
                )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayInfo(modifier: Modifier = Modifier, sheetState: SheetState, details: List<String>, onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .scrollable(enabled = true, state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            details.firstOrNull()?.let { title ->
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(bottom = 5.dp)
                )
            }

            details.drop(1).forEach { detail ->
                Text(
                    text = detail,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapOptions(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    optionList: List<Any>,
    onDismiss: () -> Unit,
    onPolylineColorChange: (Color) -> Unit,
    onPolylineWidthChange: (Float) -> Unit,
    onPolygonColorChange: (Color) -> Unit,
    onPolygonWidthChange: (Float) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        Column(
            modifier = modifier.padding(5.dp).scrollable(enabled = true, state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            Text(
                text = "Polyline Options",
                fontSize = 30.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Line Color",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            ColorOptions(modifier, selectedColor = optionList[0] as Color) { onPolylineColorChange(it) }
            Text(
                text = "Line Width",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            WidthOptions(modifier, selectedWidth = optionList[1] as Float) { onPolylineWidthChange(it) }
            Text(
                text = "Polygon Options",
                fontSize = 30.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Shape Color",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            ColorOptions(modifier, selectedColor = optionList[2] as Color) { onPolygonColorChange(it) }
            Text(
                text = "Line Width",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            WidthOptions(modifier, selectedWidth = optionList[3] as Float) { onPolygonWidthChange(it) }
        }
    }
}

@Composable
fun ColorOptions(modifier: Modifier = Modifier, selectedColor: Color, onColorChange: (Color) -> Unit) {
    val colorOptions = listOf(
        Color.Black,
        Color.Gray,
        Color.DarkGray,
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
    )
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(colorOptions) { color ->
            Box(
                modifier = modifier
                    .padding(5.dp)
                    .background(color, CircleShape)
                    .size(40.dp)
                    .border(2.dp, if (color == selectedColor) Color.Black else Color.Transparent, CircleShape)
                    .clickable {
                        onColorChange(color)
                    }
            )
        }
    }
}

@Composable
fun WidthOptions(modifier: Modifier = Modifier, selectedWidth: Float = 1f, onWidthChange: (Float) -> Unit) {
    val widthOptions = listOf(3f, 5f, 7f, 9f)
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier.padding(0.dp)
    ) {
        items(widthOptions) { width ->
            Text(
                text = width.toInt().toString(),
                fontSize = 25.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { onWidthChange(width) }
                    .border(
                        1.dp,
                        if (width == selectedWidth) Color.Black else Color.Transparent,
                        RoundedCornerShape(10.dp)
                    )
            )
        }
    }

}