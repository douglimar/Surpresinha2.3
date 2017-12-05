package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = new String ("br.com.douglimar.surpresinha.MESSAGE");

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        LinearLayout linearMegaSena  = findViewById(R.id.linearMegaSena);
        LinearLayout linearQuina     = findViewById(R.id.linearQuina);
        LinearLayout linearLotofacil = findViewById(R.id.linearLotofacil);
        LinearLayout linearLotomania = findViewById(R.id.linearLotomania);
        LinearLayout linearDuplasena = findViewById(R.id.linearDuplaSena);

        linearMegaSena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity("MEGA-SENA");
            }
        });

        linearQuina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity("QUINA");
            }
        });

        linearLotofacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity("LOTOFÁCIL");
            }
        });

        linearLotomania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity2("LOTOMANIA");
            }
        });

        linearDuplasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity2("DUPLA-SENA");
            }
        });

        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void openActivity(String pMessage) {

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, pMessage);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, pMessage);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SelectGameModeActivity.class);
        intent.putExtra(EXTRA_MESSAGE, pMessage);

        startActivity(intent);


    }



    private void openActivity2(String pMessage) {

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, pMessage);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, pMessage);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_MESSAGE, pMessage);

        startActivity(intent);


    }

}