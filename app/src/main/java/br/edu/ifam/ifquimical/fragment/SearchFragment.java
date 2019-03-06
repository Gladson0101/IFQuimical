package br.edu.ifam.ifquimical.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifam.ifquimical.R;
import br.edu.ifam.ifquimical.activity.QuimicalInformationActivity;
import br.edu.ifam.ifquimical.adapter.QuimicalInformationAdapter;
import br.edu.ifam.ifquimical.helper.QuimicalInformationDAO;
import br.edu.ifam.ifquimical.helper.RecyclerItemClickListener;
import br.edu.ifam.ifquimical.model.QuimicalInformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    /**
     * TextViews de Configurações.
     */
    private TextView textViewA;
    private TextView textViewB;
    private TextView textViewC;
    private TextView textViewD;
    private TextView textViewE;
    private TextView textViewF;
    private TextView textViewG;
    private TextView textViewH;
    private TextView textViewI;
    private TextView textViewJ;
    private TextView textViewK;
    private TextView textViewL;
    private TextView textViewM;
    private TextView textViewN;
    private TextView textViewO;
    private TextView textViewP;
    private TextView textViewQ;
    private TextView textViewR;
    private TextView textViewS;
    private TextView textViewT;
    private TextView textViewU;
    private TextView textViewV;
    private TextView textViewW;
    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;

    private RecyclerView recyclerView;
    private ArrayList<QuimicalInformation> quimicalInformationArrayList = new ArrayList<>();
    private ArrayList<QuimicalInformation> qiInfo = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Obtém as informações do banco de dados.
        QuimicalInformationDAO quimicalInformationDAO = new QuimicalInformationDAO(getActivity());
        quimicalInformationArrayList = (ArrayList<QuimicalInformation>) quimicalInformationDAO.list();
        qiInfo = quimicalInformationArrayList;

        // Configuração do Adapter.
        QuimicalInformationAdapter adapter = new QuimicalInformationAdapter(quimicalInformationArrayList, getActivity());

        // Configuração do RecyclerView.
        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        // recyclerView.scrollToPosition();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                QuimicalInformation qi = qiInfo.get(position);

                                Intent intent = new Intent(getActivity(), QuimicalInformationActivity.class);
                                intent.putExtra("name", qi.getName());
                                intent.putExtra("formula", qi.getFormula());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        })
        );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        configureAlfabeticListener(view);

        return view;
    }

    private void configureAlfabeticListener(View view) {
        textViewA = view.findViewById(R.id.textViewSearchA);
        textViewB = view.findViewById(R.id.textViewSearchB);
        textViewC = view.findViewById(R.id.textViewSearchC);
        textViewD = view.findViewById(R.id.textViewSearchD);
        textViewE = view.findViewById(R.id.textViewSearchE);
        textViewF = view.findViewById(R.id.textViewSearchF);
        textViewG = view.findViewById(R.id.textViewSearchG);
        textViewH = view.findViewById(R.id.textViewSearchH);
        textViewI = view.findViewById(R.id.textViewSearchI);
        textViewJ = view.findViewById(R.id.textViewSearchJ);
        textViewK = view.findViewById(R.id.textViewSearchK);
        textViewL = view.findViewById(R.id.textViewSearchL);
        textViewM = view.findViewById(R.id.textViewSearchM);
        textViewN = view.findViewById(R.id.textViewSearchN);
        textViewO = view.findViewById(R.id.textViewSearchO);
        textViewP = view.findViewById(R.id.textViewSearchP);
        textViewQ = view.findViewById(R.id.textViewSearchQ);
        textViewR = view.findViewById(R.id.textViewSearchR);
        textViewS = view.findViewById(R.id.textViewSearchS);
        textViewT = view.findViewById(R.id.textViewSearchT);
        textViewU = view.findViewById(R.id.textViewSearchU);
        textViewV = view.findViewById(R.id.textViewSearchV);
        textViewW = view.findViewById(R.id.textViewSearchW);
        textViewX = view.findViewById(R.id.textViewSearchX);
        textViewY = view.findViewById(R.id.textViewSearchY);
        textViewZ = view.findViewById(R.id.textViewSearchZ);

        textViewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });

        textViewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

        textViewZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(40);
            }
        });

    }

    public void reloadSearchView() {
        QuimicalInformationAdapter adapter = new QuimicalInformationAdapter(quimicalInformationArrayList, getActivity());
        qiInfo = quimicalInformationArrayList;
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void search(String text) {
        List<QuimicalInformation> qiListSearch = new ArrayList<>();

        for (QuimicalInformation qi : quimicalInformationArrayList) {
            String name = qi.getName().toLowerCase();
            String formula = qi.getFormula().toLowerCase();

            if (name.contains(text) || formula.contains(text)) {
                qiListSearch.add(qi);
            }
        }

        QuimicalInformationAdapter adapter = new QuimicalInformationAdapter(qiListSearch, getActivity());
        qiInfo = (ArrayList<QuimicalInformation>) qiListSearch;
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
