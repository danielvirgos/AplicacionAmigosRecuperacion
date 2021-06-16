package com.example.aplicacionamigosrecuperacion.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacionamigosrecuperacion.model.entity.Amigo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AmigosRepository {

    private FirebaseFirestore db;

    private Context contexto;

    private MutableLiveData<QuerySnapshot> liveListaAmigos;

    public AmigosRepository(Context context) {

        this.contexto = context;

        db = FirebaseFirestore.getInstance();
    }

    public MutableLiveData<List<Amigo>> getLiveListaAmigos() {
        MutableLiveData<List<Amigo>> liveFriends = new MutableLiveData<>();
        db.collection("amigo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    liveFriends.setValue(task.getResult().toObjects(Amigo.class));
                }
            }
        });
        return liveFriends;
    }

    public void insert(Amigo amigo) {
        db.collection("amigo").document((amigo.getTelefono())).set(amigo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(contexto, "amigo añadido", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(contexto, "el amigo no se ha añadido correctamente", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void update(Amigo amigo) {
        db.collection("amigo").document((amigo.getTelefono()))
                .set(amigo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(contexto, "amigo actualizado", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(contexto, "amigo no se ha actualizado", Toast.LENGTH_LONG).show();
                        Log.v("zzzzz", e.getLocalizedMessage());
                    }
                });
    }

    public void delete(Amigo amigo) {
        db.collection("amigo").document(String.valueOf(amigo.getTelefono())).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(contexto, "amigo eliminado", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(contexto, "el amigo seleccionado no ha podido ser eliminado", Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void getAllAmigos () {
        db.collection("amigo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                liveListaAmigos.setValue(task.getResult());
            }
        });
    }




}
