package com.example.aplicacionamigosrecuperacion.view.recyclerviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionamigosrecuperacion.R;
import com.example.aplicacionamigosrecuperacion.model.entity.Amigo;
import com.example.aplicacionamigosrecuperacion.viewmodel.ViewModel;

import java.util.List;

public class UpdateRecyclerViewAdapter extends RecyclerView.Adapter<UpdateRecyclerViewAdapter.ViewHolder>{

    List<Amigo> listaAmigos;
    Context contexto;
    NavController controller;
    ViewModel viewModel;

    public UpdateRecyclerViewAdapter(List<Amigo> list, Context context) {

        this.listaAmigos = list;
        contexto = context;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) contexto).get(ViewModel.class);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        UpdateRecyclerViewAdapter.ViewHolder holder = new UpdateRecyclerViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvamigo.setText(listaAmigos.get(position).toString());
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setAmigoUpdate(listaAmigos.get(position));
                controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_updateFragment_to_updateConfirmacionFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAmigos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvamigo;
        ConstraintLayout parent_layout;
        TextView tvcontador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcontador = itemView.findViewById(R.id.tvContadorLlamadas);
            tvamigo = itemView.findViewById(R.id.textView);
            parent_layout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
