package br.edu.ifam.ifquimical.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

import br.edu.ifam.ifquimical.R;
import br.edu.ifam.ifquimical.fragment.FavoriteFragment;
import br.edu.ifam.ifquimical.fragment.HistoricFragment;
import br.edu.ifam.ifquimical.fragment.QRCodeFragment;
import br.edu.ifam.ifquimical.fragment.SearchFragment;
import br.edu.ifam.ifquimical.helper.QuimicalInformationDAO;
import br.edu.ifam.ifquimical.model.QuimicalInformation;

public class MainActivity extends AppCompatActivity {

    private MaterialSearchView searchView;
    private BottomNavigationView navigation;
    private ViewPager viewPager;
    private FragmentPagerItemAdapter adapter;

    /**
     * Configura as alterações de tela por meio do BottonNavigation.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_favorites:
                    viewPager.setCurrentItem(0, true);
                    return true;
                case R.id.navigation_historic:
                    viewPager.setCurrentItem(1, true);
                    return true;
                case R.id.navigation_search:
                    viewPager.setCurrentItem(2, true);
                    return true;
                case R.id.navigation_qr_code:
                    viewPager.setCurrentItem(3, false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura a toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuração do BottonNavigation
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Configura FragmentPagerItemAdapter.
        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("", FavoriteFragment.class)
                .add("", HistoricFragment.class)
                .add("", SearchFragment.class)
                .add("", QRCodeFragment.class)
                .create());

        // Configura o ViewPager
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i == 3) {
                    navigation.setSelectedItemId(R.id.navigation_qr_code);
                    QRCodeFragment fragment = (QRCodeFragment) adapter.getPage(3);
                    fragment.scan();
                }

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    navigation.setSelectedItemId(R.id.navigation_favorites);
                } else if (i == 1) {
                    navigation.setSelectedItemId(R.id.navigation_historic);
                } else if (i == 2) {
                    navigation.setSelectedItemId(R.id.navigation_search);
                } else if (i == 3) {
                    navigation.setSelectedItemId(R.id.navigation_qr_code);
                    QRCodeFragment fragment = (QRCodeFragment) adapter.getPage(3);
                    fragment.scan();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // Configura a SearchView
        searchView = findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                navigation.setVisibility(View.GONE);
            }

            @Override
            public void onSearchViewClosed() {
                int currentItem = viewPager.getCurrentItem();

                if (currentItem == 0) {
                    FavoriteFragment fragment = (FavoriteFragment) adapter.getPage(0);
                    fragment.reloadSearchView();
                } else if (currentItem == 1) {
                    HistoricFragment fragment = (HistoricFragment) adapter.getPage(1);
                    fragment.reloadSearchView();
                } else if (currentItem == 2) {
                    SearchFragment fragment = (SearchFragment) adapter.getPage(2);
                    fragment.reloadSearchView();
                }

                navigation.setVisibility(View.VISIBLE);
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                int currentItem = viewPager.getCurrentItem();

                if (currentItem == 0) {
                    FavoriteFragment fragment = (FavoriteFragment) adapter.getPage(0);
                    fragment.search(newText.toLowerCase());
                } else if (currentItem == 1) {
                    HistoricFragment fragment = (HistoricFragment) adapter.getPage(1);
                    fragment.search(newText.toLowerCase());
                } else if (currentItem == 2) {
                    SearchFragment fragment = (SearchFragment) adapter.getPage(2);
                    fragment.search(newText.toLowerCase());
                }

                return true;
            }
        });

        navigation.setSelectedItemId(R.id.navigation_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        // Configura o botão de pesquisa.
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initDatabaseWithXML();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                Uri uri = Uri.parse(result.getContents());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    /**
     * Obtém os dados do arquivo XML e transfere para o banco de dados.
     *
     * @onStart deve chamar este método.
     */
    public void initDatabaseWithXML() {
        XmlPullParserFactory parserFactory;

        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();

            InputStream is = getAssets().open("quimical_information.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processParsing(parser);

        } catch (Exception e) {
            Log.i("XML", "Erro ao passar o XML para o Banco de Dados");
        }
    }

    /**
     * Processa as informações do XML.
     *
     * @param parser
     * @initDatabaseWithXML deve chamar este método.
     */
    private void processParsing(XmlPullParser parser) {

        // Incia lista e atual.
        ArrayList<QuimicalInformation> quimicalInformationsList = new ArrayList<>();
        QuimicalInformation currentQuimicalInformation = null;

        try {
            // Obtém o tipo de evento.
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();

                        // Verifica o tamanho da lista.
                        if ("size".equals(tagName)) {
                            QuimicalInformationDAO quimicalInformationDAO = new QuimicalInformationDAO(getApplicationContext());
                            int listSize = quimicalInformationDAO.list().size();
                            int size = Integer.parseInt(parser.nextText());

                            if (size <= listSize) {
                                return;
                            }
                        }

                        if ("item".equals(tagName)) {
                            currentQuimicalInformation = new QuimicalInformation();
                            quimicalInformationsList.add(currentQuimicalInformation);
                        } else if (currentQuimicalInformation != null) {
                            if ("name".equals(tagName)) {
                                currentQuimicalInformation.setName(parser.nextText());
                            } else if ("formula".equals(tagName)) {
                                currentQuimicalInformation.setFormula(parser.nextText());
                            } else if ("firstAidActions".equals(tagName)) {
                                currentQuimicalInformation.setFirstAidActions(parser.nextText());
                            } else if ("fireSafety".equals(tagName)) {
                                currentQuimicalInformation.setFireSafety(parser.nextText());
                            } else if ("handlingAndStorage".equals(tagName)) {
                                currentQuimicalInformation.setHandlingAndStorage(parser.nextText());
                            } else if ("exposureControlAndPersonalProtection".equals(tagName)) {
                                currentQuimicalInformation.setExposureControlAndPersonalProtection(parser.nextText());
                            } else if ("spillOrLeak".equals(tagName)) {
                                currentQuimicalInformation.setSpillOrLeak(parser.nextText());
                            } else if ("stabilityAndReactivity".equals(tagName)) {
                                currentQuimicalInformation.setStabilityAndReactivity(parser.nextText());
                            }
                        }

                        break;
                }

                eventType = parser.next();
            }
        } catch (Exception e) {
            Log.i("XML", "Não foi possível carregar os dados do XML");
        }

        parseDataToDatabase(quimicalInformationsList);
    }

    /**
     * Passa as informações do arraylist para o banco de dados.
     *
     * @param qiList
     * @processParsing deve chamar este método ao seu término.
     */
    private void parseDataToDatabase(ArrayList<QuimicalInformation> qiList) {

        QuimicalInformationDAO quimicalInformationDAO = new QuimicalInformationDAO(getApplicationContext());
        for (QuimicalInformation qi : qiList) {
            quimicalInformationDAO.save(qi);
        }
    }


}