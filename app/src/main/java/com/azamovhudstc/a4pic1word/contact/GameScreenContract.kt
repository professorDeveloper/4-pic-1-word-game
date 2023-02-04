package com.azamovhudstc.a4pic1word.contact

import com.azamovhudstc.a4pic1word.model.Pictures


object GameScreenContract {
    var MAX_ANSWER = 7
    var VARIANT_COUNT = 14

    interface Model {
        operator fun get(level: Int): Pictures?
        val allLevel: ArrayList<Pictures>
    }

    interface Presenter {
        fun init(level: Int)
        fun onClickBackButton(view: android.view.View?)
        fun onClickMenuButton()
        fun onClickDeleteButton()
        fun onClickAnswer(index: Int)
        fun onClickVariant(index: Int)
        fun onClickContinue()
        fun onClickImage(index: Int)
        fun onClickFullImage()
        fun onYesClick()
    }

    interface View {
        fun levelState(index: Int)
        fun coinState(coinCount: Int)
        fun episodeDialog()
        fun resultDialog(level: Int, coinCount: Int, answer: String?)
        fun loadImages(resIdOne: Int, resIdTwo: Int, resIdThree: Int, resIdFour: Int)
        fun hideAnswer(index: Int)
        fun showAnswer(index: Int)
        fun clearAnswer(index: Int)
        fun writeAnswer(index: Int, text: String?)
        fun writeVariant(index: Int, text: String?)
        fun showVariant(index: Int)
        fun hideVariant(index: Int)
        fun isShownVariant(index: Int): Boolean
        fun removeDialog()
        fun animate()
        fun warningDialog()
        fun winDialog()
        fun langState(language: String)
    }
}
