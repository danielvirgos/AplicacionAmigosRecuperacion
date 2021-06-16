package com.example.aplicacionamigosrecuperacion.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplicacionamigosrecuperacion.R;
import com.example.aplicacionamigosrecuperacion.model.entity.Amigo;
import com.example.aplicacionamigosrecuperacion.viewmodel.ViewModel;

public class UpdateConfirmacionFragment extends Fragment {

    EditText etnm, ettf, etfn;
    ViewModel viewModel;
    NavController controller;
    Amigo amigoupdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        init(view);
    }

    private void init(View view) {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        etfn = view.findViewById(R.id.etFechaNac);
        etnm = view.findViewById(R.id.etnombre);
        ettf = view.findViewById(R.id.etTelefono);
        amigoupdate = viewModel.getAmigoUpdate();
        etnm.setText(amigoupdate.getNombre());
        ettf.setText(amigoupdate.getTelefono());
        etfn.setText(amigoupdate.getFechaNac());

        Button btacep, btcancel;
        btacep = view.findViewById(R.id.btConfirmar);
        btcancel = view.findViewById(R.id.btCancelar);

        btacep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm;
                String tele;
                String fecha;
                nm = etnm.getText().toString();
                tele = ettf.getText().toString();
                fecha = etfn.getText().toString();
                amigoupdate.setNombre(nm);
                amigoupdate.setFechaNac(fecha);
                amigoupdate.setTelefono(tele);
                viewModel.update(amigoupdate);
                controller.navigate(R.id.action_updateConfirmacionFragment_to_inicioFragment);
            }
        });

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_updateConfirmacionFragment_to_inicioFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_confirmacion, container, false);
    }
}