package wtf.matsem.bolt.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife

abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(getContentView())
		ButterKnife.bind(this)
	}

	@LayoutRes abstract fun getContentView(): Int
}


