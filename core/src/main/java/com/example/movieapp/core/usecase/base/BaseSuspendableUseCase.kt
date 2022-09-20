package com.example.movieapp.core.usecase.base

abstract class BaseSuspendableUseCase<Input, Output> {

    suspend operator fun invoke(input: Input? = null): Result<Output> {
        return try {
            val output = execute(input)
            Result.success(output)
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    abstract suspend fun execute(input: Input?): Output
}
