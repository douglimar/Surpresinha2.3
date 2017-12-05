package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConfiguradorActivity extends AppCompatActivity {

    private int iCount = 2;
    TextView tvNumero, tvConfiguratorTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurador);

        LinearLayout linearConfigurator = findViewById(R.id.linearConfigurador);

        tvNumero = findViewById(R.id.tvNumero);
        tvNumero.setText(String.valueOf(iCount));
        tvConfiguratorTitle = findViewById(R.id.tvConfiguratorTitle);

        Intent intent = getIntent();

        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        tvConfiguratorTitle.setText(message);

        switch (message) {

            case "MEGA-SENA": {

                //linearLayout.setBackgroundResource(R.color.colorMegasena);

                linearConfigurator.setBackgroundResource(R.drawable.degrade_radial_megasena);

                break;
            }
            case "QUINA": {

                linearConfigurator.setBackgroundResource(R.color.colorQuina);

                break;
            }
            case "LOTOFÁCIL": {

                linearConfigurator.setBackgroundResource(R.color.colorLotofacil);

                break;
            }
            case "LOTOMANIA": {

                linearConfigurator.setBackgroundResource(R.color.colorLotomania);

                break;
            }
            case "DUPLA-SENA": {

                linearConfigurator.setBackgroundResource(R.color.colorDuplasena);

                break;
            }
        }



        Button btnMinus = findViewById(R.id.btnMinus);

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iCount--;

                if (iCount < 2) {
                    Toast.makeText(getApplicationContext(), "Você tem que gerar pelo menos 2 jogos aqui.", Toast.LENGTH_SHORT).show();
                    iCount = 2;
                }
                tvNumero.setText(String.valueOf(iCount));
            }
        });


        Button btnPlus = findViewById(R.id.btnPlus);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iCount++;

                if (iCount >10) {
                    iCount = 10;
                    Toast.makeText(getApplicationContext(), "Você tem que gerar NO MÁXIMO 10 jogos", Toast.LENGTH_SHORT).show();
                }
                tvNumero.setText(String.valueOf(iCount));
            }
        });


        Button btnMultiBets = findViewById(R.id.btnGenerateMultipleBets);

        btnMultiBets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Surpresinha surpresinha = new Surpresinha();

                Intent intent1 = new Intent(getBaseContext(), ResultActivity.class);

                intent1.putExtra(MainActivity.EXTRA_MESSAGE, message);
                intent1.putExtra(MainActivity.EXTRA_MESSAGE2,  generateMultipleBets(surpresinha, message, iCount));

                startActivity(intent1);

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

    private String generateMultipleBets(Surpresinha pSurpresinha, String pMessage, int iQtd) {

        String retorno = "";
        int iControle;

        for(int i = 0; i < iQtd; i++) {

            iControle = i+1;

            switch (pMessage) {
                case "MEGA-SENA": {

                    retorno = retorno + "\n\nJogo " + iControle+ "\n---------------\n\n" + pSurpresinha.generateMegasenaGame();

                    break;
                }
                case "QUINA": {
                    retorno = retorno + "\n\nJogo " + iControle + "\n---------------\n\n" +pSurpresinha.generateQuinaGame();

                    break;
                }
                case "LOTOFÁCIL": {

                    retorno = retorno + "\n\nJogo " + iControle + "\n---------------\n\n" + pSurpresinha.generateLotofacilGame();

                    break;
                }
                case "LOTOMANIA": {

                    retorno = retorno + "\n\nJogo " + iControle + "\n---------------\n\n" + pSurpresinha.generateLotomaniaGame();
                    break;
                }
                case "DUPLA-SENA": {

                    retorno = retorno + "\n\nJogo " + iControle + "\n---------------\n\n" + pSurpresinha.generateDuplaSenaGame();

                    break;
                }
            }
        }



        return  retorno + "\n\n\n";

    }


}
