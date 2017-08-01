package wtf.matsem.bolt.ui.main

import android.os.Bundle
import android.widget.EditText
import butterknife.BindView
import wtf.matsem.bolt.R
import wtf.matsem.bolt.tools.ui.SimpleTextWatcher
import wtf.matsem.bolt.ui.base.BaseActivity


class MainActivity : BaseActivity(), MainView {

	@BindView(R.id.bolt_edittext) lateinit var boltEditText: EditText
	@BindView(R.id.czk_edittext) lateinit var czkEditText: EditText

	val presenter: MainActivityPresenter by lazy {
		MainActivityPresenter()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		presenter.attachView(this)

		setupListeners()

		boltEditText.requestFocus()
	}

	override fun onDestroy() {
		presenter.detachView()
		super.onDestroy()
	}

	override fun getContentView(): Int {
		return R.layout.activity_main
	}

	private fun setupListeners() {
		boltEditText.addTextChangedListener(object : SimpleTextWatcher() {
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				s?.let {
					if (boltEditText.hasFocus()) {
						presenter.onBoltTextChanged(s.toString())
					}
				}
			}
		})

		czkEditText.addTextChangedListener(object : SimpleTextWatcher() {
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				s?.let {
					if (czkEditText.hasFocus()) {
						presenter.onCzkTextChanged(s.toString())
					}
				}
			}
		})
	}

	// region View impl

	override fun setCzkText(text: String) {
		czkEditText.setText(text)
	}

	override fun setBoltText(text: String) {
		boltEditText.setText(text)
	}

	// endregion
}