package com.icgen.movieapp.home.presentation.detail

import com.icgen.movieapp.home.domain.usecase.GetDetailUseCase.Output

sealed class DetailViewState {
    data object Loading : DetailViewState()

    data class DetailError(val message: String?) : DetailViewState()

    data class DetailLoaded(val result: Output) : DetailViewState()
}