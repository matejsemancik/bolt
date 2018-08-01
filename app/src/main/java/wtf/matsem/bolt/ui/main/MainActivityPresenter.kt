package wtf.matsem.bolt.ui.main

import floor
import wtf.matsem.bolt.ui.base.BasePresenter
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivityPresenter : BasePresenter<MainView>() {

    val anyCharactersPattern: Pattern = Pattern.compile("[a-zA-Z]")
    var boltRate: Double = 50.0

    companion object {
        val LIMIT_TEXT_LEN: Int = 8
    }

    override fun attachView(view: MainView) {
        super.attachView(view)

        getView()?.setBoltText(1.toDouble().toString())
        getView()?.setCzkText(boltRate.toString())
        getView()?.setBoltRateText(boltRate)
    }

    // CZK Input
    fun onCzkTextChanged(text: String) {
        if (text.startsWith(".")) {
            return
        }

        var matcher: Matcher = anyCharactersPattern.matcher(text)
        if (matcher.find()) {
            return
        }

        if (text.isNotEmpty()) {
            val bolt = czkToBolt(text.toDouble()).floor(1).toString()
            if (bolt.length > LIMIT_TEXT_LEN) {
                getView()?.setBoltText("U WOT M8")
            } else {
                getView()?.setBoltText(bolt)
            }
        } else {
            getView()?.setBoltText("")
        }
    }

    // BOLT input
    fun onBoltTextChanged(text: String) {
        if (text.startsWith(".")) {
            return
        }

        var matcher: Matcher = anyCharactersPattern.matcher(text)
        if (matcher.find()) {
            return
        }

        if (text.isNotEmpty()) {
            val czk = boltToCzk(text.toDouble()).floor(1).toString()
            if (czk.length > LIMIT_TEXT_LEN) {
                getView()?.setCzkText("U WOT M8")
            } else {
                getView()?.setCzkText(czk)
            }
        } else {
            getView()?.setCzkText("")
        }
    }

    fun onRateChangeClick() {
        getView()?.showBoltRateDialog(boltRate)
    }

    fun onRateChanged(newRateString: String) {
        try {
            val newRate = newRateString.toDouble()
            boltRate = newRate
            getView()?.setBoltRateText(boltRate)
        } catch (e: NumberFormatException) {
            getView()?.showToast("Invalid input")
        }
    }

    fun czkToBolt(czk: Double): Double = czk / boltRate

    fun boltToCzk(bolt: Double): Double = bolt * boltRate
}
