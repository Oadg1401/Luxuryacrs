package com.example.luxurycars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class lista_autos extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> datalist;
    private ArrayAdapter<String> adapter;

    private Button vol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_autos);

        listView = findViewById(R.id.listView);
        vol = findViewById(R.id.button_vol);

        // Inicializar el ArrayList y el adaptador correctamente
        datalist = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datalist);
        listView.setAdapter(adapter);

        String url = "http://192.168.1.146/luxurycars/generatecars.php";

        // Inicializar RequestQueue dentro de onCreate
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Crear el JsonArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Iterar por cada objeto en el JSONArray
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String marca = jsonObject.getString("marca");
                                String modelo = jsonObject.getString("modelo");
                                String precio = jsonObject.getString("precio");


                                // Agregar el nombre y otros datos al ArrayList
                                datalist.add(marca + " - " + modelo + " - " + precio);
                            }

                            // Notificar al adapter que los datos han cambiado
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Mostrar un mensaje en caso de error
                        Toast.makeText(lista_autos.this, "Error al obtener datos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedData = datalist.get(position);

            Intent intent = new Intent(lista_autos.this, comprar.class);
            intent.putExtra("data", selectedData);
            startActivity(intent);
        });

        // Agregar la solicitud a la cola
        requestQueue.add(jsonArrayRequest);

        // Configurar el botón para volver al menú principal
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lista_autos.this, menu_prin.class);
                startActivity(intent);
            }
        });
    }
}
