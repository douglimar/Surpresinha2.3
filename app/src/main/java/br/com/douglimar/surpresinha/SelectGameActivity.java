package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SelectGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        LinearLayout linearLayout = findViewById(R.id.linearSelectGame);

        Button btnJogoUnico = findViewById(R.id.btnSingleGame);
        Button btnJogosMultiplos = findViewById(R.id.btnMultipleGames);

        final Surpresinha surpresinha = new Surpresinha();

        Intent intent = getIntent();

        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView tvTitulo = findViewById(R.id.tvSelectGameTitle2);

        tvTitulo.setText(message);

        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = findViewById(R.id.adViewSelect2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btnJogoUnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getBaseContext(), ResultActivity.class);

                intent2.putExtra(MainActivity.EXTRA_MESSAGE, message);
                intent2.putExtra(MainActivity.EXTRA_MESSAGE2, openActivity2(surpresinha,message));

                startActivity(intent2);

                //openActivity2(surpresinha, message);

            }
        });

        btnJogosMultiplos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(), ConfiguradorActivity.class);

                intent1.putExtra(MainActivity.EXTRA_MESSAGE, message);

                startActivity(intent1);

            }
        });


        switch (message) {

            case "MEGA-SENA": {

                //linearLayout.setBackgroundResource(R.color.colorMegasena);
                linearLayout.setBackgroundResource(R.drawable.degrade_radial_megasena);

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

    private String generateMultipleBets(Surpresinha pSurpresinha, String pMessage, int iQtd) {

        String retorno = "";

        for(int i = 0; i == iQtd; i++) {

            switch (pMessage) {
                case "MEGA-SENA": {

                    retorno = retorno + "\n\n" + pSurpresinha.generateMegasenaGame();

                    break;
                }
                case "QUINA": {
                    retorno = pSurpresinha.generateQuinaGame();

                    break;
                }
                case "LOTOFÁCIL": {

                    retorno = retorno + "\n\n" + pSurpresinha.generateLotofacilGame();

                    break;
                }
                case "LOTOMANIA": {

                    retorno = retorno + "\n\n" + pSurpresinha.generateLotomaniaGame();
                    break;
                }
                case "DUPLA-SENA": {

                    retorno = retorno + "\n\n" + pSurpresinha.generateDuplaSenaGame();

                    break;
                }
            }
        }



        return  retorno;

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

        }

        return  retorno;

    }


}
