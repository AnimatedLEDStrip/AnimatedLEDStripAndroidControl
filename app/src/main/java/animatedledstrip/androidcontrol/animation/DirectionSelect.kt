package animatedledstrip.androidcontrol.animation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import animatedledstrip.androidcontrol.R

class DirectionSelect : androidx.fragment.app.Fragment() {

    lateinit var forwardButton: Button
    lateinit var backwardButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val thisView = inflater.inflate(R.layout.fragment_direction_select, container, false)

//        forwardButton = thisView.findViewById<View>(R.id.forward_button) as Button
//        backwardButton = thisView.findViewById<View>(R.id.backward_button) as Button

//        forwardButton.setOnClickListener {
//            animationData.direction('F')
//            if (connected) {
//                animationData.send()
//                Log.d("AnimationData", animationData.toString())
//            } else {
//                Toast.makeText(this.context, "Not connected to server", Toast.LENGTH_LONG).show()
//            }
//            animationData = AnimationData()
//            AnimationNeeds.reset()
//            activity?.supportFragmentManager!!
//                .beginTransaction()
//                .replace(
//                    R.id.startup_container,
//                    AnimationSelectFragment()
//                )
//                .commit()
//        }
//        backwardButton.setOnClickListener {
//            animationData.direction('B')
//            if (connected) {
//                animationData.send()
//            } else {
//                Toast.makeText(this.context, "Not connected to server", Toast.LENGTH_LONG).show()
//            }
//            animationData = AnimationData()
//            AnimationNeeds.reset()
//            activity?.supportFragmentManager!!
//                .beginTransaction()
//                .replace(
//                    R.id.startup_container,
//                    AnimationSelectFragment()
//                )
//                .commit()
//        }
        return thisView
    }

    fun directionToggle(view: View) {
        when ((view as Button).text) {
            "Forward" -> {}
            "Backward" -> {}
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DirectionSelect()
    }
}
