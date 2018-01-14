package com.swishsoftwaresolutions.json_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class
Json_example1 extends AppCompatActivity {
    ListView listView;
    ArrayList<DataModel> datamodel;
    TextView name, versionNumber, api;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_example1);
        datamodel = new ArrayList<>();
        listView = (ListView)findViewById(R.id.lv);
        adapter = new Adapter(Json_example1.this,0);
        new Async().execute();
    }

    class Async extends AsyncTask {
        ProgressDialog progressDialog;
        private static final String URI = "http://serviceapi.skholingua.com/open-feeds/list_multipletext_json.php";
        TextView textView;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Json_example1.this);
            progressDialog.setMessage("Getting started");
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String data = HTTP_Connection.getData(URI);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("Android Version List");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String name = obj.getString("Version Name");
                    String version = obj.getString("Version No");
                    String api = obj.getString("API Level");

                    DataModel json = new DataModel(name,version,api);
                    datamodel.add(json);

//                    Log.e("Name:",name);
//                    Log.e("Version No:",version);
//                    Log.e("API:",api);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return datamodel;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            listView.setAdapter(adapter);
        }
    }

    class Adapter extends ArrayAdapter {


        public Adapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return datamodel.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(Json_example1.this);
                convertView = inflater.inflate(R.layout.json_layout, null);
                name = (TextView) convertView.findViewById(R.id.name);
                versionNumber = (TextView) convertView.findViewById(R.id.version);
                api = (TextView) convertView.findViewById(R.id.api);
            }
            name.setText(datamodel.get(position).getName());
            versionNumber.setText(datamodel.get(position).getVersion_no());
            api.setText(datamodel.get(position).getApi());

            return convertView;
        }
    }
}
