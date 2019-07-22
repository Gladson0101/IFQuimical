package br.edu.ifam.ifquimical.activity;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import br.edu.ifam.ifquimical.R;

public class SpecificInformationActivity extends AppCompatActivity {

    private TextView textInformation;
    private TextToSpeech textToSpeech;
    private Button buttonReader;
    private String textData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_information);

        textInformation = findViewById(R.id.textInformation);

        // Obtém os dados da intent.
        Bundle data = getIntent().getExtras();
        String title = data.getString("title");
        textData = data.getString("textData");

        textInformation.setText(textData);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    Locale locale = new Locale("pt", "BR"); // Voz em pt-BR
                    textToSpeech.setLanguage(locale);
                }
            }
        });

        buttonReader = findViewById(R.id.buttonReader);
        buttonReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak(textData, TextToSpeech.QUEUE_FLUSH, null);

                Toast.makeText(SpecificInformationActivity.this, "Realizando leitura...", Toast.LENGTH_SHORT).show();
            }
        });

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
