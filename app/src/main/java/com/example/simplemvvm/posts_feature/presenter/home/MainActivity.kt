package com.example.simplemvvm.posts_feature.presenter.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplemvvm.core_feature.domain.model.UIState
import com.example.simplemvvm.core_feature.presenter.theme.SimpleMVVMTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.simplemvvm.posts_feature.domain.model.Posts

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListPosts()
                }
            }
        }
    }
}

@Composable
fun ListPosts(
    viewModel:HomeViewModel = hiltViewModel<HomeViewModel>()
) {
    val data by viewModel.posts.observeAsState(initial = UIState.Loading)
    when(val state=data){
        is UIState.Loading->{
            Log.i("responseTest","Loading")


        }
        is UIState.Failure->{
            Log.e("responseTest",state.error.toString())

        }
        is UIState.Success->{
            Log.i("responseTest",state.data.toString())
            LazyColumn(
                flingBehavior = ScrollableDefaults.flingBehavior(),
                state = rememberLazyListState(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = "Posts",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    }
                    items(
                        count = state.data.size,
                        key= {k->
                            state.data[k].id!!
                        },
                        itemContent = {index->
                            val itemData=state.data[index]
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                    ),
                            ){
                                Column(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(
                                        text = itemData.title!!+"[${itemData.id}]",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = itemData.body!!,
                                        style = MaterialTheme.typography.bodyLarge,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                    )
                }
            )
        }
    }
}
