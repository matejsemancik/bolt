package wtf.matsem.bolt.ui.main

import floor
import wtf.matsem.bolt.ui.base.BasePresenter

class MainActivityPresenter : BasePresenter<MainView>() {

	val TAG: String = this.javaClass.simpleName

	companion object {
		val BLT_RATE: Double = 48.0
		val LIMIT_TEXT_LEN: Int = 8
	}

	override fun attachView(view: MainView) {
		super.attachView(view)

		getView()?.setBoltText("1.0")
		getView()?.setCzkText("48.0")
	}

	fun onCzkTextChanged(text: String) {
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

	fun onBoltTextChanged(text: String) {
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
