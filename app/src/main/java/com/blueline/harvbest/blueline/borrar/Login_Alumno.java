package com.blueline.harvbest.blueline.borrar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.blueline.harvbest.blueline.MainActivity;
import com.blueline.harvbest.blueline.R;

import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.INVISIBLE;

public class Login_Alumno extends AppCompatActivity {

    TextView registrarAlumno;
    EditText et_usuarioA, et_passwordA;
    Button btn_logM;
    ProgressDialog progreso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__alumno);

        registrarAlumno = (TextView) findViewById(R.id.TV_registrar_A);
        et_usuarioA = (EditText) findViewById(R.id.TV_usuario_A);
        et_passwordA = (EditText) findViewById(R.id.TV_password_A);
        btn_logM = (Button) findViewById(R.id.Btn_iniciar_A);





        btn_logM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(et_usuarioA.getText().toString().length() > 0 & et_passwordA.getText().toString().length() >0)
                {


                final int numcuenta = Integer.parseInt(et_usuarioA.getText().toString());
                final int nip= Integer.parseInt(et_passwordA.getText().toString());
                final ProgressBar ProgressBar = (android.widget.ProgressBar) findViewById(R.id.progressBar5);

                ProgressBar.setVisibility(view.VISIBLE);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String nombre =jsonResponse.getString("nombre");
                                String apellidop =jsonResponse.getString("apellidop");
                                String apellidom =jsonResponse.getString("apellidom");
                                int semestre =jsonResponse.getInt("semestre");
                                //int grupo =jsonResponse.getInt("grupo");
                                String correo =jsonResponse.getString("correo");
                               // int telefono =jsonResponse.getInt("telefono");


                               Intent intent = new Intent(Login_Alumno.this, MainActivity.class);
                                intent.putExtra("numcuenta",numcuenta);
                                intent.putExtra("nip",nip);



                                Login_Alumno.this.startActivity(intent);


                                finish();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login_Alumno.this);
                                builder.setMessage("Error en login, Revisa que tus datos sean correctos")
                                        .setNegativeButton("Reintentar",null)
                                        .create().show();
                                ProgressBar.setVisibility(INVISIBLE);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequestA = new LoginRequest(numcuenta, nip, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login_Alumno.this);
                queue.add(loginRequestA);
            }

            else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login_Alumno.this);
                builder.setMessage("Error en login, Existen campos vacios")
                        .setNegativeButton("Reintentar",null)
                        .create().show();

                }
            }
        });

    }
}
