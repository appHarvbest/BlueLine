package com.blueline.harvbest.blueline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blueline.harvbest.blueline.Categorias_Adapter.ListAdapterCol;
import com.blueline.harvbest.blueline.Categorias_Adapter.ListAdapterPro;
import com.blueline.harvbest.blueline.Fragments.RedFragment;
import com.blueline.harvbest.blueline.Modelo.PhotoCol;
import com.blueline.harvbest.blueline.Modelo.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {

    private static final String TAG = RedFragment.class.getName();
    private RecyclerView mRecyclerViewPro;
    private RecyclerView.Adapter mAdapterPro;
    private RecyclerView.LayoutManager mLayoutManagerPro;

    TextView clave,user;

    //El dataset de tipo Photo
    private ArrayList<Producto> myDatasetPro;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        String username="";
        String nombre= "";
        String Url = "";
        String idcate = "";
        String filename = "";

       user = (TextView) findViewById(R.id.usernamePro);

        Intent intent = getIntent();
        String username2 = intent.getStringExtra("username");

        user.setText(username2);




        Bundle extras = getIntent().getExtras();
        if (extras != null){

            nombre = extras.getString("category_name");
            idcate = extras.getString("virtuemart_category_id");
            filename = extras.getString("file_title");
            Url = extras.getString("file_url");

        }

       TextView dato= (TextView) findViewById(R.id.NombrePro);
        dato.setText(nombre);

         clave = (TextView) findViewById(R.id.idcategory);
        clave.setText(idcate);

        TextView dato4 = (TextView) findViewById(R.id.file_name_img);
        dato4.setText(filename);

        TextView dato2 = (TextView) findViewById(R.id.URLPRO);
        dato2.setText(Url);

        mRecyclerViewPro = (RecyclerView) findViewById(R.id.my_recycler_view);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerViewPro.setLayoutManager(manager);

        // Si se sabe que la cantidad de items de la lista es siempre la misma y esta no cambiará
        // entonces podemos hacer uso de la sigiente propidad para mejorar el
        // Performance del RecyclerView
        mRecyclerViewPro.setHasFixedSize(true);

     
        // Instanciamos unlinear layout manager para setearlo en el RecyclerView
        mLayoutManagerPro = new GridLayoutManager(this,3);
        mRecyclerViewPro.setLayoutManager(mLayoutManagerPro);

        //Cargamos los datos en el dataset
        loadPhotosFromWeb();
    }

    private void refreshDataset() {
        if (mRecyclerViewPro == null)
            return;

        if (mAdapterPro == null) {
            mAdapterPro = new ListAdapterPro(this, myDatasetPro);
            mRecyclerViewPro.setAdapter(mAdapterPro);
        } else {
            mAdapterPro.notifyDataSetChanged();
        }
    }


    private void loadPhotosFromWeb() {
        //Hacemos uso de Volley para consumir el End-point
        myDatasetPro = new ArrayList<Producto>();

        //Definimos un String con la URL del End-point
        String url = "http://bluelinemexico.com/ws_app/wsProdPorFamilia.php?Familia="+clave.getText().toString()+"&Destino=cancun";
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
                                        Producto producto = new Producto();
                                        if (p.has("product_name"))
                                            producto.setTitlepro(p.getString("product_name"));

                                        if (p.has("Imagen"))
                                            producto.setImageUrlpro (urlIMG+(p.getString ("Imagen")));

                                        if (p.has("Stock"))
                                            producto.setSku (p.getString ("Stock"));


                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetPro.add(producto);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } finally {
                                    //Finalmente si hemos cargado datos en el Dataset
                                    // entonces refrescamos
                                    if (myDatasetPro.size() > 0)
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


