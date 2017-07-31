package wtf.matsem.bolt.ui.base

import android.support.annotation.CallSuper

abstract class BasePresenter<T: BaseMvpView> {

	var mvpView: T? = null

	@CallSuper open fun attachView(view: T) {
		this.mvpView = view
	}

	@CallSuper open fun detachView() {
		this.mvpView = null
	}

	fun getView(): T? = mvpView
}


