package com.blueline.harvbest.blueline.Categorias_Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blueline.harvbest.blueline.Detalle_pro;
import com.blueline.harvbest.blueline.Modelo.Producto;
import com.blueline.harvbest.blueline.Productos;
import com.blueline.harvbest.blueline.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListAdapterPro extends RecyclerView.Adapter<ListAdapterPro.ViewHolder> {
    private Context mContextPro;
    private ArrayList<Producto> mDatasetpro;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView mTextViewpro,filename,skuPro;
        ImageView mImgtViewPro;
        Context context;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mTextViewpro = (TextView) v.findViewById(R.id.title_Pro);
            skuPro =(TextView) v.findViewById(R.id.Sku_Pro);
            filename = (TextView) v.findViewById(R.id.file_title_Pro);
            mImgtViewPro = (ImageView) v.findViewById(R.id.image_Pro);
        }
        void setOnClickListener()    {
            mImgtViewPro.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_Pro:
                    Intent intent = new Intent(context,Detalle_pro.class);
                    intent.putExtra("product_name", mTextViewpro.getText());
                    intent.putExtra("Stock", skuPro.getText());
                  //  intent.putExtra("Imagen", filename.getText());
                    //intent.putExtra("Imagen", url.getText());
                    context.startActivity(intent);
                    break;
            }
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapterPro(Context contextPro, ArrayList<Producto> myDatasetpro) {
        mDatasetpro = myDatasetpro;
        mContextPro= contextPro;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list_producto, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ListAdapterPro.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//////////
        /////////////
        //////////////
        //aqui se habilita el holder para mostrar el campo de texto

          Glide.with(mContextPro).load(mDatasetpro.get(position).getImageUrlpro()).into(holder.mImgtViewPro);
        holder.mTextViewpro.setText(mDatasetpro.get(position).getTitlepro());
        holder.skuPro.setText(mDatasetpro.get(position).getSku());
        //holder.filename.setText(mDatasetpro.get(position).getFileTitle());
      //  holder.url.setText(mDatasetpro.get(position).getCategoryid());
        //Glide.with(mContextPro).load(mDatasetpro.get(position).getImageUrlpro()).into(holder.mImgtViewPro);
        //Glide.with(mContext).load("http://goo.gl/gEgYUd").into(holder.mImgtView);

        holder.setOnClickListener();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetpro.size();
    }
}
