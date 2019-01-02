package com.blueline.harvbest.blueline.borrar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Soporte on 06/08/2018.
 */

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="https://enriquealarcon03.000webhostapp.com/Login_Alumno.php";
    private Map<String,String> params;
    public LoginRequest (int numcuenta, int nip, Response.Listener<String>listener){
        super (Request.Method.POST,LOGIN_REQUEST_URL, listener, null);

        params=new HashMap<>();
        params.put("numcuenta",numcuenta+"");
        params.put("nip",nip+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
