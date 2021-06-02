package com.example.a95_18050901_hoduongvu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowInfomation extends AppCompatActivity {
    private List<Product> list= new ArrayList<>();
    private ListView listView;
    private  ProductAdapter productAdapter;
    String url="https://60b6e58117d1dc0017b8882b.mockapi.io/Product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_infomation);
        GetArrayJson(url);
    }

    private void GetArrayJson(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        String id =object.getString("id");
                        String type =object.getString("type");
                        String price =object.getString("price");
                        String country =object.getString("country");
                        Product product = new Product(id,type,price,country);
                        list.add(product);
                        productAdapter= new ProductAdapter(list,getApplicationContext(),R.layout.item);
                        listView=findViewById(R.id.listView);
                        listView.setAdapter(productAdapter);
                    } catch (JSONException e ){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowInfomation.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}