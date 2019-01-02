package com.blueline.harvbest.blueline.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.blueline.harvbest.blueline.Clases.PersonajeVO;
import com.blueline.harvbest.blueline.R;

import java.util.ArrayList;

public class AdaptadorPersonajes extends RecyclerView.Adapter<AdaptadorPersonajes.ViewHolderPersonajes> implements View.OnClickListener {

ArrayList<PersonajeVO> listaPersonajes;
private View.OnClickListener listener;

public AdaptadorPersonajes(ArrayList<PersonajeVO>listaPersonajes){
   this.listaPersonajes = listaPersonajes;
}

    public ViewHolderPersonajes onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,null,false);
    view.setOnClickListener(this);
        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderPersonajes viewHolderPersonajes, int i) {

    viewHolderPersonajes.EtiNombre.setText(listaPersonajes.get(i).getNombre());
    viewHolderPersonajes.etInfo.setText(listaPersonajes.get(i).getInfo());
    viewHolderPersonajes.foto.setImageResource(listaPersonajes.get(i).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    public  void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


    @Override
    public void onClick(View v) {
if(listener!=null){
    listener.onClick(v);
}
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {

    TextView EtiNombre, etInfo;
    ImageView foto;

        public ViewHolderPersonajes(View itemView) {
            super(itemView);

            EtiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            etInfo = (TextView) itemView.findViewById(R.id.idDescr);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
