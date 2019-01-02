package com.blueline.harvbest.blueline.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.blueline.harvbest.blueline.Categorias.Cat1;
import com.blueline.harvbest.blueline.Categorias.Cat2;
import com.blueline.harvbest.blueline.Categorias.Cat3;
import com.blueline.harvbest.blueline.Categorias.Cat4;
import com.blueline.harvbest.blueline.Categorias.Cat5;
import com.blueline.harvbest.blueline.Categorias.Cat6;
import com.blueline.harvbest.blueline.Clases.PersonajeVO;
import com.blueline.harvbest.blueline.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaPersonajesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaPersonajesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaPersonajesFragment extends Fragment implements View.OnClickListener {

    private Button btn1,btn2,btn3,btn4,btn5,btn6;
    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerPerso;
    ArrayList<PersonajeVO> listaPersonajes;

    public ListaPersonajesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPersonajesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPersonajesFragment newInstance(String param1, String param2) {
        ListaPersonajesFragment fragment = new ListaPersonajesFragment();
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

        view = inflater.inflate(R.layout.fragment_lista_personajes, container, false);


        btn1 = (Button) view.findViewById(R.id.ca1);
        btn1.setOnClickListener(this);

        btn2 = (Button) view.findViewById(R.id.ca2);
        btn2.setOnClickListener(this);

        btn3 = (Button) view.findViewById(R.id.ca3);
        btn3.setOnClickListener(this);

        btn4 = (Button) view.findViewById(R.id.ca4);
        btn4.setOnClickListener(this);

        btn5 = (Button) view.findViewById(R.id.ca5);
        btn5.setOnClickListener(this);

        btn6 = (Button) view.findViewById(R.id.ca6);
        btn6.setOnClickListener(this);

        return view;
        ///   listaPersonajes = new ArrayList<>();
      //  recyclerPerso =  vista.findViewById(R.id.recyclerId);
     //   recyclerPerso.setLayoutManager(new GridLayoutManager(getContext(),2));

     //   llenarLista();

     /*   AdaptadorPersonajes adapter = new AdaptadorPersonajes(listaPersonajes);
        recyclerPerso.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Seleccion:  " + listaPersonajes.get
                        (recyclerPerso.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_LONG).show();
            }
        });
        */


    }
/*
    private void llenarLista() {
        listaPersonajes.add(new PersonajeVO("Llavero A","",R.drawable.aa));
        listaPersonajes.add(new PersonajeVO("Llavero B","",R.drawable.bb));
        listaPersonajes.add(new PersonajeVO("Llavero C","",R.drawable.cc));
        listaPersonajes.add(new PersonajeVO("Llavero D","",R.drawable.dd));
        listaPersonajes.add(new PersonajeVO("Llavero E","",R.drawable.ee));
        listaPersonajes.add(new PersonajeVO("Llavero F","",R.drawable.ff));
        listaPersonajes.add(new PersonajeVO("Llavero G","",R.drawable.gg));
        listaPersonajes.add(new PersonajeVO("Llavero H","",R.drawable.hh));
        listaPersonajes.add(new PersonajeVO("Llavero I","",R.drawable.ii));
    }


*/



    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.ca1:
                Toast.makeText(getActivity(), "Cargando Categoría", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), Cat1.class);
                getActivity().startActivity(intent);
                break;


            case R.id.ca2:
                Toast.makeText(getActivity(), "Cargando Categoría", Toast.LENGTH_SHORT).show();

                Intent col2 = new Intent(getActivity(), Cat2.class);
                getActivity().startActivity(col2);
                break;

            case R.id.ca3:
                Toast.makeText(getActivity(), "Cargando Categoría", Toast.LENGTH_SHORT).show();

                Intent col3 = new Intent(getActivity(), Cat3.class);
                getActivity().startActivity(col3);
                break;

            case R.id.ca4:
                Toast.makeText(getActivity(), "Cargando Categoría", Toast.LENGTH_SHORT).show();

                Intent col4 = new Intent(getActivity(), Cat4.class);
                getActivity().startActivity(col4);
                break;

            case R.id.ca5:
                Toast.makeText(getActivity(), "Cargando Categoría", Toast.LENGTH_SHORT).show();

                Intent col5 = new Intent(getActivity(), Cat5.class);
                getActivity().startActivity(col5);
                break;

            case R.id.ca6:
                Toast.makeText(getActivity(), "Cargando Categoría", Toast.LENGTH_SHORT).show();

                Intent col6 = new Intent(getActivity(), Cat6.class);
                getActivity().startActivity(col6);
                break;

        }


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
