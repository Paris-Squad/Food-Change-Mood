package org.example.presentaion.presenter.io

class ConsolePrinter : Printer {
    override fun display(input: Any?) {
        print(input)
    }

    override fun displayLn(input: Any?) {
        println(input)
    }
}