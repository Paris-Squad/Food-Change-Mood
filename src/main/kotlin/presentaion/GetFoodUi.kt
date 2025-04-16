package org.example.presentaion
import org.example.domain.usecase.GetQuickHealthyPicksUseCase

class GetFoodUi(private val useCase:GetQuickHealthyPicksUseCase) {
    operator fun invoke() {
        useCase.quickHealthyPicks().forEach {
            println(it)
        }
    }
}