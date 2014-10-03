package com.tjhorner.soundsync;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tjhorner.soundsync.tasks.SyncTask;


public class HelloActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        final SharedPreferences settings = getSharedPreferences(Constants.PC_ID_FILE, 0);
        final Button saveBtn = (Button) findViewById(R.id.setPcIdButton);
        final EditText pcIdBox = (EditText) findViewById(R.id.pcIdBox);
        pcIdBox.setText(settings.getString("sessionName", ""));
        final Button pausePlayBtn = (Button) findViewById(R.id.pausePlayBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("sessionName", pcIdBox.getText().toString());
                editor.apply();
                Toast.makeText(v.getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        pausePlayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pcId = settings.getString("sessionName", "");
                SyncTask stask = new SyncTask();
                String[] params = {"toggle", pcId};
                stask.execute(params);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
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
