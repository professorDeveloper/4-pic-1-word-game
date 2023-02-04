package com.azamovhudstc.a4pic1word.contact

import androidx.appcompat.widget.SwitchCompat
import com.azamovhudstc.a4pic1word.model.Pictures


class MainScreenContract {
    interface Presenter {
        fun init()
        fun onClickSetting()
        fun onClickPlay(view: android.view.View?)
        fun onClickLanguageButton()
        fun onClickInfoButton()
        fun onCLickUzb()
        fun onClickRus()
        fun onClickEng()
    }

    interface View {
        fun coinCount()
        fun loadImageCurrentLevel(resIdOne: Int, resIdTwo: Int, resIdThree: Int, resIdFour: Int)
        fun currentLevel(level: Int)
        fun settingDialog()
        fun infoDialog()
        fun langDialog()
    }

    interface Repository {
        fun getQuestion(level: Int): Pictures?
    }
}
