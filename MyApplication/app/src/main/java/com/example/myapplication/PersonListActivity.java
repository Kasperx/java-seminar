package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.core.app.Person;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PersonListActivity extends Activity {

    ArrayList <Person> personlist = new ArrayList();
    ArrayAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personlist);
        adapter = new PersonlistAdapter(this, R.layout.person_item);
        ListView v = findViewById(R.id.personlist);
        v.setAdapter(adapter);
        new PersonLoader().execute();
    }

    class PersonLoader extends AsyncTask <Void, Person, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            Integer r = null;
            try {
                HttpURLConnection c = (HttpURLConnection) new URL("http://10.0.2.2:8080/server/rest/persons").openConnection();
                c.setRequestMethod("GET");
                c.setRequestProperty("Accept", "application/json");
                //c.setDoOutput(true);
                c.connect();

                Gson gson = new Gson();
                Person [] pa = gson.fromJson(new InputStreamReader(c.getInputStream()), Person[].class);
                for (Person temp: pa){
                    publishProgress(temp);
                }

                c.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Person... values) {
            for (Person temp: values) {
                personlist.add(temp);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    class PersonlistAdapter extends ArrayAdapter {

        int resourceid;

        public PersonlistAdapter(@NonNull Context context, int resource) {
            super(context, resource);
            this.resourceid = resource;
        }

        @NonNull
        @Override
        public Object getItem (int position){
            return super.getItem(position);
        }

        @Override
        public int getCount(){
            return personlist.size();
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            if (convertView==null) {
                LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //this.super.getView(position, convertView, parent);
                convertView = inflater.inflate(resourceid, parent, false);
            }
            TextView vname = convertView.findViewById(R.id.firstname);
            TextView nname = convertView.findViewById(R.id.lastname);
            vname.setText(personlist.get(position).getVorname());
            nname.setText(personlist.get(position).getNachname());

            return convertView;
        }
    }
}
