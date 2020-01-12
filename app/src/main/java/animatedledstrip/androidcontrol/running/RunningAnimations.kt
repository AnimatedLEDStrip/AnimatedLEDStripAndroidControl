/*
 *  Copyright (c) 2020 AnimatedLEDStrip
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package animatedledstrip.androidcontrol.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import animatedledstrip.androidcontrol.R
import animatedledstrip.androidcontrol.utils.mainSender
import kotlinx.android.synthetic.main.fragment_running_animations.*

/**
 * The list of running animations.
 */
class RunningAnimations : Fragment() {
    private fun addCurrentAnimations() {
        mainSender.runningAnimations.forEach { (id, data) ->
            fragmentManager?.beginTransaction()?.add(
                running_animation_list.id,
                AnimationFragment.newInstance(data),
                id
            )?.commit()
        }
    }

    private fun setConnectionCallbacks() {
        mainSender
            .setOnNewAnimationCallback { data ->
                activity?.runOnUiThread {
                    fragmentManager?.beginTransaction()?.add(
                        running_animation_list?.id ?: return@runOnUiThread,
                        AnimationFragment.newInstance(data),
                        data.id
                    )?.commit()
                }
            }.setOnEndAnimationCallback { data ->
                activity?.runOnUiThread {
                    fragmentManager?.beginTransaction()
                        ?.remove(
                            fragmentManager?.findFragmentByTag(data.id) ?: return@runOnUiThread
                        )
                        ?.commit()
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_running_animations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCurrentAnimations()
        setConnectionCallbacks()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RunningAnimations()
    }

}
