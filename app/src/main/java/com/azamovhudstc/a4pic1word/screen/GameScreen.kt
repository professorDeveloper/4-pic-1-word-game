package com.azamovhudstc.a4pic1word.screen

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.a4pic1word.R
import com.azamovhudstc.a4pic1word.contact.GameScreenContract
import com.azamovhudstc.a4pic1word.impl.game.ModelImpl
import com.azamovhudstc.a4pic1word.impl.game.PresenterImpl
import com.azamovhudstc.a4pic1word.utils.MySharedPreference
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.game_screen.*
import kotlinx.android.synthetic.main.result_dialog.view.*

class GameScreen : Fragment(R.layout.game_screen),
    GameScreenContract.View {
    private val answerButtons: MutableList<Button> = ArrayList()
    private val variantButtons: MutableList<Button> = ArrayList()
    private lateinit var presenter: GameScreenContract.Presenter
    private lateinit var sharedPreference: MySharedPreference
    private lateinit var buttonSound: MediaPlayer
    private var model: ModelImpl? = null
    private var isWin: Boolean = false
    private var isNext: Boolean = false
    private var isAnim: Boolean = false
    var imag4_res = 0
    var imag1_res = 0
    var imag2_res = 0
    var imag3_res = 0

    private lateinit var views: View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = view
        presenter = PresenterImpl(this, ModelImpl(), requireContext())

        sharedPreference = MySharedPreference.getInstance(requireContext())!!
        loadButtons(answerButtons, R.id.layout_answer, object : OnClickListener {
            override fun onClick(index: Int) {
                (presenter as PresenterImpl).onClickAnswer(index)
            }

        })

        loadButtons(variantButtons, R.id.variant_one, object : OnClickListener {
            override fun onClick(index: Int) {
                (presenter as PresenterImpl).onClickVariant(index)
            }
        })

        loadButtons(variantButtons, R.id.variant_two, object : OnClickListener {
            override fun onClick(index: Int) {
                (presenter as PresenterImpl).onClickVariant(index)
            }

        })

        (presenter as PresenterImpl).init(sharedPreference.level + 1)
        back.setOnClickListener {
            (presenter as PresenterImpl).onClickBackButton(
                requireView()
            )
        }


        delete.setOnClickListener {
            removeDialog()
        }
        setImageZoom()


    }

    override fun levelState(index: Int) {
        level.text = index.toString()
    }

    override fun coinState(coinCount: Int) {
        coinsCount.text = coinCount.toString()
    }

    override fun episodeDialog() {

    }

    override fun loadImages(resIdOne: Int, resIdTwo: Int, resIdThree: Int, resIdFour: Int) {
        imag1_res = resIdOne
        imag2_res = resIdTwo
        imag3_res = resIdThree
        imag4_res = resIdFour
        image_oneS.setImageResource(resIdOne)
        image_twoS.setImageResource(resIdTwo)
        image_threeS.setImageResource(resIdThree)
        image_fourS.setImageResource(resIdFour)

    }

    override fun hideAnswer(index: Int) {
        YoYo.with(Techniques.FadeIn).duration(600)
            .playOn(answerButtons[index])

        answerButtons[index].visibility = View.GONE
    }

    override fun showAnswer(index: Int) {
        answerButtons[index].visibility = View.VISIBLE
    }

    override fun clearAnswer(index: Int) {
        answerButtons[index].text = ""
    }

    override fun writeAnswer(index: Int, text: String?) {

        YoYo.with(Techniques.BounceIn).duration(900)
            .playOn(answerButtons[index])

        answerButtons[index].text = text

    }

    override fun writeVariant(index: Int, text: String?) {

        YoYo.with(Techniques.FadeIn).duration(800)
            .playOn(variantButtons[index])
        variantButtons[index].text = text

    }

    fun setImageZoom() {
        image_oneS!!.setOnClickListener { v: View? ->


            full.visibility = View.VISIBLE
            fullImage.setImageResource(imag1_res)

            pictures.visibility = View.INVISIBLE
        }
        image_twoS!!.setOnClickListener { v: View? ->

            full.visibility = View.VISIBLE
            fullImage.setImageResource(imag2_res)
            pictures.visibility = View.INVISIBLE

        }
        image_threeS!!.setOnClickListener { v: View? ->
            full.visibility = View.VISIBLE
            fullImage.setImageResource(imag3_res)
            pictures.visibility = View.INVISIBLE
        }
        image_fourS!!.setOnClickListener { v: View? ->
            full.visibility = View.VISIBLE

            fullImage.setImageResource(imag4_res)

            pictures.visibility = View.INVISIBLE
        }
        fullImage.setOnClickListener { v: View? ->

            full.visibility = View.INVISIBLE
            pictures.visibility = View.VISIBLE
        }
    }

    override fun showVariant(index: Int) {
        YoYo.with(Techniques.BounceIn).duration(600)
            .playOn(variantButtons[index])
        variantButtons[index].visibility = View.VISIBLE
    }

    override fun hideVariant(index: Int) {

        YoYo.with(Techniques.FadeOut).duration(600)
            .playOn(variantButtons[index])
        variantButtons[index].visibility = View.INVISIBLE
    }

    override fun isShownVariant(index: Int): Boolean {
        return variantButtons[index].visibility == View.VISIBLE
    }

    override fun removeDialog() {

        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            LayoutInflater.from(requireContext()).inflate(R.layout.delete_dialog, null, false)
        dialog.setCancelable(false)

        dialogBinding.yes.setOnClickListener { it ->
            presenter.onYesClick()
            delete.alpha = 0.5f
            delete.isClickable = false

            dialog.cancel()
        }

        dialogBinding.cancel.setOnClickListener { v ->


            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(dialogBinding)
        dialog.show()
    }

    override fun animate() {
        YoYo.with(Techniques.Shake).duration(900)
            .playOn(layout_answer)

    }

    override fun warningDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            LayoutInflater.from(requireContext()).inflate(R.layout.warning_dialog, null, false)
        dialog.setCancelable(false)

        dialogBinding.yes.setOnClickListener { it ->

            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(dialogBinding)
        dialog.show()
    }

    override fun winDialog() {
        isWin = true
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            LayoutInflater.from(requireContext()).inflate(R.layout.win_dialog, null, false)
        dialog.setCancelable(false)
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent);


        dialogBinding.yes.setOnClickListener { it ->
            sharedPreference?.level = 0
            findNavController().navigate(R.id.action_gameScreen_to_mainScreen)

            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(dialogBinding)
        dialog.show()
    }

    private fun hideZoom() {
        full.visibility = View.INVISIBLE
        pictures.visibility = View.VISIBLE
    }

    override fun resultDialog(level: Int, coinCount: Int, answer: String?) {
        hideZoom()
        val dialog = AlertDialog.Builder(requireContext()).create()
        isNext = true
        val dialogBinding =
            LayoutInflater.from(requireContext()).inflate(R.layout.result_dialog, null, false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        model = ModelImpl()
        dialog.setCancelable(false)
        dialogBinding.answers.text = answer
        dialogBinding.coinsCount.text = coinCount.toString() + ""
        dialogBinding.continueGame.setOnClickListener {
            isNext = false
            presenter.onClickContinue()
            dialog.cancel()
            delete.alpha = 1f
            delete.isClickable = true
        }

        dialog.setView(dialogBinding)
        dialog.show()
    }

    override fun onPause() {
        super.onPause()
        if (isWin) {
            sharedPreference?.level = 0

        }
        if (isNext) {
            presenter.onClickContinue()

        }
    }

    override fun onStop() {
        super.onStop()
        if (isWin) {
            sharedPreference?.level = 0

        }
        if (isNext) {
            presenter.onClickContinue()

        }
    }

    override fun langState(language: String) {

    }

    internal interface OnClickListener {
        fun onClick(index: Int)
    }

    private fun loadButtons(buttons: MutableList<Button>, groupId: Int, listener: OnClickListener) {
        val group: ViewGroup = views.findViewById(groupId)
        val oldSize = buttons.size
        for (i in 0 until group.childCount) {
            val view = group.getChildAt(i)
            if (view is Button) {
                val button = view
                val index = oldSize + i
                button.setOnClickListener { v: View? ->

                    listener.onClick(index)
                }
                buttons.add(button)
            }
        }
    }

}