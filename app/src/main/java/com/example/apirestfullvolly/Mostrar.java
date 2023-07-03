package com.example.apirestfullvolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mostrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        Bundle b= getIntent().getExtras();
        Map<String, String> datos= new HashMap<String,String>();
        datos.put("fuente","1");
        RequestQueue Mos = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/productos/search";
        JsonObjectRequest Object = new JsonObjectRequest(Request.Method.POST, url,
                new JSONObject(datos), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView mostrar= findViewById(R.id.txtmostrar);
                ArrayList<String> productod = new ArrayList<String>();
                JSONArray productosArray = null;
                try {
                    productosArray = response.getJSONArray("productos");

                    for (int i = 0; i < productosArray.length(); i++) {
                        JSONObject producto = productosArray.getJSONObject(i);
                        productod.add("("+i+")"+producto.getString("descripcion").toString()+
                                " "+" "+" "+producto.getString("descuento").toString()
                                +producto.getString("costo").toString()+" "+" "+" "
                                +producto.getString("stockmax").toString()+"\n");
                    }

                    mostrar.setText(productod.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            //HEADER
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Authorization", "Bearer " + b.getString("TOKEN"));
            return headerMap;
        }



    };

        Mos.add(Object);
}
}