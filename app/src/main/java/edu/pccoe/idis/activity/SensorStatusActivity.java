package edu.pccoe.idis.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.pccoe.idis.R;
import edu.pccoe.idis.utils.HttpRequestTask;
import edu.pccoe.idis.utils.ServerResponse;


public class SensorStatusActivity extends ActionBarActivity {

    private TextView sensorStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_status);
        sensorStatus = (TextView) findViewById(R.id.textView4);
        new ServerRequestTask().execute("sensorStatus");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor_status, menu);
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
            sensorStatus.setText(res.getContent());
        }
    }
}
