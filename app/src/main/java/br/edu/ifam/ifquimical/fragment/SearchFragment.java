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

    private RecyclerView recyclerView;
    private ArrayList<QuimicalInformation> quimicalInformationArrayList = new ArrayList<>();
    private ArrayList<QuimicalInformation> qiInfo = new ArrayList<>();
    private boolean search;

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

        search = true;

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

        return view;
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
