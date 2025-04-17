package org.example.presentaion

import org.example.domain.usecase.GetMealUseCase

class GetMealConsoleUi(private val useCase:GetMealUseCase) {
    operator fun invoke() {
        println(useCase.invoke())
    }
}