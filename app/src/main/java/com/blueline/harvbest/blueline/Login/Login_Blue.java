package com.blueline.harvbest.blueline.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blueline.harvbest.blueline.MainActivity;
import com.blueline.harvbest.blueline.Productos;
import com.blueline.harvbest.blueline.R;


import org.json.JSONException;
import org.json.JSONObject;

public class Login_Blue extends AppCompatActivity {

    EditText etUsuario, etContra;
    Button btnIngresar;
    JSONObject ja;
    ProgressDialog dialog;

    ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__blue);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContra = (EditText) findViewById(R.id.etContra);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);




        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (etContra.getText().toString().length() > 0 & etUsuario.getText().toString().length() > 0)

                    ConsultaPass("http://bluelinemexico.com/ws_app/Validar2.php?Usuario=" + etUsuario.getText().toString()
                            +"&PasswordApp="+etContra.getText().toString());



                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login_Blue.this);
                    builder.setMessage("Error en login, Revisa que tus datos sean correctos")
                            .setNegativeButton("Reintentar", null)
                            .create().show();


                }
            }
        });
    }

    private void ConsultaPass(String URL) {



        Log.i("url", "" + URL);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    ja = new JSONObject(response);
                    //String user = ja.getString("name");

                    String id = ja.getString("id");
                    String contra = ja.getString("name");
                    String username = ja.getString("username");
                    String email = ja.getString("email");
                    String cdestinos = ja.getString("destinos");
                    String tipo = ja.getString("tipousuario");


                    if (contra.equals(etUsuario.getText().toString())) {

                        Toast.makeText(getApplicationContext(), "Bienvenido  " + "Usuario:   " + contra, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login_Blue.this, MainActivity.class);

                        intent.putExtra("id", id);
                        intent.putExtra("name", contra);
                        intent.putExtra("username", username);
                        intent.putExtra("email", email);
                        intent.putExtra("destinos", cdestinos);
                        intent.putExtra("tipousuario", tipo);
                        startActivity(intent);

                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "verifique su contraseña", Toast.LENGTH_SHORT).show();

                    }

                }


                catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(), "El usuario o la contraseña son incorrectos, favor de verificar", Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }
}

