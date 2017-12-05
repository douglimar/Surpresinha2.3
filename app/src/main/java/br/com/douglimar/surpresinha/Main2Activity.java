package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Main2Activity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE = new String ("br.com.douglimar.surpresinha.MESSAGE");

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        AdView adView = findViewById(R.id.adViewMain2);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Button btnMega = findViewById(R.id.btnMegasena);
        Button btnLotofacil = findViewById(R.id.btnLotofacil);
        Button btnLotomania = findViewById(R.id.btnLotomania);
        Button btnQuina = findViewById(R.id.btnQuina);
        Button btnDuplaSena = findViewById(R.id.btnDuplaSena);

        btnMega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("MEGA-SENA");
            }
        });

        btnLotofacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("LOTOFÁCIL");
            }
        });

        btnLotomania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("LOTOMANIA");

            }

        });

        btnQuina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("QUINA");
            }
        });


        btnDuplaSena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("DUPLA-SENA");
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

    private void openActivity(String pMessage) {

/*        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, pMessage);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, pMessage);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
(*/
        Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SelectGameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, pMessage);

        startActivity(intent);


    }



    private void openActivity2(String pMessage) {
/*
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, pMessage);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, pMessage);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
*/
        Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SelectGameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, pMessage);

        startActivity(intent);


    }
}
