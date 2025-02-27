package com.icgen.movieapp.home.presentation.catalog

import com.icgen.movieapp.home.domain.usecase.GetCatalogUseCase.Output

sealed class CatalogState {
    data object Loading : CatalogState()
    data class CatalogError(val message: String?) : CatalogState()
    data class CatalogLoaded(val catalog: Output) : CatalogState()
}