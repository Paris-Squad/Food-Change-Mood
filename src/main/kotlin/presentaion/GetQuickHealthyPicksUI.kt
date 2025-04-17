package org.example.presentaion
import org.example.domain.usecase.GetQuickHealthyPicksUseCase

class GetQuickHealthyPicksUI(private val useCase:GetQuickHealthyPicksUseCase) {
     fun invoke() {
        useCase.quickHealthyPicks().forEach {
            println(it)
        }
    }
}