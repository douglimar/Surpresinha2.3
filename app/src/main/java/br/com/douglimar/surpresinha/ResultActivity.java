package br.com.douglimar.surpresinha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final String numerosGerados = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        TextView tvResult = findViewById(R.id.tvResult);

        CoordinatorLayout linearResult = findViewById(R.id.linearResult);

        this.setTitle(message);

        switch (message) {

            case "MEGA-SENA": {

                //linearLayout.setBackgroundResource(R.color.colorMegasena);

                linearResult.setBackgroundResource(R.drawable.degrade_radial_megasena);

                break;
            }
            case "QUINA": {

                linearResult.setBackgroundResource(R.color.colorQuina);

                break;
            }
            case "LOTOFÁCIL": {

                linearResult.setBackgroundResource(R.color.colorLotofacil);

                break;
            }
            case "LOTOMANIA": {

                linearResult.setBackgroundResource(R.color.colorLotomania);

                break;
            }
            case "DUPLA-SENA": {

                linearResult.setBackgroundResource(R.color.colorDuplasena);

                break;
            }
        }

        tvResult.setText(numerosGerados);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
}
