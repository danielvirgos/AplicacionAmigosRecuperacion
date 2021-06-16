package com.example.aplicacionamigosrecuperacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InicioFragment extends Fragment {

    NavController controller;
    Button btadd, btdelete, btselect, btupdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btadd = view.findViewById(R.id.btAddAmigos);
        btselect = view.findViewById(R.id.btListarAmigos);
        btdelete = view.findViewById(R.id.btBorrarAmigo);
        btupdate = view.findViewById(R.id.btEditarAmigo);
        controller = Navigation.findNavController(view);

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(getView().findViewById(R.id.nav_host_fragment)).navigate(R.id.action_inicioFragment_to_addFragment);
                controller.navigate(R.id.action_inicioFragment_to_addFragment);
            }
        });

        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(getView().findViewById(R.id.nav_host_fragment)).navigate(R.id.action_inicioFragment_to_deleteFragment);
                controller.navigate(R.id.action_inicioFragment_to_deleteFragment);
            }
        });

        btselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(getView().findViewById(R.id.nav_host_fragment)).navigate(R.id.action_inicioFragment_to_listarFragment);
                controller.navigate(R.id.action_inicioFragment_to_listarFragment);
            }
        });

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(getView().findViewById(R.id.nav_host_fragment)).navigate(R.id.action_inicioFragment_to_updateFragment);
                controller.navigate(R.id.action_inicioFragment_to_updateFragment);
            }
        });
    }
}