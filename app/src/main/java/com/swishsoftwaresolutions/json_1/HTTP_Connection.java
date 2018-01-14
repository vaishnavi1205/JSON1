package com.swishsoftwaresolutions.json_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DELL on 12/9/2017.
 */

public class HTTP_Connection {

    public static String getData(String uri){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String web = "";

        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(1000);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);

            InputStream inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String data = null;

            while((data=reader.readLine())!=null) {
                web += data + "\n";
            }
            return web;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return web;
    }
}
