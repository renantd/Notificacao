package br.dev.com.notificacao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.dev.com.notificacao.databinding.ActivityMainBinding
import br.dev.com.notificacao.ui.notificacao.Notificacao


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var n = Notificacao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = initBinding()

        // inicializa a classe
        n.init(binding.root.context)

        binding.btnEnviarNotificacaoBasica.setOnClickListener {
            n.solicitarNotificacaoBasica("Facial Maps" , "Teste" , R.drawable.icon_notification_facialmaps_custom)
        }

        binding.btnEnviarNotificacaoTextoLongo.setOnClickListener {
            n.solicitarNotificacaoTextoLongo("Facial Maps" , "Much longer text that cannot fit one line..." , R.drawable.icon_notification_facialmaps_custom)
        }

        binding.btnEnviarNotificacaoPendente.setOnClickListener {
            n.solicitarNotificacaoPendente("Facial Maps" , "Much longer text that cannot fit one line..." , R.drawable.icon_notification_facialmaps_custom)
        }
    }

    private fun initBinding(): ActivityMainBinding {
        return DataBindingUtil.setContentView(this , R.layout.activity_main)
    }

}