package edu.pccoe.idis.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.pccoe.idis.R;
import edu.pccoe.idis.utils.CircleProgress;
import edu.pccoe.idis.utils.HttpRequestTask;
import edu.pccoe.idis.utils.ServerResponse;

public class WaterLevelActivity extends ActionBarActivity {

    private CircleProgress circleProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level);
        circleProgress = (CircleProgress) findViewById(R.id.circle_progress);
        new ServerRequestTask().execute("waterLevel");
        Button refreshButton = (Button) findViewById(R.id.refresh_btn);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ServerRequestTask().execute("waterLevel");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_water_level, menu);
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

    private class ServerRequestTask extends HttpRequestTask{
        @Override
        protected void onPostExecute(ServerResponse res) {
            Integer waterLevel = new Integer(res.getContent());
            circleProgress.setProgress(waterLevel);
        }
    }
}
