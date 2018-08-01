package wtf.matsem.bolt.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*
import wtf.matsem.bolt.R
import wtf.matsem.bolt.tools.ui.SimpleTextWatcher
import wtf.matsem.bolt.ui.base.BaseActivity


class MainActivity : BaseActivity(), MainView {

    val presenter: MainActivityPresenter by lazy {
        MainActivityPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)

        setupListeners()

        bolt_edittext.requestFocus()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    private fun setupListeners() {
        bolt_edittext.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (bolt_edittext.hasFocus()) {
                        presenter.onBoltTextChanged(s.toString())
                    }
                }
            }
        })

        czk_edittext.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (czk_edittext.hasFocus()) {
                        presenter.onCzkTextChanged(s.toString())
                    }
                }
            }
        })

        top_up_button.setOnClickListener {
            val url = "https://letitroll.pay.intellifest.com/login"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        bolt_rate_button.setOnClickListener {
            presenter.onRateChangeClick()
        }
    }

    // endregion

    // region View impl

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setCzkText(text: String) {
        czk_edittext.setText(text)
    }

    override fun setBoltText(text: String) {
        bolt_edittext.setText(text)
    }

    override fun showBoltRateDialog(currentRate: Double) {
        MaterialDialog.Builder(this)
                .title(resources.getString(R.string.bolt_dialog_title))
                .content(resources.getString(R.string.bolt_dialog_content))
                .inputType(InputType.TYPE_NUMBER_FLAG_DECIMAL)
                .input(resources.getString(R.string.bolt_dialog_hint), currentRate.toString()) { dialog, input ->
                    presenter.onRateChanged(input.toString())
                    presenter.onBoltTextChanged(bolt_edittext.text.toString())
                }
                .show()
    }

    override fun setBoltRateText(boltRate: Double) {
        bolt_rate_button.text = resources.getString(R.string.bolt_rate_text, boltRate)
    }

    // endregion
}
