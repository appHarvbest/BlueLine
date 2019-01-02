package com.blueline.harvbest.blueline.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blueline.harvbest.blueline.Clases.utilidades;
import com.blueline.harvbest.blueline.Colecciones.Col1;
import com.blueline.harvbest.blueline.Colecciones.Col2;
import com.blueline.harvbest.blueline.Colecciones.Col3;
import com.blueline.harvbest.blueline.Colecciones.Col4;
import com.blueline.harvbest.blueline.Colecciones.Col5;
import com.blueline.harvbest.blueline.Colecciones.Col6;
import com.blueline.harvbest.blueline.Colecciones.Col7;
import com.blueline.harvbest.blueline.Colecciones.Col8;
import com.blueline.harvbest.blueline.R;


public class RedFragment extends Fragment implements View.OnClickListener
{



    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    private View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RedFragment newInstance(String param1, String param2) {
        RedFragment fragment = new RedFragment();
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
       view= inflater.inflate(R.layout.fragment_red, container, false);


        btn1 = (Button) view.findViewById(R.id.co1);
        btn1.setOnClickListener(this);

        btn2 = (Button) view.findViewById(R.id.co2);
        btn2.setOnClickListener(this);

        btn3 = (Button) view.findViewById(R.id.co3);
        btn3.setOnClickListener(this);

        btn4 = (Button) view.findViewById(R.id.co4);
        btn4.setOnClickListener(this);

        btn5 = (Button) view.findViewById(R.id.co5);
        btn5.setOnClickListener(this);

        btn6 = (Button) view.findViewById(R.id.co6);
        btn6.setOnClickListener(this);

        btn7 = (Button) view.findViewById(R.id.co7);
        btn7.setOnClickListener(this);

        btn8 = (Button) view.findViewById(R.id.co8);
        btn8.setOnClickListener(this);


       return view;
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.co1:

                Toast.makeText(getActivity(), "Accediendo a colección Flexi", Toast.LENGTH_SHORT).show();
                Intent col1 = new Intent(getActivity(), Col1.class);
                getActivity().startActivity(col1);
                break;


            case R.id.co2:
                Toast.makeText(getActivity(), "Accediendo a colección Flexi", Toast.LENGTH_SHORT).show();

                Intent col2 = new Intent(getActivity(), Col2.class);
                getActivity().startActivity(col2);
                break;

            case R.id.co3:
                Toast.makeText(getActivity(), "Accediendo a colección Imagenes de México", Toast.LENGTH_SHORT).show();

                Intent col3 = new Intent(getActivity(), Col3.class);
                getActivity().startActivity(col3);
                break;

            case R.id.co4:
                Toast.makeText(getActivity(), "Accediendo a colección Acrílico", Toast.LENGTH_SHORT).show();

                Intent col4 = new Intent(getActivity(), Col4.class);
                getActivity().startActivity(col4);
                break;

            case R.id.co5:
                Toast.makeText(getActivity(), "Accediendo a colección Arena", Toast.LENGTH_SHORT).show();

                Intent col5 = new Intent(getActivity(), Col5.class);
                getActivity().startActivity(col5);
                break;

            case R.id.co6:
                Toast.makeText(getActivity(), "Accediendo a colección Metal", Toast.LENGTH_SHORT).show();

                Intent col6 = new Intent(getActivity(), Col6.class);
                getActivity().startActivity(col6);
                break;

            case R.id.co7:
                Toast.makeText(getActivity(), "Accediendo a colección Plumas", Toast.LENGTH_SHORT).show();

                Intent col7 = new Intent(getActivity(), Col7.class);
                getActivity().startActivity(col7);
                break;

            case R.id.co8:
                Toast.makeText(getActivity(), "Accediendo a colección Resina", Toast.LENGTH_SHORT).show();

                Intent col8 = new Intent(getActivity(), Col8.class);
                getActivity().startActivity(col8);
                break;
        }


    }
}
