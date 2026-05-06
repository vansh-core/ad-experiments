package com.example.exp6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    EditText etUrl;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        etUrl   = findViewById(R.id.etUrl);
        btnGo   = findViewById(R.id.btnGo);

        // VERY IMPORTANT: Without this, links open in Chrome (external browser)
        // With this, ALL links open INSIDE our WebView
        webView.setWebViewClient(new WebViewClient());

        // Enable JavaScript (needed for modern websites)
        // Note: review for XSS vulnerabilities if loading untrusted content
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load default URL on start
        webView.loadUrl("https://www.google.com");

        // GO button — load URL from EditText
        btnGo.setOnClickListener(v -> {
            String inputUrl = etUrl.getText().toString().trim();
            String finalUrl;

            // Add https:// if user forgot
            if (!inputUrl.startsWith("http://") && !inputUrl.startsWith("https://")) {
                finalUrl = "https://" + inputUrl;
            } else {
                finalUrl = inputUrl;
            }
            webView.loadUrl(finalUrl);
        });

        // Handle Back button using the modern OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new androidx.activity.OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack(); // Go back in browser history
                } else {
                    setEnabled(false); // Disable this callback
                    getOnBackPressedDispatcher().onBackPressed(); // Propagate to system (exit app)
                }
            }
        });
    }
}
