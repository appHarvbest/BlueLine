package com.blueline.harvbest.blueline.Categorias_Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blueline.harvbest.blueline.Modelo.Photo;
import com.blueline.harvbest.blueline.Productos;
import com.blueline.harvbest.blueline.R;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by javiexpo on 26/7/16.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Photo> mDataset;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView mTextView;
        ImageView mImgtView;
        Context context;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            mTextView = (TextView) v.findViewById(R.id.title);
            mImgtView = (ImageView) v.findViewById(R.id.image);
            ///funcion para que nos regrese la posicion presionada
      /*     v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    if (getAdapterPosition()==0){
                        Intent intent= new Intent(context, Splash.class);

                    }

                }
            });   */
        }

        void setOnClickListener()    {
            mImgtView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image:
                    Intent intent = new Intent(context,Productos.class);
                    intent.putExtra("nombre", mTextView.getText());
                break;
            }
        }
    }


        // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(Context context, ArrayList<Photo> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getTitle());
        Glide.with(mContext).load(mDataset.get(position).getImageUrl()).into(holder.mImgtView);
        //Glide.with(mContext).load("http://goo.gl/gEgYUd").into(holder.mImgtView);

        holder.setOnClickListener();
    }




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
