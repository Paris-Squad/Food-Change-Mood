package org.example.presentaion

import org.example.domain.usecase.GetFoodUseCase

class GetFoodUi(private val useCase:GetFoodUseCase) {
    operator fun invoke() {
        println(useCase.invoke())
    }
}