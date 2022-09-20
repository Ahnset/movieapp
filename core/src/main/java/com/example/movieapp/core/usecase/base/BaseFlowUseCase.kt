package com.example.movieapp.core.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

abstract class BaseFlowUseCase<Input, Output> {

    operator fun invoke(input: Input): Flow<Result<Output>> {
        return execute(input)
            .map { Result.success(it) }
            .catch { t: Throwable -> Result.failure<Output>(t) }
    }

    abstract fun execute(input: Input): Flow<Output>
}
