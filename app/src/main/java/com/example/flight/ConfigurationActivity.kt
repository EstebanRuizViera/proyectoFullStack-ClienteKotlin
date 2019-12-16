package com.example.flight

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_configuration.*
import java.util.*


class ConfigurationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        val intent = Intent(this,SearchActivity::class.java)

        spanish_textView.setOnClickListener{
            val localizacion = Locale("es", "ES")

            Locale.setDefault(localizacion)
            val config = Configuration()
            config.locale = localizacion
            baseContext.resources
                .updateConfiguration(config, baseContext.resources.displayMetrics)
            startActivity(intent)
        }

        english_textView.setOnClickListener{
            val localizacion = Locale("en", "EN")

            Locale.setDefault(localizacion)
            val config = Configuration()
            config.locale = localizacion
            baseContext.resources
                .updateConfiguration(config, baseContext.resources.displayMetrics)
            startActivity(intent)
        }

    }
}
