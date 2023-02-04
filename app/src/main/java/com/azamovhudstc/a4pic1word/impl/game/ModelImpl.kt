package com.azamovhudstc.a4pic1word.impl.game

import com.azamovhudstc.a4pic1word.contact.GameScreenContract
import com.azamovhudstc.a4pic1word.data.Database
import com.azamovhudstc.a4pic1word.model.Pictures


class ModelImpl : GameScreenContract.Model {
    private val pictures: ArrayList<Pictures> =
        Database.instance!!.allQuestions
    override operator fun get(level: Int): Pictures {
        return pictures[level]
    }

    override val allLevel: ArrayList<Pictures>
        get() = pictures

}
