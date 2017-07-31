package wtf.matsem.bolt.ui.main

import timber.log.Timber
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
			Timber.tag(TAG).d("CZK: ${text.toDouble()} -> BLT: ${czkToBolt(text.toDouble())}")
			getView()?.setBoltText(czkToBolt(text.toDouble()).toString())
		} else {
			getView()?.setBoltText("")
		}
	}

	fun onBoltTextChanged(text: String) {
		if (text.isNotEmpty()) {
			Timber.tag(TAG).d("BLT: ${text.toDouble()} -> CZK: ${boltToCzk(text.toDouble())}")
			getView()?.setCzkText(boltToCzk(text.toDouble()).toString())
		} else {
			getView()?.setCzkText("")
		}
	}

	fun czkToBolt(czk: Double): Double = czk / BLT_RATE

	fun boltToCzk(bolt: Double): Double = bolt * BLT_RATE
}
