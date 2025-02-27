package com.icgen.movieapp.home.presentation.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icgen.movieapp.common.presentation.navigation.NavigationConstants.ID_PARAM
import com.icgen.movieapp.common.util.Dispatcher
import com.icgen.movieapp.common.util.StringHelper.getGenericErrorMessage
import com.icgen.movieapp.common.util.StringProvider
import com.icgen.movieapp.home.domain.usecase.GetDetailUseCase
import com.icgen.movieapp.home.domain.usecase.GetDetailUseCase.Output
import com.icgen.movieapp.home.presentation.detail.DetailViewState.DetailError
import com.icgen.movieapp.home.presentation.detail.DetailViewState.DetailLoaded
import com.icgen.movieapp.home.presentation.detail.DetailViewState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getDetailUseCase: GetDetailUseCase,
    private val dispatcher: Dispatcher,
    private val provider: StringProvider
) : ViewModel() {

    private val _state: MutableState<DetailViewState> = mutableStateOf(Loading)

    val state: State<DetailViewState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = DetailError(getGenericErrorMessage(provider))
    }

    init {
        loadDetail()
    }

    private fun loadDetail() {
        viewModelScope.launch(errorHandler) {
            val id = stateHandle.get<Int>(ID_PARAM) ?: 0
            val result = fetchDetail(id)
            result?.let { _state.value = DetailLoaded(result) }
        }
    }

    private suspend fun fetchDetail(id: Int) : Output? {
        return withContext(dispatcher.io) {
            getDetailUseCase(id).getOrNull()
        }
    }
}