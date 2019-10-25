package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button);
        final EditText firstname = findViewById(R.id.editText);
        final EditText lastname = findViewById(R.id.editText2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject obj = new JSONObject();
                try {
                    obj.put("vorname", firstname.getText().toString());
                    obj.put("nachname", lastname.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        new AsyncTask<JSONObject, Void, Integer>() {
            @Override
            protected Integer doInBackground(JSONObject... jsonObjects) {
                Integer r = null;
                try {
                    HttpURLConnection c = (HttpURLConnection) new URL("http://10.0.2.2:8080/server/rest").openConnection();
                    c.setRequestMethod("POST");
                    c.setRequestProperty("Content-Type", "application/json");
                    c.setDoInput(true);

                    OutputStreamWriter sw = new OutputStreamWriter(c.getOutputStream());
                    sw.write(jsonObjects[0].toString());
                    sw.flush();

                    r = c.getResponseCode();
                    c.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return r;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                //super.onPostExecute(r);
                Toast.makeText(MainActivity.this, "http-status: "+integer, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, PersonListActivity.class);
                startActivity(intent);
            }
        }.execute(obj);
            }
        });
    }
}
