package edu.pccoe.idis.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import edu.pccoe.idis.R;
import edu.pccoe.idis.utils.HttpRequestTask;
import edu.pccoe.idis.utils.ServerResponse;


public class IDISActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idis);

        Button startButton = (Button) findViewById(R.id.start_btn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ServerRequestTask().execute("start");
            }
        });

        Button waterLevelButton = (Button) findViewById(R.id.waterLevel_btn);
        waterLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new ServerRequestTask().execute("waterLevel");
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), WaterLevelActivity.class);
                startActivity(intent);
            }
        });

        Button sensorStatusButton = (Button) findViewById(R.id.sensorStatus_btn);
        sensorStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // new ServerRequestTask().execute("sensorStatus");
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SensorStatusActivity.class);
                startActivity(intent);
            }
        });

        Button stopButton = (Button) findViewById(R.id.stop_btn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ServerRequestTask().execute("stop");
            }
        });

        Button logoutButton = (Button) findViewById(R.id.logout__btn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_idi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ServerRequestTask extends HttpRequestTask {
        @Override
        protected void onPostExecute(ServerResponse res) {
            CharSequence text = res.getContent();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }
    }
}
