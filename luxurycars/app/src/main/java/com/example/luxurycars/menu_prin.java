package com.example.luxurycars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class menu_prin extends AppCompatActivity {

    private ViewFlipper vf;
    private int[] image = {R.drawable.logo,R.drawable.lambo };
    private Button com, lista, btncontac, btndetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_prin);


        com = findViewById(R.id.button_comprar);
        btncontac=findViewById(R.id.btncontac);
        lista = findViewById(R.id.button_lista);
        vf = (ViewFlipper)findViewById(R.id.slider);
        btndetalle=findViewById(R.id.btndetalle);

        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_prin.this, comprar.class);
                startActivity(intent);
            }
        });
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_prin.this, lista_autos.class);
                startActivity(intent);
            }
        });
        btncontac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_prin.this, contacto.class);
                startActivity(intent);
            }
        });

        btndetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_prin.this, descrip.class);
                startActivity(intent);
            }
        });



        //slider
        for (int i=0;  i< image.length; i++){

            flip_image(image[i]);

        }
    }

    public void flip_image(int i){

        ImageView viwe = new ImageView(this);
        viwe.setBackgroundResource(i);
        vf.addView(viwe);
        vf.setFlipInterval(2800);
        vf.setAutoStart(true);

        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    }


}