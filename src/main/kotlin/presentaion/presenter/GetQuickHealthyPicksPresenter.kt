package org.example.presentaion.presenter

import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.example.utils.formatDetails

class GetQuickHealthyPicksPresenter(private val useCase: GetQuickHealthyPicksUseCase) {
    fun invoke() {
        useCase().fold(
            onSuccess = { healthyPicks -> healthyPicks.forEach { println(it.formatDetails()) } },
            onFailure = { println("Error: ${it.message}") }
        )
    }
}