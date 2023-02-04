package com.azamovhudstc.a4pic1word.impl.main

import com.azamovhudstc.a4pic1word.contact.MainScreenContract
import com.azamovhudstc.a4pic1word.data.Database
import com.azamovhudstc.a4pic1word.model.Pictures


class MainModelImpl : MainScreenContract.Repository {
    private val pictures: ArrayList<Pictures> = Database.instance!!.allQuestions
    override fun getQuestion(level: Int): Pictures {
        return pictures[level]
    }
}
