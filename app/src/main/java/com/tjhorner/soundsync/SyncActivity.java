package com.tjhorner.soundsync;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tjhorner.soundsync.tasks.SyncTask;


public class SyncActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE); getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 0);
        setContentView(R.layout.activity_sync);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            SharedPreferences settings = getSharedPreferences(Constants.PC_ID_FILE, 0);
            String pcId = settings.getString("sessionName", "");
            SyncTask stask = new SyncTask();
            String[] params = {intent.getStringExtra(Intent.EXTRA_TEXT), pcId};
            stask.execute(params);
            Toast.makeText(getApplicationContext(), "Sending to server...", Toast.LENGTH_LONG).show();
        }
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
