package cymru.cribarth.musicplayer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar

class SettingsActivity : AppCompatActivity() {

    private lateinit var serverIpEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var languageSwitch: TextView
    private lateinit var languageSwitchCy: TextView
    
    private var currentLanguage = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        serverIpEditText = findViewById(R.id.serverIpEditText)
        saveButton = findViewById(R.id.saveButton)
        languageSwitch = findViewById(R.id.languageSwitchEn)
        languageSwitchCy = findViewById(R.id.languageSwitchCy)

        loadSavedConfig()
        setupLanguageSwitch()
        setupSaveButton()
    }

    private fun loadSavedConfig() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val serverIp = prefs.getString("server_ip", null)
        val language = prefs.getString("language", "en")
        
        currentLanguage = language ?: "en"
        
        serverIp?.let {
            serverIpEditText.setText(it)
        }
        
        updateLanguageUI()
    }

    private fun setupLanguageSwitch() {
        languageSwitch.setOnClickListener {
            if (currentLanguage != "en") {
                currentLanguage = "en"
                updateLanguageUI()
            }
        }

        languageSwitchCy.setOnClickListener {
            if (currentLanguage != "cy") {
                currentLanguage = "cy"
                updateLanguageUI()
            }
        }
    }

    private fun updateLanguageUI() {
        val isEnglish = currentLanguage == "en"
        
        languageSwitch.text = getString(R.string.language_english)
        languageSwitchCy.text = getString(R.string.language_welsh)
        
        if (isEnglish) {
            languageSwitch.setTextColor(getColor(android.R.color.primary))
            languageSwitchCy.setTextColor(getColor(android.R.color.secondaryText))
        } else {
            languageSwitch.setTextColor(getColor(android.R.color.secondaryText))
            languageSwitchCy.setTextColor(getColor(android.R.color.primary))
        }

        // Update all text elements based on language
        val titleRes = if (isEnglish) R.string.title_settings else R.string.title_settings_cy
        val labelRes = if (isEnglish) R.string.server_ip_label else R.string.server_ip_label_cy
        val hintRes = if (isEnglish) R.string.server_ip_hint else R.string.server_ip_hint_cy
        val buttonRes = if (isEnglish) R.string.save_connect else R.string.save_connect_cy

        findViewById<TextView>(R.id.settingsTitle).setText(titleRes)
        findViewById<TextView>(R.id.serverIpLabel).setText(labelRes)
        serverIpEditText.hint = getString(hintRes)
        saveButton.text = getString(buttonRes)
    }

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            val serverIp = serverIpEditText.text.toString().trim()

            // Basic IP validation
            val ipRegex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)".toRegex()
            val isLocal = serverIp == "localhost"

            if (!ipRegex.matches(serverIp) && !isLocal) {
                val errorRes = if (currentLanguage == "en") R.string.invalid_ip_error else R.string.invalid_ip_error_cy
                Snackbar.make(findViewById(android.R.id.content), getString(errorRes), Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Return result to MainActivity
            val resultIntent = Intent().apply {
                putExtra("server_ip", serverIp)
                putExtra("language", currentLanguage)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}