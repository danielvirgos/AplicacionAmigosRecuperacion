package com.example.aplicacionamigosrecuperacion.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aplicacionamigosrecuperacion.R;
import com.example.aplicacionamigosrecuperacion.model.entity.Amigo;
import com.example.aplicacionamigosrecuperacion.view.recyclerviewadapter.AddRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    NavController controller;
    List<Amigo> amigos = new ArrayList<>();
    private static final int CONTACTS_PERMISSION = 1;
    private static final int PHONE_PERMISSION = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewContactos);
        controller = Navigation.findNavController(view);
        init(view);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case CONTACTS_PERMISSION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    getContacts();
                } else {
                    controller.navigate(R.id.action_addFragment_to_inicioFragment);
                }
                break;
            case PHONE_PERMISSION:
                break;
        }
    }

    private void init(View view) {
        Button btContactos = view.findViewById(R.id.btContactos);
        btContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConstactsPermission();
            }
        });
    }

    private void getConstactsPermission() {
        int result = PackageManager.PERMISSION_GRANTED;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            result = getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS);
        }
        if(result == PackageManager.PERMISSION_GRANTED) {
            getContacts();
        } else {
            requestPermission();
        }
    }

    @SuppressLint("NewApi")
    private void requestPermission() {
        if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) || shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
            explainReason();
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE}, CONTACTS_PERMISSION);
        }
    }

    private void explainReason() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.titulo_permiso);
        builder.setMessage(R.string.mensaje_permiso);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION);
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.navigate(R.id.action_addFragment_to_inicioFragment);
            }
        });
        builder.show();
    }

    private void getContacts() {
        ContentResolver contentResolver = getContext().getContentResolver();

        Cursor contactCursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, //url, consulta de contactos
                null, // *
                null, // where
                null, // parámetros
                null);

        while (contactCursor.moveToNext()) {
            int id = Integer.parseInt(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID)));
            String name = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor phoneCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, //url
                    null, //*
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", //where contact_id = :contact_id
                    new String[]{String.valueOf(id)}, //parámetro id como sustituto de ?
                    null);

            while (phoneCursor.moveToNext()) {
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Amigo contacto = new Amigo(name,phoneNumber, "");
                amigos.add(contacto);
            }

            phoneCursor.close();
        }

        MostrarContactos();

    }

    private void MostrarContactos() {
        AddRecyclerViewAdapter agendaRecyclerViewAdapter = new AddRecyclerViewAdapter(amigos, getContext());

        recyclerView.setAdapter(agendaRecyclerViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }
}