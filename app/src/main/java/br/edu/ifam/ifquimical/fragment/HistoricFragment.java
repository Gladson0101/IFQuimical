package br.edu.ifam.ifquimical.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import br.edu.ifam.ifquimical.helper.HistoricDAO;
import br.edu.ifam.ifquimical.helper.RecyclerItemClickListener;
import br.edu.ifam.ifquimical.model.QuimicalInformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricFragment extends Fragment {

    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<QuimicalInformation> quimicalInformationArrayList = new ArrayList<>();

    public HistoricFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historic, container, false);

        HistoricDAO historicDAO = new HistoricDAO(getActivity());
        quimicalInformationArrayList = (ArrayList<QuimicalInformation>) historicDAO.list();

        textView = view.findViewById(R.id.textViewHistoric);

        if (quimicalInformationArrayList.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }

        QuimicalInformationAdapter adapter = new QuimicalInformationAdapter(quimicalInformationArrayList, getActivity());

        // Configuração do RecyclerView.
        recyclerView = view.findViewById(R.id.recyclerViewHistoric);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                QuimicalInformation qi = quimicalInformationArrayList.get(position);

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

        return view;
    }

    public void reloadSearchView() {
        QuimicalInformationAdapter adapter = new QuimicalInformationAdapter(quimicalInformationArrayList, getActivity());
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
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
