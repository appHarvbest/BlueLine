package com.blueline.harvbest.blueline.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blueline.harvbest.blueline.Categorias_Adapter.ListAdapterCol;
import com.blueline.harvbest.blueline.Entidades.Usuario;
import com.blueline.harvbest.blueline.Modelo.PhotoCol;
import com.blueline.harvbest.blueline.R;
import com.blueline.harvbest.blueline.adapters.UsuariosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Formulario.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Formulario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Formulario extends Fragment {


    private static final String TAG = ListaPersonajesFragment.class.getName();
    private RecyclerView mRecyclerViewCo;
    private RecyclerView.Adapter mAdapterco;
    private RecyclerView.LayoutManager mLayoutManagerco;

    //El dataset de tipo Photo
    private ArrayList<PhotoCol> myDatasetCo;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;

    ProgressDialog progreso;

    RequestQueue request;

    JsonObjectRequest jsonObjectRequest;


    public Formulario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Formulario.
     */
    // TODO: Rename and change types and number of parameters
    public static Formulario newInstance(String param1, String param2) {
        Formulario fragment = new Formulario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View vista = inflater.inflate(R.layout.fragment_formulario, container, false);
        mRecyclerViewCo = (RecyclerView) vista.findViewById(R.id.my_recycler_view_co);

        // Si se sabe que la cantidad de items de la lista es siempre la misma y esta no cambiará
        // entonces podemos hacer uso de la sigiente propidad para mejorar el
        // Performance del RecyclerView
        mRecyclerViewCo.setHasFixedSize(true);

        // Instanciamos unlinear layout manager para setearlo en el RecyclerView
        mLayoutManagerco = new GridLayoutManager(getContext(),2);
        mRecyclerViewCo.setLayoutManager(mLayoutManagerco);

        //Cargamos los datos en el dataset
        cargarWebService();
        return  vista;
    }


    private void refreshDataset() {
        if (mRecyclerViewCo == null)
            return;

        if (mAdapterco == null) {
            mAdapterco = new ListAdapterCol(getContext(), myDatasetCo);
            mRecyclerViewCo.setAdapter(mAdapterco);
        } else {
            mAdapterco.notifyDataSetChanged();
        }
    }
    private void cargarWebService() {
        progreso= new ProgressDialog(getContext());
        progreso.setMessage("cargando...");
        progreso.show();
        myDatasetCo = new ArrayList<PhotoCol>();



        String url = "http://bluelinemexico.com/ws_app/wsFamPorCategoria.php?Categoria=824&Destino=cancun";
        final String urlIMG = "http://bluelinemexico.com/";

        //Instanciamos un objeto RequestQueue el cual se encarga de gestionar la cola de peticiones
        RequestQueue queue = Volley.newRequestQueue(getContext());

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
                                        if (p.has("file_url"))
                                            photo.setImageUrl (urlIMG+(p.getString ("file_url")));
                                        if (p.has("virtuemart_category_id"))
                                            photo.setCategoryid (p.getString ("virtuemart_category_id"));
                                        //Agreagamos el objeto Photo al Dataset
                                        myDatasetCo.add(photo);
                                    }
                                    progreso.hide();
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




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
