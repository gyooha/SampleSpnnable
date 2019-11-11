package io.seroo.samplespnnable

sealed class Mark
object BulletListItem : Mark()
data class NumberListItem(val number: Int) : Mark()