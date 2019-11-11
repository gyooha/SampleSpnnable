package io.seroo.samplespnnable

import android.text.Editable
import android.text.Html
import io.seroo.samplespnnable.tags.Ol
import io.seroo.samplespnnable.tags.Ul
import org.xml.sax.XMLReader
import java.util.*

class ListTagHandler : Html.TagHandler {
    private val stack = Stack<ListTag>()

    override fun handleTag(
        opening: Boolean,
        tag: String?,
        output: Editable,
        xmlReader: XMLReader?
    ) {
        when (tag) {
            StringUtils.UL_TAG -> if (opening) {
                stack.push(Ul())
            } else {
                stack.pop()
            }
            StringUtils.OL_TAG -> if (opening) {
                stack.push(Ol())
            } else {
                stack.pop()
            }
            StringUtils.LI_TAG -> if (opening) {
                stack.peek().openItem(output)
            } else {
                stack.peek().closeItem(output, indentation = stack.size - 1)
            }
        }
    }

    interface ListTag {
        fun openItem(text: Editable)
        fun closeItem(text: Editable, indentation: Int)

        fun appendNewLine(text: Editable) {
            if (text.isNotEmpty() && text.last() != '\n') {
                text.append("\n")
            }
        }
    }
}