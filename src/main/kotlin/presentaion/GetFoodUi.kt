package org.example.presentaion

import org.example.domain.usecase.GetFoodUC

class GetFoodUi(private val useCase:GetFoodUC) {
    operator fun invoke() {
        println(useCase.invoke())
    }
}