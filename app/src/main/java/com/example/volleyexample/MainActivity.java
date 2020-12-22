package com.example.volleyexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private Button buttonParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();

            }
        });

    }

    private void initLayout() {
        textViewResult = findViewById(R.id.textViewResult);
        buttonParse = findViewById(R.id.button_parse);
    }


    private void jsonParse() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://reqres.in/api/products/3",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    Log.d("VolleyExample", "The response is" + response.getString("data"));
                    int id = jsonObject.getInt("id");
                    Log.d("VolleyExample", "id");
                    String name = jsonObject.getString("name");
                    Log.d("VolleyExample", "name");
                    int year = jsonObject.getInt("year");
                    Log.d("VolleyExample", "year");
                    String color = jsonObject.getString("color");
                    Log.d("VolleyExample", "color");
                    textViewResult.append(String.valueOf(id) + ", " + name + ", " + String.valueOf(year) + ", " + color + "\n\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp", "somthing went wrong");
            }
        });
        requestQueue.add(jsonObjectRequest);


    }
}