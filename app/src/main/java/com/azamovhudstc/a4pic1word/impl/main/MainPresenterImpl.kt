package com.azamovhudstc.a4pic1word.impl.main

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.widget.SwitchCompat
import androidx.navigation.Navigation.findNavController
import com.azamovhudstc.a4pic1word.R
import com.azamovhudstc.a4pic1word.contact.MainScreenContract
import com.azamovhudstc.a4pic1word.model.Pictures
import com.azamovhudstc.a4pic1word.utils.MySharedPreference
import java.util.*

class MainPresenterImpl(
    repository: MainScreenContract.Repository,
    view: MainScreenContract.View,
    context: Context
) :
    MainScreenContract.Presenter {
    private val repository: MainScreenContract.Repository
    private val view: MainScreenContract.View
    private val context: Context
    override fun init() {
        val sharedPreference: MySharedPreference? = MySharedPreference.getInstance(context)
        view.currentLevel(sharedPreference!!.level + 1)
        val picture: Pictures = repository.getQuestion(sharedPreference.level)!!
        view.loadImageCurrentLevel(
            picture.picturesOne,
            picture.picturesTwo,
            picture.picturesThree,
            picture.picturesFour
        )
    }

    override fun onClickSetting() {}
    override fun onClickPlay(view: View?) {
        findNavController(view!!).navigate(R.id.action_mainScreen_to_gameScreen)
    }


    override fun onClickLanguageButton() {

    }

    override fun onClickInfoButton() {

    }

    override fun onCLickUzb() {

    }

    override fun onClickRus() {

    }

    override fun onClickEng() {

    }

    init {
        this.repository = repository
        this.view = view
        this.context = context
    }


}
