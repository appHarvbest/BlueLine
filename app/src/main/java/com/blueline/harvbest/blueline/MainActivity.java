package com.blueline.harvbest.blueline;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blueline.harvbest.blueline.Fragments.BlueBlankFragment;
import com.blueline.harvbest.blueline.Fragments.ConsultarUsuarios;
import com.blueline.harvbest.blueline.Fragments.ContenedorFragment;
import com.blueline.harvbest.blueline.Fragments.FamColeFragment;
import com.blueline.harvbest.blueline.Fragments.Formulario;
import com.blueline.harvbest.blueline.Fragments.ListaPersonajesFragment;
import com.blueline.harvbest.blueline.Fragments.RedFragment;
import com.blueline.harvbest.blueline.Login.Login_Blue;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,

        BlueBlankFragment.OnFragmentInteractionListener,
        Formulario.OnFragmentInteractionListener,
        ContenedorFragment.OnFragmentInteractionListener,
        ListaPersonajesFragment.OnFragmentInteractionListener,
        ConsultarUsuarios.OnFragmentInteractionListener,
        FamColeFragment.OnFragmentInteractionListener
{

    final Context context = this;
    TextView nombre, email,tipo,plaza;
    Spinner spin;
    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        Fragment fragment=new ContenedorFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,fragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headView = navigationView.getHeaderView(0);
        email = (TextView) headView.findViewById(R.id.correo);
        nombre = (TextView) headView.findViewById(R.id.usuario);
        tipo = (TextView) headView.findViewById(R.id.tipo);
        plaza = (TextView) headView.findViewById(R.id.plaza);
        spin = (Spinner) headView.findViewById(R.id.spinner);

        Intent intent = getIntent();
        String email2 = intent.getStringExtra("email");
        String username = intent.getStringExtra("username");
        String tipouser = intent.getStringExtra("tipousuario");
        String destinos = intent.getStringExtra("destinos");

      /*
       Intent intent2 = new Intent(Login_Blue.this, Productos.class);
                        intent2.putExtra("username", username);
                        startActivity(intent2);
*/
destinos = destinos.replace("|*|"," "+",");
        email.setText(email2);
        nombre.setText(username);
        tipo.setText(tipouser + ":  ");
        plaza.setText(destinos);

        destinos.split(",");
        String[]elementos=destinos.split(",");

        String[] valores = elementos;
        spin.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, valores));
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {

                Toast.makeText(adapterView.getContext(), "Tendra acceso a los productos de: "+(String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });
    }

    @Override
    public void onBackPressed() {

//SAlir conmensaje de alerta
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Cerrar sesión?");
            alertDialogBuilder
                    .setMessage("Esta seguro de cerrar su sesión")
                    .setCancelable(false)
                    .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
         //salir con un contador de tres segundos

        //////////////////////////////////////
        ////////////////////////////////////

   /*     if (contador == 0) {
            Toast.makeText(getApplicationContext(), "Presione nuevamente para sali", Toast.LENGTH_LONG).show();
            contador++;
        } else {
            super.onBackPressed();
        }
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
            contador=0;
            }
        }.start(); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment = null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_camera) {

            miFragment= new RedFragment();
            fragmentSeleccionado=true;

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            miFragment= new BlueBlankFragment();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_slideshow) {
            miFragment = new Formulario();
            fragmentSeleccionado=true;


        } else if (id == R.id.nav_share) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set title
            alertDialogBuilder.setTitle("Cerrar sesión?");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Esta seguro de cerrar su sesión")
                    .setCancelable(false)
                    .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
            // finish();
            // System.exit(0);



        } else if (id == R.id.nav_send) {

            miFragment = new ContenedorFragment();
            fragmentSeleccionado = true;

        }

        if(fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
