package wtf.matsem.bolt.ui.main

import wtf.matsem.bolt.ui.base.BaseMvpView

interface MainView : BaseMvpView {

	fun setBoltText(text: String)

	fun setCzkText(text: String)
}


