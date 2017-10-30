package wtf.matsem.bolt.ui.main

import floor
import wtf.matsem.bolt.ui.base.BasePresenter
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivityPresenter : BasePresenter<MainView>() {

	val TAG: String = this.javaClass.simpleName
	val anyCharactersPattern: Pattern = Pattern.compile("[a-zA-Z]")

	companion object {
		val BLT_RATE: Double = 48.0
		val LIMIT_TEXT_LEN: Int = 8
	}

	override fun attachView(view: MainView) {
		super.attachView(view)

		getView()?.setBoltText("1.0")
		getView()?.setCzkText("48.0")
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

	fun czkToBolt(czk: Double): Double = czk / BLT_RATE

	fun boltToCzk(bolt: Double): Double = bolt * BLT_RATE
}
