package com.blueline.harvbest.blueline.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blueline.harvbest.blueline.Entidades.Usuario;
import com.blueline.harvbest.blueline.R;

import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder> {

List<Usuario> listaUsuario;

public UsuariosAdapter(List<Usuario>listaUsuario){
    this.listaUsuario = listaUsuario;
}
    @Override
    public UsuariosAdapter.UsuariosHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

    View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.usuarios_list,viewGroup,false);
    RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams
            (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder( UsuariosHolder usuariosHolder, int i) {
        usuariosHolder.idd.setText(listaUsuario.get(i).getId().toString());
        usuariosHolder.nombre.setText(listaUsuario.get(i).getNombre().toString());
        usuariosHolder.info.setText(listaUsuario.get(i).getDescripcion().toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class UsuariosHolder extends RecyclerView.ViewHolder{

    TextView nombre,info, idd;

        public UsuariosHolder(View itemView) {
            super(itemView);
            idd= (TextView) itemView.findViewById(R.id.idnumero);
            nombre= (TextView)itemView.findViewById(R.id.idNombre);
            info= (TextView)itemView.findViewById(R.id.idDescr);

        }
    }
}
