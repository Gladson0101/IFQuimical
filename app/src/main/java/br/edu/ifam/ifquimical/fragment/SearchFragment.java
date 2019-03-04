package br.edu.ifam.ifquimical.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.edu.ifam.ifquimical.R;
import br.edu.ifam.ifquimical.adapter.QuimicalInformationAdapter;
import br.edu.ifam.ifquimical.model.QuimicalInformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private QuimicalInformationAdapter adapter;
    private ArrayList<QuimicalInformation> quimicalInformationArrayList = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        // TODO RECUPERAR INFORMAÇÕES DO BANCO DE DADOS

        // Configuração do Adapter.
        adapter = new QuimicalInformationAdapter(quimicalInformationArrayList, getActivity());

        // Configuração do RecyclerView.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
