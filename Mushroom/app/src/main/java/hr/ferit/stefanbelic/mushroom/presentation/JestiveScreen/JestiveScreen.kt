import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.stefanbelic.mushroom.models.MashroomInfo
import hr.ferit.stefanbelic.mushroom.presentation.JestiveScreen.JestiveScreenState
import hr.ferit.stefanbelic.mushroom.presentation.commonComposables.MushroomListItem
import hr.ferit.stefanbelic.mushroom.ui.theme.BackgroundColor
import hr.ferit.stefanbelic.mushroom.ui.theme.BlackColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JestiveScreen(
    jestiveScreenState: State<JestiveScreenState>, removeFromList: (MashroomInfo) -> Unit,
    addMushroomClicked: () -> Unit = {}
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = jestiveScreenState.value.error, block = {
        jestiveScreenState.value.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    })

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
            .padding(10.dp)
    ) {

        Column {

            Text(
                text = "JESTIVE GLJIVE",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                style = TextStyle(textAlign = TextAlign.Center),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Divider(color = BlackColor)

            jestiveScreenState.value.data?.let { list ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        list.size,
                        key = { index ->
                            list[index].uid
                        },
                    ) { index ->

                        val dismissState = rememberDismissState()


                        if (dismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                            Toast.makeText(context, "Stavka uklonjena", Toast.LENGTH_SHORT).show()
                            removeFromList(list[index])
                        }

                        SwipeToDismiss(state = dismissState, directions = setOf(
                            DismissDirection.EndToStart
                        ), background = {



                            val backgroundColor by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.8f)
                                    else -> BackgroundColor
                                }, label = ""
                            )


                            val iconScale by animateFloatAsState(
                                targetValue = if (dismissState.targetValue == DismissValue.DismissedToStart) 1.3f else 0.0f,
                                label = ""
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = backgroundColor)
                                    .padding(end = 16.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    modifier = Modifier.scale(iconScale),
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White
                                )
                            }

                        }, dismissContent = {
                            MushroomListItem(mashroomInfo = list[index])
                            Divider(color = BlackColor)
                        })

                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                addMushroomClicked()
            },
            Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }

        if (jestiveScreenState.value.isLoading) {
            Box(
                modifier = Modifier.size(20.dp), contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator()
            }
        }

    }

}


@Preview
@Composable
fun PreviewJestiveScreen() {

    val defaultJestiveScreenState = remember {
        mutableStateOf(
            JestiveScreenState(
                isLoading = true
            )
        )
    }
    JestiveScreen(jestiveScreenState = defaultJestiveScreenState, {})
}