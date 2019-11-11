package io.seroo.samplespnnable.tags

import android.text.Editable
import io.seroo.samplespnnable.BulletListItem
import io.seroo.samplespnnable.ListTagHandler
import io.seroo.samplespnnable.NumberListItem
import io.seroo.samplespnnable.StringUtils.GAP_WIDTH
import io.seroo.samplespnnable.StringUtils.getLast
import io.seroo.samplespnnable.StringUtils.setSpanFromMark
import io.seroo.samplespnnable.StringUtils.start
import io.seroo.samplespnnable.TextLeadingMarginSpan

class Ol : ListTagHandler.ListTag {
    private var index = 1

    override fun openItem(text: Editable) {
        appendNewLine(text)
        start(text, BulletListItem)
        index++
    }

    override fun closeItem(text: Editable, indentation: Int) {
        appendNewLine(text)

        getLast<NumberListItem>(text)?.let { actualMark ->
            val prefix = "${actualMark.number}."
            setSpanFromMark(text, actualMark, TextLeadingMarginSpan(GAP_WIDTH, indentation, prefix))
        }
    }
}