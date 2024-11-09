package com.example.luxurycars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class comprar extends AppCompatActivity {

    private Button bton, bton_can;
    private TextView conf, tvPrecio, tvSeguro, tvTotal;
    private Spinner spn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comprar);

        bton = findViewById(R.id.button_com);
        conf = findViewById(R.id.tvCompra);
        bton_can = findViewById(R.id.button_volm);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvSeguro = findViewById(R.id.tvSeguro);
        spn2 = findViewById(R.id.spn2);
        tvTotal=findViewById(R.id.tvTotal);

        // Configurar el spinner y sus eventos
        ArrayList<String> carsList = new ArrayList<>();
        // Aquí debes agregar los modelos de autos que tienes disponibles en tu lista
        carsList.add("Phantom");
        carsList.add("Continental GT");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn2.setAdapter(adapter);

        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCar = parent.getItemAtPosition(position).toString();

                // Realizamos la solicitud para obtener los detalles del carro seleccionado
                getCarDetails(selectedCar);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Si no se selecciona nada, no hacer nada
            }
        });

        // Volver a la pantalla principal
        bton_can.setOnClickListener(v -> {
            Intent intent = new Intent(comprar.this, menu_prin.class);
            startActivity(intent);
        });

        // Acción para realizar la compra
        bton.setOnClickListener(v -> {
            conf.setText("Compra realizada");
        });
    }

    private void getCarDetails(String selectedCar) {
        // En este método haces una solicitud a tu PHP para obtener los detalles del carro
        String url = "http://192.168.1.146/luxurycars/getinfocars.php?modelo=" + selectedCar;

        // Aquí realizas la solicitud de red para obtener la información del carro.
        JsonArrayRequest request = new JsonArrayRequest(url, response -> {
            try {
                if (response.length() > 0) {
                    // Asumiendo que solo hay un carro con ese modelo
                    JSONObject car = response.getJSONObject(0);
                    String precio = car.getString("precio");
                    String seguro = car.getString("seguro");

                    // Mostrar los datos del carro en los TextViews correspondientes
                    tvPrecio.setText(precio);
                    tvSeguro.setText(seguro);

                    int precioValue = Integer.parseInt(precio);
                    int seguroValue = Integer.parseInt(seguro);

                    // Calcular el total
                    int total = precioValue + seguroValue;

                    // Mostrar el total en tvTotal
                    tvTotal.setText(String.valueOf(total));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            // Mostrar un mensaje de error si no se puede obtener los datos
            tvPrecio.setText("Error al obtener datos.");
            tvSeguro.setText("Error al obtener datos.");
        });

        // Añadir la solicitud a la cola de Volley
        Volley.newRequestQueue(this).add(request);
    }
}
