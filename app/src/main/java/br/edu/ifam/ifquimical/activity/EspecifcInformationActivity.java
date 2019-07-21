package br.edu.ifam.ifquimical.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.edu.ifam.ifquimical.R;

public class EspecifcInformationActivity extends AppCompatActivity {

    private TextView textInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especifc_information);

        textInformation = findViewById(R.id.textInformation);

        // Obtém os dados da intent.
        Bundle data = getIntent().getExtras();
        String title = data.getString("title");
        String textData = data.getString("textData");

        textInformation.setText(textData);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
