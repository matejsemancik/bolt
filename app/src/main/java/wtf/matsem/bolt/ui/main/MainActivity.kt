package wtf.matsem.bolt.ui.main

import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import wtf.matsem.bolt.R
import wtf.matsem.bolt.ui.base.BaseActivity

class MainActivity : BaseActivity() {

	@BindView(R.id.bolt_text) lateinit var boltText: TextView
	@BindView(R.id.realcurrency_text) lateinit var realCurrencyText: TextView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		boltText.text = "1.0 BLT"
		realCurrencyText.text = "48 CZK"
	}

	override fun getContentView(): Int {
		return R.layout.activity_main
	}
}
