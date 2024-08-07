package com.lazyxu.mine

import android.widget.SearchView
import androidx.appcompat.widget.AppCompatTextView
import com.lazyxu.network.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchViewModel : BaseViewModel() {
    private fun search(searchView: SearchView,textViewResult:AppCompatTextView) {
//        viewModelScope.launch {
//            searchView.getQueryTextChangeStateFlow()
//                .debounce(300)
//                .filter { query ->
//                    if (query.isEmpty()) {
//                        textViewResult.text = ""
//                        return@filter false
//                    } else {
//                        return@filter true
//                    }
//                }
//                .distinctUntilChanged()
//                .flatMapLatest { query ->
//                    dataFromNetwork(query)
//                        .catch {
//                            emitAll(flowOf(""))
//                        }
//                }
//                .flowOn(Dispatchers.Default)
//                .collect { result ->
//                    textViewResult.text = result
//                }
//        }
    }
    private fun dataFromNetwork(query: String): Flow<String> {
        return flow {
            delay(2000)
            emit(query)
        }
    }
}