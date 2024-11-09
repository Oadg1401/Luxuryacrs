package com.example.luxurycars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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

public class descrip extends AppCompatActivity {
    private Button btncan, btnbuy;
    private TextView textcar;
    private Spinner spn;
    private ArrayList<String> carList; // Lista para los nombres de los carros

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrip);

        btncan = findViewById(R.id.btncan);
        btnbuy = findViewById(R.id.btnbuy);
        textcar = findViewById(R.id.textcar);
        spn = findViewById(R.id.spn);


    }
}
