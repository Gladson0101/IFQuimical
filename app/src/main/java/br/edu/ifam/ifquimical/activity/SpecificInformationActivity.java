package br.edu.ifam.ifquimical.activity;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.zagum.switchicon.SwitchIconView;

import java.util.Locale;

import br.edu.ifam.ifquimical.R;

public class SpecificInformationActivity extends AppCompatActivity {

    private TextView textInformation;
    private TextToSpeech textToSpeech;
    private String textData;

    private SwitchIconView switchIconViewSpeak;
    private View buttonSpeak;

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

        switchIconViewSpeak = findViewById(R.id.switchIconViewSpeak);

        buttonSpeak = findViewById(R.id.buttonSpeak);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchIconViewSpeak.switchState();

                if (textToSpeech.isSpeaking()) {
                    textToSpeech.stop();
                    Toast.makeText(SpecificInformationActivity.this, "Leitura interrompida", Toast.LENGTH_SHORT).show();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(textData,TextToSpeech.QUEUE_FLUSH,null,null);
                    } else {
                        textToSpeech.speak(textData, TextToSpeech.QUEUE_FLUSH, null);
                    }

                    Toast.makeText(SpecificInformationActivity.this, "Realizando leitura...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech = null;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
