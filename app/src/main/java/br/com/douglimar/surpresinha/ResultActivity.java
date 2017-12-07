package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AdView adView = findViewById(R.id.adViewResult);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        tvResult = findViewById(R.id.tvResult);
        CoordinatorLayout linearResult = findViewById(R.id.linearResult);

        Intent intent = getIntent();

        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final String numerosGerados = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        int iQtdeDeJogos = intent.getIntExtra("XPTO", 0);

        this.setTitle(message);

        final Surpresinha surpresinha = new Surpresinha();

        linearResult.setBackgroundResource(surpresinha.getBackgroundColors(message)[0]);
        appBarLayout.setBackgroundResource(surpresinha.getBackgroundColors(message)[1]);


        tvResult.setText(getString(R.string.numeros_da_sorte, numerosGerados));

        //Toast.makeText(getBaseContext(), "Clique no botão azul para gerar novos números sem ter que sair da tela.", Toast.LENGTH_LONG).show();
        
        Snackbar.make(findViewById(R.id.linearResult), "Clique no botão azul para gerar novos números sem ter que sair da tela", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();

        FloatingActionButton fab = findViewById(R.id.fab);
        final int finalIQtdeDeJogos = iQtdeDeJogos;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Snackbar.make(view, "Gerando novos números da sorte.", Snackbar.LENGTH_LONG)
              //          .setAction("Action", null).show();

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


    private String generateMultiBets(Surpresinha pSurpresinha, String pMessage, int iQtd) {

        String retorno = "Estes são os seus números da sorte:\n";
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

    private void shareContent(String pMessage) {


        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Surpresinha");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, pMessage + "\n" + getResources().getString(R.string.googlePlayURL));
        startActivity(Intent.createChooser(sharingIntent, "Compartilhar com..."));
    }


}
