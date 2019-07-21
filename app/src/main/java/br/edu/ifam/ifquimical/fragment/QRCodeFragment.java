package br.edu.ifam.ifquimical.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;

import br.edu.ifam.ifquimical.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QRCodeFragment extends Fragment {


    public QRCodeFragment() {
        // Required empty public constructor
    }


    private Button scanButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qrcode, container, false);

        scanButton = view.findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });

        return view;
    }

    public void scan() {
        // Inicia o QR Reader.
        Activity activity = getActivity();

        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Camera Scan");
        intentIntegrator.setCameraId(0);
        intentIntegrator.initiateScan();
    }

}
