package com.tjhorner.soundsync.tasks;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by TJ on 9/27/2014.
 */
public class SyncTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();

        try {
            JSONObject data = new JSONObject();
            if(params[0].equals("toggle")){
                HttpPost httppost = new HttpPost("http://soundsync.tjhorner.com/control");
                data.put("session", params[1]);
                httppost.setEntity(new StringEntity(data.toString()));
                httppost.setHeader("Content-type", "application/json");
                HttpResponse response = httpclient.execute(httppost);
                return response.getEntity().toString();
            }else {
                HttpPost httppost = new HttpPost("http://soundsync.tjhorner.com/song");
                data.put("url", params[0]);
                data.put("session", params[1]);
                httppost.setEntity(new StringEntity(data.toString()));
                httppost.setHeader("Content-type", "application/json");

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                return response.getEntity().toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
