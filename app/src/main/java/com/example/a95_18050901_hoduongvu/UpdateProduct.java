package com.example.a95_18050901_hoduongvu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateProduct extends AppCompatActivity {
    EditText type,price,country;
    Button btnSave,btnBack3;
    String url="https://60b6e58117d1dc0017b8882b.mockapi.io/Product";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        type=findViewById(R.id.txtUpType);
        price=findViewById(R.id.txtUpPrice);
        country=findViewById(R.id.txtUpCountry);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        type.setText(intent.getStringExtra("type"));
        price.setText(intent.getStringExtra("price"));
        country.setText(intent.getStringExtra("country"));

        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi(url,id);
                Intent intent  = new Intent(getApplicationContext(),ShowInfomation.class);
                startActivity(intent);
            }
        });
        btnBack3=findViewById(R.id.btnBack3);
        btnBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProduct.this,Manager.class);
                startActivity(intent);
            }
        });


    }
    private void PutApi(String url,String id){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UpdateProduct.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateProduct.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("type", type.getText().toString());
                params.put("price", price.getText().toString());
                params.put("country", country.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}