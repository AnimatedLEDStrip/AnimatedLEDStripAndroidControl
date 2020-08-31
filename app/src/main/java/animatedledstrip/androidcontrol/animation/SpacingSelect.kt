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

package animatedledstrip.androidcontrol.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import animatedledstrip.androidcontrol.R
import animatedledstrip.androidcontrol.utils.animationData
import animatedledstrip.androidcontrol.utils.mainSender
import animatedledstrip.animationutils.ParamUsage
import animatedledstrip.animationutils.findAnimation
import animatedledstrip.animationutils.spacing
import kotlinx.android.synthetic.main.fragment_spacing_select.*

/**
 * Set the spacing property of the animation.
 */
class SpacingSelect : Fragment(), SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(bar: SeekBar?, progress: Int, p2: Boolean) {
        spacing_value.text = progress.toString()
        animationData.spacing(progress)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spacing_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spacing_select.max = mainSender.stripInfo?.numLEDs ?: 240
        val info = findAnimation(animationData.animation).info
        spacing_select.progress =
            if (info.spacing != ParamUsage.NOTUSED) info.spacingDefault
            else spacing_select.max
        animationData.spacing = spacing_select.progress
        spacing_select.setOnSeekBarChangeListener(this)
        spacing_value.text = spacing_select.progress.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SpacingSelect()
    }

}
