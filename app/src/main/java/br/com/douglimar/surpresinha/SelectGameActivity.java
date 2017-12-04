package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

                openActivity(surpresinha, message);

            }
        });

        btnJogosMultiplos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(), ConfiguradorActivity.class);

                startActivity(intent1);

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
        }
    }

    private void openActivity(Surpresinha pSurpresinha, String pMessage) {

        switch (pMessage) {
            case "MEGA-SENA": {

                Toast.makeText(this,pSurpresinha.generateMegasenaGame(), Toast.LENGTH_SHORT).show();

                break;
            }
            case "QUINA": {
                Toast.makeText(this,pSurpresinha.generateQuinaGame(), Toast.LENGTH_SHORT).show();

                break;
            }
            case "LOTOFÁCIL": {
                Toast.makeText(this,pSurpresinha.generateLotofacilGame(), Toast.LENGTH_SHORT).show();

                break;
            }
            case "LOTOMANIA": {

                Toast.makeText(this,pSurpresinha.generateLotomaniaGame(), Toast.LENGTH_SHORT).show();
                break;
            }
            case "DUPLA-SENA": {

                Toast.makeText(this,pSurpresinha.generateDuplaSenaGame(), Toast.LENGTH_SHORT).show();

                break;
            }

        }

    }

}
