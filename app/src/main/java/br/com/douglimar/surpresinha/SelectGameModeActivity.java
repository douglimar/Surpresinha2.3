package br.com.douglimar.surpresinha;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class SelectGameModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game_mode);

        LinearLayout linearSelectGameMain = findViewById(R.id.linearSelectGameMain);

        LinearLayout linearJogoUnico = findViewById(R.id.linearSingleSelectGame);
        LinearLayout linearMultiplosJogos = findViewById(R.id.linearMultiSelectGame);


        final Surpresinha surpresinha = new Surpresinha();

        ImageView imageSingleGame = findViewById(R.id.imgSingleGameThumb);
        ImageView imageMultiGames = findViewById(R.id.imgMultiGameThumb);

        Intent intent = getIntent();

        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView tvTitulo = findViewById(R.id.tvSelectGameTitle);

        tvTitulo.setText(message);

        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = findViewById(R.id.adViewSelect);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        linearJogoUnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity(surpresinha, message);

            }
        });

        linearMultiplosJogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(), ConfiguradorActivity.class);

                startActivity(intent1);

            }
        });


        switch (message) {

            case "MEGA-SENA": {

                //tvTitulo.setTextColor(getResources().getColor(R.color.colorMegasena));

                linearSelectGameMain.setBackgroundResource(R.color.colorMegasena);
                //linearJogoUnico.setBackgroundResource(R.color.colorMegasenaLight);
                imageSingleGame.setImageResource(R.drawable.megasena_ico);

                //linearMultiplosJogos.setBackgroundResource(R.color.colorMegasenaLight);
                imageMultiGames.setImageResource(R.drawable.megasena_ico);

                break;
            }
            case "QUINA": {

                //tvTitulo.setTextColor(getResources().getColor(R.color.colorQuina));

                linearSelectGameMain.setBackgroundResource(R.color.colorQuina);
                //linearJogoUnico.setBackgroundResource(R.color.colorQuina);
                imageSingleGame.setImageResource(R.drawable.quina_ico);

                //linearMultiplosJogos.setBackgroundResource(R.color.colorQuina);
                imageMultiGames.setImageResource(R.drawable.quina_ico);

                break;
            }
            case "LOTOFÁCIL": {

                //tvTitulo.setTextColor(getResources().getColor(R.color.colorLotofacil));
                linearSelectGameMain.setBackgroundResource(R.color.colorLotofacil);

                //linearJogoUnico.setBackgroundResource(R.color.colorLotofacil);
                imageSingleGame.setImageResource(R.drawable.lotofacil_ico);

                //linearMultiplosJogos.setBackgroundResource(R.color.colorLotofacil);
                imageMultiGames.setImageResource(R.drawable.lotofacil_ico);

                break;
            }
            case "LOTOMANIA": {

                //tvTitulo.setTextColor(getResources().getColor(R.color.colorLotomania));

                linearSelectGameMain.setBackgroundResource(R.color.colorLotomania);

                //linearJogoUnico.setBackgroundResource(R.color.colorLotomania);
                imageSingleGame.setImageResource(R.drawable.lotomania_ico);

                //linearMultiplosJogos.setBackgroundResource(R.color.colorLotomania);
                imageMultiGames.setImageResource(R.drawable.lotomania_ico);

                break;
            }
            case "DUPLA-SENA": {

                //tvTitulo.setTextColor(getResources().getColor(R.color.colorDuplasena));

                linearSelectGameMain.setBackgroundResource(R.color.colorDuplasena);

                //linearJogoUnico.setBackgroundResource(R.color.colorDuplasena);
                imageSingleGame.setImageResource(R.drawable.duplasena_ico);

                //linearMultiplosJogos.setBackgroundResource(R.color.colorDuplasena);
                imageMultiGames.setImageResource(R.drawable.duplasena_ico);

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
