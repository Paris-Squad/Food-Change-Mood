package org.example.presentaion.presenter

import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class QuickHealthyPicksPresenter(
    private val quickHealthyPicks: GetQuickHealthyPicksUseCase, printer: Printer
) : BasePresenter(printer) {
    fun presentQuickHealthyMeals() {
        quickHealthyPicks().fold(
            onSuccess = { healthyPicks -> healthyPicks.forEach { printer.displayLn(it.formatDetails()) } },
            onFailure = ::handleException
        )
    }
}