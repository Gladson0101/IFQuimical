package br.edu.ifam.ifquimical.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.ifam.ifquimical.R;
import br.edu.ifam.ifquimical.model.QuimicalInformation;

/**
 * Adapter para as informações reduzidas que aparecerão no RecyclerView.
 */
public class QuimicalInformationAdapter extends RecyclerView.Adapter<QuimicalInformationAdapter.MyViewHolder> {

    private List<QuimicalInformation> quimicalInformationList;
    private Context context;

    public QuimicalInformationAdapter(List<QuimicalInformation> quimicalInformationList, Context context) {
        this.quimicalInformationList = quimicalInformationList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemList =
                LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_quimical_information, viewGroup, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        QuimicalInformation quimicalInformation = new QuimicalInformation("",
                "",
                "",
                "",
                "",
                "",
                "",
                "");

        myViewHolder.textQuimicalName.setText(quimicalInformation.getName());
        myViewHolder.textQuimicalFormula.setText(quimicalInformation.getFormula());
    }

    @Override
    public int getItemCount() { return quimicalInformationList.size(); }

    /**
     * Classe interna para inicialização dos componentes do adapter_quimical_information.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textQuimicalName;
        private TextView textQuimicalFormula;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textQuimicalName = itemView.findViewById(R.id.textViewQuimicalName);
            textQuimicalFormula = itemView.findViewById(R.id.textViewQuimicalFormula);
        }
    }

}
