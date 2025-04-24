package org.example.presentaion.presenter

import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.example.utils.formatDetails

class GetQuickHealthyPicksPresenter(private val getQuickHealthyPicks: GetQuickHealthyPicksUseCase) : BasePresenter() {
    fun invoke() {
        getQuickHealthyPicks().fold(
            onSuccess = { healthyPicks -> healthyPicks.forEach { println(it.formatDetails()) } },
            onFailure = ::handleException
        )
    }
}