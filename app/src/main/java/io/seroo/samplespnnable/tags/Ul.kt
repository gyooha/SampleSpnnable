package io.seroo.samplespnnable.tags

import android.text.Editable
import io.seroo.samplespnnable.BulletListItem
import io.seroo.samplespnnable.ListTagHandler
import io.seroo.samplespnnable.StringUtils.GAP_WIDTH
import io.seroo.samplespnnable.StringUtils.getLast
import io.seroo.samplespnnable.StringUtils.setSpanFromMark
import io.seroo.samplespnnable.StringUtils.start
import io.seroo.samplespnnable.TextLeadingMarginSpan

class Ul : ListTagHandler.ListTag {
    override fun openItem(text: Editable) {
        appendNewLine(text)
        start(text, BulletListItem)
    }

    override fun closeItem(text: Editable, indentation: Int) {
        appendNewLine(text)
        getLast<BulletListItem>(text)?.let { actualMark ->
            setSpanFromMark(text, actualMark, TextLeadingMarginSpan(GAP_WIDTH, indentation, "•"))
        }
    }
}