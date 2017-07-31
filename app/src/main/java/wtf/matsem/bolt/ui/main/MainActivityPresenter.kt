package wtf.matsem.bolt.ui.main

import floor
import wtf.matsem.bolt.ui.base.BasePresenter

class MainActivityPresenter : BasePresenter<MainView>() {

	val BLT_RATE: Double = 48.0
	val TAG: String = this.javaClass.simpleName

	override fun attachView(view: MainView) {
		super.attachView(view)

		getView()?.setBoltText("1.0")
		getView()?.setCzkText("48.0")
	}

	fun onCzkTextChanged(text: String) {
		if (text.isNotEmpty()) {
			val bolt = czkToBolt(text.toDouble())
			getView()?.setBoltText(bolt.floor(1).toString())
		} else {
			getView()?.setBoltText("")
		}
	}

	fun onBoltTextChanged(text: String) {
		if (text.isNotEmpty()) {
			val czk = boltToCzk(text.toDouble())
			getView()?.setCzkText(czk.floor(1).toString())
		} else {
			getView()?.setCzkText("")
		}
	}

	fun czkToBolt(czk: Double): Double = czk / BLT_RATE

	fun boltToCzk(bolt: Double): Double = bolt * BLT_RATE
}
