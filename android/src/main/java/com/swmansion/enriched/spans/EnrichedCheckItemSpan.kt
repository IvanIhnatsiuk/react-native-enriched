package com.swmansion.enriched.spans

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.TextPaint
import android.text.style.LeadingMarginSpan
import android.text.style.MetricAffectingSpan
import com.swmansion.enriched.spans.interfaces.EnrichedParagraphSpan
import com.swmansion.enriched.styles.HtmlStyle

class EnrichedCheckItemSpan(private val htmlStyle: HtmlStyle,
                            private var isChecked: Boolean = false
) : MetricAffectingSpan(), LeadingMarginSpan, EnrichedParagraphSpan {
  fun toggleChecked() {
    isChecked = !isChecked
  }

  fun getIsChecked(): Boolean = isChecked

  override fun updateMeasureState(p0: TextPaint) {
    // Do nothing, but inform layout that this span affects text metrics
  }

  override fun getLeadingMargin(p0: Boolean): Int {
    return htmlStyle.blockquoteStripeWidth + htmlStyle.blockquoteGapWidth
  }

  override fun drawLeadingMargin(c: Canvas, p: Paint, x: Int, dir: Int, top: Int, baseline: Int, bottom: Int, text: CharSequence?, start: Int, end: Int, first: Boolean, layout: Layout?) {
    val style = p.style
    val color = p.color
    p.style = Paint.Style.FILL
    p.color = htmlStyle.blockquoteBorderColor
    c.drawRect(x.toFloat(), top.toFloat(), x + dir * htmlStyle.blockquoteStripeWidth.toFloat(), bottom.toFloat(), p)
    p.style = style
    p.color = color
  }

  override fun updateDrawState(textPaint: TextPaint?) {
    val color = htmlStyle.blockquoteColor
    if (color != null) {
      textPaint?.color = color
    }
  }
}
