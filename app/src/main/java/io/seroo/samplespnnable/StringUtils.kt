package io.seroo.samplespnnable

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import androidx.core.text.HtmlCompat

object StringUtils {
    const val OL_TAG = "ordered"
    const val UL_TAG = "unordered"
    const val LI_TAG = "listitem"
    const val GAP_WIDTH = 50

    fun fromHtml(html: String?): Spanned {
        html ?: return SpannableString("")

        val formattedHtml = html
            .replace("(?i)<ul[^>]*>".toRegex(), "<$UL_TAG>")
            .replace("(?i)</ul>".toRegex(), "</$UL_TAG>")
            .replace("(?i)<ol[^>]*>".toRegex(), "<$OL_TAG>")
            .replace("(?i)</ol>".toRegex(), "</$OL_TAG>")
            .replace("(?i)<li[^>]*>".toRegex(), "<$LI_TAG>")
            .replace("(?i)</li>".toRegex(), "</$LI_TAG>")

        return HtmlCompat.fromHtml(
            formattedHtml,
            HtmlCompat.FROM_HTML_MODE_LEGACY,
            null,
            ListTagHandler()
        )
    }

    inline fun <reified T : Mark> getLast(text: Spanned) =
        text.getSpans(0, text.length, T::class.java).lastOrNull()

    fun setSpanFromMark(text: Spannable, mark: Mark, styleSpan: Any) {
        val markLocation = text.getSpanStart(mark)
        text.removeSpan(mark)

        val end = text.length
        if (markLocation != end) {
            text.setSpan(styleSpan, markLocation, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    fun start(text: Spannable, mark: Mark) {
        val currentPosition = text.length
        text.setSpan(mark, currentPosition, currentPosition, Spanned.SPAN_MARK_MARK)
    }
}