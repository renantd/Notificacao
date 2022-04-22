package br.dev.com.notificacao.ui.notificacao.resultado

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.dev.com.notificacao.R
import br.dev.com.notificacao.databinding.ActivityAfterNotificationBinding

class AfterNotification : AppCompatActivity() {

    private lateinit var binding : ActivityAfterNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_notification)

        binding = initBinding()

    }

    fun initBinding(): ActivityAfterNotificationBinding {
        return DataBindingUtil.setContentView(this , R.layout.activity_after_notification)
    }

}