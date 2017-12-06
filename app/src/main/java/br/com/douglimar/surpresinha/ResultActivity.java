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
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        tvResult = findViewById(R.id.tvResult);
        CoordinatorLayout linearResult = findViewById(R.id.linearResult);

        Intent intent = getIntent();

        String all = intent.getExtras().toString();


        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final String numerosGerados = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        int iQtdeDeJogos = 1;
        iQtdeDeJogos = intent.getIntExtra("XPTO", 0);

        this.setTitle(message);

        final Surpresinha surpresinha = new Surpresinha();

        linearResult.setBackgroundResource(surpresinha.getBackgroundColors(message)[0]);
        appBarLayout.setBackgroundResource(surpresinha.getBackgroundColors(message)[1]);

        url = surpresinha.getUrl(message);

        tvResult.setText(numerosGerados);

        Toast.makeText(getBaseContext(), "Clique no botão azul para visualizar os últimos resultados.", Toast.LENGTH_LONG).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        final int finalIQtdeDeJogos = iQtdeDeJogos;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gerando novos números da sorte.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                tvResult.setText(generateMultiBets(surpresinha, message, finalIQtdeDeJogos));

            }
        });


        FloatingActionButton fabShare = findViewById(R.id.fab2);

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Compartilhando", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                shareContent(tvResult.getText().toString());

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

    private String generateMultiBets(Surpresinha pSurpresinha, String pMessage, int iQtd) {

        String retorno = "";
        String sQuebralinha = "\n____________________\n";
        int iControle;

        for(int i = 0; i < iQtd; i++) {

            iControle = i+1;

            switch (pMessage) {
                case "MEGA-SENA": {
                    retorno = retorno + "\nJogo " + iControle+ "\n\n"+ pSurpresinha.generateMegasenaGame() +sQuebralinha;
                    break;
                }
                case "QUINA": {
                    retorno = retorno + "\nJogo " + iControle + "\n\n" +pSurpresinha.generateQuinaGame() + sQuebralinha ;
                    break;
                }
                case "LOTOFÁCIL": {
                    retorno = retorno + "\nJogo " + iControle + "\n\n" + pSurpresinha.generateLotofacilGame() + sQuebralinha ;
                    break;
                }
                case "LOTOMANIA": {
                    retorno = retorno + "\nJogo " + iControle + "\n\n" + pSurpresinha.generateLotomaniaGame() + sQuebralinha ;
                    break;
                }
                case "DUPLA-SENA": {
                    retorno = retorno + "\nJogo " + iControle + "\n\n" + pSurpresinha.generateDuplaSenaGame() + sQuebralinha ;
                    break;
                }
            }
        }

        return  retorno + "\n\n\n\n\n";
    }

    public void shareContent(String pMessage) {


        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Surpresinha");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, pMessage + "\n" + "https://play.google.com/store/apps/details?id=br.com.douglimar.surpresinha_megasena");
        startActivity(Intent.createChooser(sharingIntent, "Compartilhar com..."));
    }


}
