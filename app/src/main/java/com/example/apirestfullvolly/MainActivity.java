package com.example.apirestfullvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Btbuscar(View view){

        EditText Correo= findViewById(R.id.txtcorreo);
        EditText Contraseña= findViewById(R.id.txtcontraseña);
        String correo;
        String contraseña;
        correo= Correo.getText().toString();
        contraseña= Contraseña.getText().toString();
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String>datos= new HashMap<String,String>();
        datos.put("correo",correo);
        datos.put("clave",contraseña);

        RequestQueue volley = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/login";
        JsonObjectRequest Rita= new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(datos), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent= new Intent(MainActivity.this, Mostrar.class);
                Bundle b = new Bundle();
                try {
                    b.putString("TOKEN", response.getString("access_token"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);

                }
                intent.putExtras(b);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        volley.add(Rita);
    }

}


