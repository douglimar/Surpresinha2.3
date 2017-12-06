package br.com.douglimar.surpresinha;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        TextView tvResult = findViewById(R.id.tvResult);
        CoordinatorLayout linearResult = findViewById(R.id.linearResult);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final String numerosGerados = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        this.setTitle(message);

        switch (message) {

            case "MEGA-SENA": {

                linearResult.setBackgroundResource(R.color.colorMegasena);
                appBarLayout.setBackgroundResource(R.drawable.degrade_radial_megasena);
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/megasena";

                break;
            }
            case "QUINA": {

                linearResult.setBackgroundResource(R.color.colorQuina);
                appBarLayout.setBackgroundResource(R.drawable.degrade_radial_quina);
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/quina";

                break;
            }
            case "LOTOFÁCIL": {

                linearResult.setBackgroundResource(R.color.colorLotofacil);
                appBarLayout.setBackgroundResource(R.drawable.degrade_radial_lotofacil);
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/lotofacil";

                break;
            }
            case "LOTOMANIA": {

                linearResult.setBackgroundResource(R.color.colorLotomania);
                appBarLayout.setBackgroundResource(R.drawable.degrade_radial_lotomania);
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/lotomania";

                break;
            }
            case "DUPLA-SENA": {

                linearResult.setBackgroundResource(R.color.colorDuplasena);
                appBarLayout.setBackgroundResource(R.drawable.degrade_radial_duplasena);
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/duplasena";

                break;
            }
        }

        tvResult.setText(numerosGerados);

        Toast.makeText(getBaseContext(), "Clique no botão azul para visualizar os últimos resultados.", Toast.LENGTH_LONG).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                carregaWebView(url, message);
            }
        });
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


    public void carregaWebView(String url, String pMessage) {

        setContentView(R.layout.activity_webview);

        WebView myWebView;

        WebViewClient myWebViewClient = new WebViewClient() {

            ProgressDialog progressDialog = new ProgressDialog(ResultActivity.this);

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                progressDialog.setMessage("Aguarde... Carregando Resultados");
                progressDialog.setCancelable(false);

                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){

                    //@Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                        if(keyCode == KeyEvent.KEYCODE_SEARCH)
                            return true;
                        else
                            return false;
                    }
                });

                progressDialog.show();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                progressDialog.setMessage("Aguarde... Carregando Resultados");
                progressDialog.setCancelable(false);

                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){
                    //@Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                        if(keyCode == KeyEvent.KEYCODE_SEARCH)
                            return true;
                        else
                            return false;
                    }});

                progressDialog.show();
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

			/* @Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				if (url.contains("www1.caixa.gov.br") == true )
					return false;

				return true;

			} */
        };

        // Get Web view
        myWebView = (WebView) findViewById( R.id.webView1 ); //This is the id you gave
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setSupportZoom(true);       //Zoom Control on web (You don't need this if ROM supports Multi-Touch
        myWebView.getSettings().setBuiltInZoomControls(true); //Enable Multitouch if supported by ROM
        myWebView.getSettings().setLoadsImagesAutomatically(true); // Dont loud the images
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(false);


        if ( pMessage.equals("LOTOMANIA") || pMessage.equals("LOTOFACIL") )
            myWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        else
            myWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        myWebView.setWebViewClient(myWebViewClient);

        // Load URL
        myWebView.loadUrl(url);

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar1);

        btnVoltar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });



        LinearLayout llWeb = (LinearLayout) findViewById(R.id.llWebview);

    }

}
