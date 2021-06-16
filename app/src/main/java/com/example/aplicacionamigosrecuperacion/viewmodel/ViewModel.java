package com.example.aplicacionamigosrecuperacion.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplicacionamigosrecuperacion.model.AmigosRepository;
import com.example.aplicacionamigosrecuperacion.model.entity.Amigo;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    public ViewModel(@NonNull Application application, Amigo amigoUpdate) {
        super(application);
        this.amigoUpdate = amigoUpdate;
    }

    Amigo amigoUpdate;

    private AmigosRepository repository;

    public Amigo getAmigoUpdate() {
        return amigoUpdate;
    }

    public void setAmigoUpdate(Amigo amigoUpdate) {
        this.amigoUpdate = amigoUpdate;
    }

    public ViewModel(@NonNull Application application) {
        super(application);
        amigoUpdate = new Amigo();
        repository = new AmigosRepository(application);
    }

    public void update(Amigo amigo) {
        Log.v("zzzz", amigo.toString());
        repository.update(amigo);
    }

    public void delete(Amigo amigo) {
        repository.delete(amigo);
    }

    public LiveData<List<Amigo>> getLiveListaAmigos() {
        return repository.getLiveListaAmigos();
    }

    public void insert(Amigo amigo) {
        repository.insert(amigo);
    }

}
