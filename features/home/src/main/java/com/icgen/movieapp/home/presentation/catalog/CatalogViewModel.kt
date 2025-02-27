package com.icgen.movieapp.home.presentation.catalog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icgen.movieapp.common.util.Dispatcher
import com.icgen.movieapp.common.util.StringHelper.getGenericErrorMessage
import com.icgen.movieapp.common.util.StringProvider
import com.icgen.movieapp.home.domain.usecase.GetCatalogUseCase
import com.icgen.movieapp.home.domain.usecase.GetCatalogUseCase.Output
import com.icgen.movieapp.home.presentation.catalog.CatalogState.CatalogError
import com.icgen.movieapp.home.presentation.catalog.CatalogState.CatalogLoaded
import com.icgen.movieapp.home.presentation.catalog.CatalogState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val dispatcher: Dispatcher,
    private val provider: StringProvider
) : ViewModel() {

    private val _state: MutableState<CatalogState> = mutableStateOf(Loading)

    val state: State<CatalogState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = CatalogError(getGenericErrorMessage(provider))
    }

    init {
        loadCatalog()
    }

    fun retry() {
        _state.value = Loading
        loadCatalog()
    }

    private fun loadCatalog() {
        viewModelScope.launch(errorHandler) {
            val catalog = fetchCatalog()
            if (catalog != null) _state.value = CatalogLoaded(catalog)
            else _state.value = CatalogError(getGenericErrorMessage(provider))
        }
    }

    private suspend fun fetchCatalog(): Output? {
        return withContext(dispatcher.io) {
            getCatalogUseCase().getOrNull()
        }
    }
}