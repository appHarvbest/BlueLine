package com.blueline.harvbest.blueline.Colecciones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blueline.harvbest.blueline.Categorias_Adapter.ListAdapterCol;
import com.blueline.harvbest.blueline.Fragments.RedFragment;
import com.blueline.harvbest.blueline.Modelo.PhotoCol;
import com.blueline.harvbest.blueline.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Col3 extends AppCompatActivity {

    private static final String TAG = RedFragment.class.getName();
    private RecyclerView mRecyclerViewCo;
    private RecyclerView.Adapter mAdapterco;
    private RecyclerView.LayoutManager mLayoutManagerco;

    //El dataset de tipo Photo
    private ArrayList<PhotoCol> myDatasetCo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colecciones);
        //Instanciamos el RecyclerView del activity_main layout y lo conectamos con la MainActivity
        mRecyclerViewCo = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Si se sabe que la cantidad de items de la lista es siempre la misma y esta no cambiará
        // entonces podemos hacer uso de la sigiente propidad para mejorar el
        // Performance del RecyclerView
        mRecyclerViewCo.setHasFixedSize(true);

        // Instanciamos unlinear layout manager para setearlo en el RecyclerView
        mLayoutManagerco = new GridLayoutManager(this,2);
        mRecyclerViewCo.setLayoutManager(mLayoutManagerco);

        //Cargamos los datos en el dataset
        loadPhotosFromWeb();
    }

    private void refreshDataset() {
        if (mRecyclerViewCo == null)
            return;

        if (mAdapterco == null) {
            mAdapterco = new ListAdapterCol(this, myDatasetCo);
            mRecyclerViewCo.setAdapter(mAdapterco);
        } else {
            mAdapterco.notifyDataSetChanged();
        }
    }


    private void loadPhotosFromWeb() {
        //Hacemos uso de Volley para consumir el End-point
        myDatasetCo = new ArrayList<PhotoCol>();

        //Definimos un String con la URL del End-point
        String url = "http://bluelinemexico.com/ws_app/wsFamPorColeccion.php?Coleccion=03&Destino=cancun";
        final String urlIMG = "http://bluelinemexico.com/";

        //Instanciamos un objeto RequestQueue el cual se encarga de gestionar la cola de peticiones
        RequestQueue queue = Volley.newRequestQueue(this);

        //Armamos una peticion de tipo JSONArray por que es un JsonArray lo que obtendremos como resultado
        JsonArrayRequest aRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse");
                        //Obtenemos un JSONArray como respuesta
                        if (response != null && response.length() > 0){
                            //Iteramos son el JSONArray
                            for (int i=0; i <response.length(); i++){
                                try {
                                    JSONObject p = (JSONObject) response.get(i);
                                    if (p != null){
                                        //Armamos un objeto Photo con el Title y la URL de cada JSONObject
                                        PhotoCol photo = new PhotoCol();
                                        if (p.has("category_name"))
                                            photo.setTitle(p.getString("category_name"));

                                        if (p.has("virtuemart_category_id"))
                                            photo.setCategoryid (p.getString ("virtuemart_category_id"));

                                        if (p.has("file_title"))
                                            photo.setFileTitle (p.getString ("file_title"));

                                        if (p.has("file_url"))
                                            photo.setImageUrl (urlIMG+(p.getString ("file_url")));

                                        if (p.has("file_url"))
                                            photo.setUrl (p.getString ("file_url"));


                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetCo.add(photo);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetCo.size() > 0)
                                        refreshDataset();
                                }
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        });

        //Agregamos la petición de tipo JSON a la cola
        queue.add(aRequest);
    }

}
