package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE  = "br.com.douglimar.surpresinha.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "br.com.douglimar.surpresinha.MESSAGE2";

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        AdView adView = findViewById(R.id.adViewMain);
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
                //openActivity("MEGA-SENA");
                openActivity(getString(R.string.megasena));
            }
        });

        btnLotofacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(getString(R.string.lotofacil));
            }
        });

        btnLotomania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(getString(R.string.lotomania));

            }

        });

        btnQuina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(getString(R.string.quina));
            }
        });


        btnDuplaSena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(getString(R.string.duplasena));
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

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, pMessage);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, pMessage);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SelectGameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, pMessage);

        startActivity(intent);
    }
}
