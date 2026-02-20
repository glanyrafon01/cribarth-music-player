package cymru.cribarth.musicplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var currentConfig: Config? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        setupWebView()

        loadConfig()
        checkAndNavigate()
    }

    private fun setupWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Hide loading indicator if needed
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: android.webkit.WebResourceRequest?): Boolean {
                request?.url?.let {
                    if (it.toString().startsWith("http://") || it.toString().startsWith("https://")) {
                        return false
                    }
                    // Handle external URLs
                    val intent = Intent(Intent.ACTION_VIEW, it)
                    startActivity(intent)
                    return true
                }
                return false
            }
        }
    }

    private fun loadConfig() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val serverIp = prefs.getString("server_ip", null)
        val language = prefs.getString("language", "en")

        currentConfig = Config(serverIp, language)
    }

    private fun saveConfig(config: Config) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        with(prefs.edit()) {
            putString("server_ip", config.serverIp)
            putString("language", config.language)
            apply()
        }
        currentConfig = config
    }

    private fun checkAndNavigate() {
        currentConfig?.let { config ->
            if (!config.serverIp.isNullOrEmpty()) {
                navigateToMusicAssistant(config.serverIp)
            } else {
                showSettingsScreen()
            }
        } ?: run {
            showSettingsScreen()
        }
    }

    private fun navigateToMusicAssistant(serverIp: String) {
        val url = "http://$serverIp:8095"
        webView.loadUrl(url)
    }

    private fun showSettingsScreen() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivityForResult(intent, REQUEST_SETTINGS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SETTINGS && resultCode == RESULT_OK) {
            data?.let {
                val serverIp = it.getStringExtra("server_ip")
                val language = it.getStringExtra("language") ?: "en"
                
                if (!serverIp.isNullOrEmpty()) {
                    val config = Config(serverIp, language)
                    saveConfig(config)
                    navigateToMusicAssistant(serverIp)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val REQUEST_SETTINGS = 1001
    }

    data class Config(
        val serverIp: String?,
        val language: String
    )
}