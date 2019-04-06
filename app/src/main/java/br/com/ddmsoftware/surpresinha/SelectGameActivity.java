package br.com.ddmsoftware.surpresinha;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class SelectGameActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        LinearLayout linearLayout = findViewById(R.id.linearSelectGame);

        Button btnJogoUnico = findViewById(R.id.btnSingleGame);
        Button btnJogosMultiplos = findViewById(R.id.btnMultipleGames);
        Button btnLastResults = findViewById(R.id.btnLastResults);

        final Surpresinha surpresinha = new Surpresinha();
        final Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView tvTitulo = findViewById(R.id.tvSelectGameTitle2);
        tvTitulo.setText(message);

        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = findViewById(R.id.adViewSelect2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btnJogoUnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "SelectGame");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "JogoUnicoClick");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                Intent intent2 = new Intent(getBaseContext(), ResultActivity.class);

                intent2.putExtra(MainActivity.EXTRA_MESSAGE, message);
                intent2.putExtra(MainActivity.EXTRA_MESSAGE2, openActivity2(surpresinha,message));
                intent2.putExtra("XPTO", 1);

                startActivity(intent2);
            }
        });

        btnJogosMultiplos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "SelectGame");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "JogoMultiplo");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                Intent intent1 = new Intent(getApplicationContext(), ConfiguradorActivity.class);
                intent1.putExtra(MainActivity.EXTRA_MESSAGE, message);

                startActivity(intent1);
            }
        });

        btnLastResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isNetworkAvailable())
                    carregaWebView(surpresinha.getUrl(message), message);
                else
                    Toast.makeText(getApplicationContext(), R.string.internet_conn,Toast.LENGTH_LONG).show();

            }
        });


        switch (message) {

            case "MEGA-SENA": {
                linearLayout.setBackgroundResource(R.color.colorMegasena);
                break;
            }
            case "QUINA": {
                linearLayout.setBackgroundResource(R.color.colorQuina);
                break;
            }
            case "LOTOFÁCIL": {
                linearLayout.setBackgroundResource(R.color.colorLotofacil);
                break;
            }
            case "LOTOMANIA": {
                linearLayout.setBackgroundResource(R.color.colorLotomania);
                break;
            }
            case "DUPLA-SENA": {
                linearLayout.setBackgroundResource(R.color.colorDuplasena);
                break;
            }
            case "DIA-DE-SORTE": {
                linearLayout.setBackgroundResource(R.color.colorDiaDeSorte);
                break;
            }

        }

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

    private String openActivity2(Surpresinha pSurpresinha, String pMessage) {

        String retorno = "";

        switch (pMessage) {
            case "MEGA-SENA": {
                retorno = pSurpresinha.generateMegasenaGame();
                break;
            }
            case "QUINA": {
                retorno = pSurpresinha.generateQuinaGame();
                break;
            }
            case "LOTOFÁCIL": {
                retorno = pSurpresinha.generateLotofacilGame();
                break;
            }
            case "LOTOMANIA": {
                retorno = pSurpresinha.generateLotomaniaGame();
                break;
            }
            case "DUPLA-SENA": {
                retorno = pSurpresinha.generateDuplaSenaGame();
                break;
            }
            case "DIA-DE-SORTE": {
                retorno = pSurpresinha.generateDiaDeSorteGame();
                break;
            }
        }
        return retorno;
    }

    private void carregaWebView(String url, String pMessage) {

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "SelectGame");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "CarregaWebView");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


        setContentView(R.layout.activity_webview);

        WebView myWebView;

        AdView adViewWeb = findViewById(R.id.adViewWebview);
        AdRequest adRequestWeb = new AdRequest.Builder().build();
        adViewWeb.loadAd(adRequestWeb);

        WebViewClient myWebViewClient = new WebViewClient() {

            final ProgressDialog progressDialog = new ProgressDialog(SelectGameActivity.this);

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                progressDialog.setMessage("Aguarde... Carregando Resultados");
                progressDialog.setCancelable(false);

                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){

                    //@Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        return keyCode == KeyEvent.KEYCODE_SEARCH;
                    }
                });

                progressDialog.show();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                progressDialog.setMessage("Aguarde... Carregando Resultados na internet");
                progressDialog.setCancelable(true);

                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){
                    //@Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                        return keyCode == KeyEvent.KEYCODE_SEARCH;
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
        myWebView = findViewById( R.id.webView1 ); //This is the id you gave
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


        LinearLayout llWeb = findViewById(R.id.llWebview);

    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
