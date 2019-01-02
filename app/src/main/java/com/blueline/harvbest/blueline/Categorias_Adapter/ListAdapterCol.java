package com.blueline.harvbest.blueline.Categorias_Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blueline.harvbest.blueline.Modelo.PhotoCol;
import com.blueline.harvbest.blueline.Productos;
import com.blueline.harvbest.blueline.R;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

/**
 * Created by javiexpo on 26/7/16.
 */
public class ListAdapterCol extends RecyclerView.Adapter<ListAdapterCol.ViewHolder> {
    private Context mContextCol;
    private ArrayList<PhotoCol> mDatasetcol;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView mTextViewco,url,filename,idcategory;
        ImageView mImgtViewco;
        Context context;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mTextViewco = (TextView) v.findViewById(R.id.title_co);
            idcategory =(TextView) v.findViewById(R.id.category_id_co);
            filename = (TextView) v.findViewById(R.id.file_title_co);
            url = (TextView) v.findViewById(R.id.url_co);
            mImgtViewco = (ImageView) v.findViewById(R.id.image_Co);
        }
        void setOnClickListener()    {
            mImgtViewco.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_Co:
                    Intent intent = new Intent(context,Productos.class);
                    intent.putExtra("category_name", mTextViewco.getText());
                    intent.putExtra("virtuemart_category_id", idcategory.getText());
                    intent.putExtra("file_title", filename.getText());
                    intent.putExtra("file_url", url.getText());
                    context.startActivity(intent);
                    break;
            }
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapterCol(Context contextcol, ArrayList<PhotoCol> myDatasetco) {
        mDatasetcol = myDatasetco;
        mContextCol= contextcol;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list_cate, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//////////
        /////////////
        //////////////
        //aqui se habilita el holder para mostrar el campo de texto

        Glide.with(mContextCol).load(mDatasetcol.get(position).getImageUrlco()).into(holder.mImgtViewco);
        holder.mTextViewco.setText(mDatasetcol.get(position).getTitleco());
        holder.idcategory.setText(mDatasetcol.get(position).getCategoryid());
        holder.filename.setText(mDatasetcol.get(position).getFileTitle());
        holder.url.setText(mDatasetcol.get(position).getCategoryid());
        Glide.with(mContextCol).load(mDatasetcol.get(position).getImageUrlco()).into(holder.mImgtViewco);
        //Glide.with(mContext).load("http://goo.gl/gEgYUd").into(holder.mImgtView);

        holder.setOnClickListener();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetcol.size();
    }
}
