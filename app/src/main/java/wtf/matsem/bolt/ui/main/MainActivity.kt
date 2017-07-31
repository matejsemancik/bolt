package wtf.matsem.bolt.ui.main

import android.os.Bundle
import android.support.v4.view.VelocityTrackerCompat
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.widget.TextView
import butterknife.BindView
import wtf.matsem.bolt.R
import wtf.matsem.bolt.ui.base.BaseActivity


class MainActivity : BaseActivity() {

	@BindView(R.id.bolt_velocity_text) lateinit var boltVelocityText: TextView
	@BindView(R.id.realcurrency_velocity_text) lateinit var realCurrencyVelocityText: TextView
	@BindView(R.id.bolt_text) lateinit var boltText: TextView
	@BindView(R.id.realcurrency_text) lateinit var realCurrencyText: TextView

	val velocityTracker: VelocityTracker by lazy {
		VelocityTracker.obtain()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		boltText.text = "1.0 BLT"
		realCurrencyText.text = "48 CZK"
	}

	override fun getContentView(): Int {
		return R.layout.activity_main
	}

	override fun onTouchEvent(event: MotionEvent?): Boolean {
		event?.let {
			val index = it.getActionIndex()
			val action = it.getActionMasked()
			val pointerId = it.getPointerId(index)

			when (action) {
				MotionEvent.ACTION_DOWN -> {
					velocityTracker.clear();
					// Add a user's movement to the tracker.
					velocityTracker.addMovement(it)
				}

				MotionEvent.ACTION_MOVE -> {
					velocityTracker.addMovement(it)
					// When you want to determine the velocity, call
					// computeCurrentVelocity(). Then call getXVelocity()
					// and getYVelocity() to retrieve the velocity for each pointer ID.
					velocityTracker.computeCurrentVelocity(500)
					// Log velocity of pixels per second
					// Best practice to use VelocityTrackerCompat where possible.

					Log.d("", "X velocity: " + VelocityTrackerCompat.getXVelocity(velocityTracker, pointerId))
					Log.d("", "Y velocity: " + VelocityTrackerCompat.getYVelocity(velocityTracker, pointerId))

					boltVelocityText.text = "${VelocityTrackerCompat.getXVelocity(velocityTracker, pointerId)}"
					realCurrencyVelocityText.text = "${VelocityTrackerCompat.getYVelocity(velocityTracker, pointerId)}"
				}

				MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
					// Return a VelocityTracker object back to be re-used by others.
					velocityTracker.clear()
				else -> {
					// Do nothing
				}
			}
		}

		return true
	}
}