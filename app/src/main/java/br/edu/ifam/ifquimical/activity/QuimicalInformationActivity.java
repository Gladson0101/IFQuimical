package br.edu.ifam.ifquimical.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import br.edu.ifam.ifquimical.R;
import br.edu.ifam.ifquimical.helper.QuimicalInformationDAO;
import br.edu.ifam.ifquimical.model.QuimicalInformation;

public class QuimicalInformationActivity extends AppCompatActivity {

    private QuimicalInformation quimicalInformation;

    private LinearLayout linearLayoutFirstAidActions;
    private LinearLayout linearLayoutFireSafety;
    private LinearLayout linearLayoutHandlingAndStorage;
    private LinearLayout linearLayoutExposureControlAndPersonalProtection;
    private LinearLayout linearLayoutSpillOrLeak;
    private LinearLayout linearLayoutStabilityAndReactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quimical_information);

        // Obtém os dados da intent.
        Bundle data = getIntent().getExtras();
        String name = data.getString("name");
        String formula = data.getString("formula");

        // Obtém o objeto com as informações.
        quimicalInformation = getObjectWithInformations(name);

        // Configura a Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name + "\n" + formula);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configura os LayoutsLineares.
        linearLayoutFirstAidActions = findViewById(R.id.layoutFirstAidActions);
        linearLayoutFireSafety = findViewById(R.id.layoutFireSafety);
        linearLayoutHandlingAndStorage = findViewById(R.id.layoutHandlingAndStorage);
        linearLayoutExposureControlAndPersonalProtection = findViewById(R.id.layoutExposureControlAndPersonalProtection);
        linearLayoutSpillOrLeak = findViewById(R.id.layoutSpillOrLeak);
        linearLayoutStabilityAndReactivity = findViewById(R.id.layoutStabilityAndReactivity);

        // Criando listeners para os layouts.
        linearLayoutFirstAidActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFirstAidActionsClicked();
            }
        });

        linearLayoutFireSafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFireSafetyClicked();
            }
        });

        linearLayoutHandlingAndStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHandlingAndStorageClicked();
            }
        });

        linearLayoutExposureControlAndPersonalProtection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onExposureControlAndPersonalProtectionClicked();
            }
        });

        linearLayoutSpillOrLeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSpillOrLeakClicked();
            }
        });

        linearLayoutStabilityAndReactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStabilityAndReactivityClicked();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quimical_information, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }


    /**
     *
     * Retorna o objeto com a informação desejada.
     *
     * @param name
     * @return qi
     */
    public QuimicalInformation getObjectWithInformations(String name) {
        QuimicalInformationDAO qiDAO = new QuimicalInformationDAO(getApplicationContext());
        ArrayList<QuimicalInformation> qiList = (ArrayList<QuimicalInformation>) qiDAO.list();

        QuimicalInformation qi = new QuimicalInformation();

        for (int i = 0; i < qiList.size(); i++) {
            if (qiList.get(i).getName().equals(name)) {
                qi = qiList.get(i);
            }
        }

        return qi;
    }

    /**
     * Abaixo estão configurados as intenções de click para cada LinearLayout configurado.
     */
    public void onFirstAidActionsClicked() {
        Intent intent = new Intent(getApplicationContext(), EspecifcInformationActivity.class);
        intent.putExtra("title", "Medidas de primeiros-socorros");
        intent.putExtra("textData", quimicalInformation.getFirstAidActions());
        startActivity(intent);
    }

    public void onFireSafetyClicked() {
        Intent intent = new Intent(getApplicationContext(), EspecifcInformationActivity.class);
        intent.putExtra("title", "Medidas de combate a incêndio");
        intent.putExtra("textData", quimicalInformation.getFireSafety());
        startActivity(intent);
    }

    public void onHandlingAndStorageClicked() {
        Intent intent = new Intent(getApplicationContext(), EspecifcInformationActivity.class);
        intent.putExtra("title", "Manuseio e armazenamento");
        intent.putExtra("textData", quimicalInformation.getHandlingAndStorage());
        startActivity(intent);
    }

    public void onExposureControlAndPersonalProtectionClicked() {
        Intent intent = new Intent(getApplicationContext(), EspecifcInformationActivity.class);
        intent.putExtra("title", "Controle de exposição e proteção individual");
        intent.putExtra("textData", quimicalInformation.getExposureControlAndPersonalProtection());
        startActivity(intent);
    }

    public void onSpillOrLeakClicked() {
        Intent intent = new Intent(getApplicationContext(), EspecifcInformationActivity.class);
        intent.putExtra("title", "Derramamento ou vazamento");
        intent.putExtra("textData", quimicalInformation.getSpillOrLeak());
        startActivity(intent);
    }

    public void onStabilityAndReactivityClicked() {
        Intent intent = new Intent(getApplicationContext(), EspecifcInformationActivity.class);
        intent.putExtra("title", "Estabilidade e reatividade");
        intent.putExtra("textData", quimicalInformation.getStabilityAndReactivity());
        startActivity(intent);
    }
}
