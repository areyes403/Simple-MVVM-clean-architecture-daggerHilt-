package com.example.simplemvvm.posts_feature.presenter.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplemvvm.core_feature.domain.model.UIState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
){
    val data by viewModel.posts.observeAsState(initial = UIState.Loading)
    when(val state=data){
        is UIState.Loading->{
            Log.i("responseTest","Loading")


        }
        is UIState.Error->{
            Log.e("responseTest",state.message+state.code.toString())

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