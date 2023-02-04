package com.azamovhudstc.a4pic1word.screen

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.a4pic1word.BuildConfig
import com.azamovhudstc.a4pic1word.R
import com.azamovhudstc.a4pic1word.contact.MainScreenContract
import com.azamovhudstc.a4pic1word.impl.main.MainModelImpl
import com.azamovhudstc.a4pic1word.impl.main.MainPresenterImpl
import com.azamovhudstc.a4pic1word.utils.MySharedPreference
import kotlinx.android.synthetic.main.info_dialog.view.*
import kotlinx.android.synthetic.main.main_screen.*
import java.util.*

class MainScreen : Fragment(R.layout.main_screen), MainScreenContract.View {
    private lateinit var presenter: MainScreenContract.Presenter
    private var sharedPreference: MySharedPreference? = null
    private lateinit var buttonsSound: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = MySharedPreference.getInstance(requireContext())
        presenter = MainPresenterImpl(MainModelImpl(), this, requireContext())
        (presenter as MainPresenterImpl).init()

        coinCount()
        play_info.setOnClickListener {
            infoDialog()
        }
        exit.setOnClickListener {
                val dialog = Dialog(requireContext())
                val view: View = LayoutInflater.from(requireContext())
                    .inflate(R.layout.exit_dialog, container, false)
                dialog.setContentView(view)

                view.findViewById<View>(R.id.button_exit_no).setOnClickListener { view1: View? ->
                    dialog.cancel()
                    dialog.dismiss()
                }
                view.findViewById<View>(R.id.button_exit_yes)
                    .setOnClickListener { view2: View? ->
                        dialog.cancel()
                        dialog.dismiss()
                        val a = Intent(Intent.ACTION_MAIN)
                        a.addCategory(Intent.CATEGORY_HOME)
                        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(a)
                    }
                dialog.show()

                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        }


        play.setOnClickListener {

            (presenter as MainPresenterImpl).onClickPlay(
                requireView()
            )
        }

        setting.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)

            sharingIntent.type = "text/plain"
            val shareBody = "4 pic 1 word Game Sharing With"
            val shareSubject = "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID.toString()}"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareSubject)
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
            startActivity(Intent.createChooser(sharingIntent, "Share using"))

        }

    }


    override fun coinCount() {
        coinsCount.text = sharedPreference?.coinCount.toString()
    }

    override fun loadImageCurrentLevel(
        resIdOne: Int,
        resIdTwo: Int,
        resIdThree: Int,
        resIdFour: Int
    ) {
        image_one.setImageResource(resIdOne)
        image_two.setImageResource(resIdTwo)
        image_three.setImageResource(resIdThree)
        image_four.setImageResource(resIdFour)
    }

    override fun currentLevel(levels: Int) {
        level.text = levels.toString()
    }

    override fun settingDialog() {

    }


    override fun infoDialog() {

        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            LayoutInflater.from(requireContext()).inflate(R.layout.info_dialog, container, false)

        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogBinding.yes.setOnClickListener {

            dialog.cancel()
        }
        dialog.setView(dialogBinding)
        dialog.show()
    }

    override fun langDialog() {

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        text.text = requireContext().resources.getString(R.string.game_name)
        play.text = requireContext().resources.getString(R.string.play)
        exit.text = requireContext().resources.getString(R.string.exit)
    }

}