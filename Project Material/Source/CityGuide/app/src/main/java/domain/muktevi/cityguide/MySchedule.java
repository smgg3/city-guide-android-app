package domain.muktevi.cityguide;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import domain.muktevi.cityguide.beans.Schedule;

public class MySchedule extends AppCompatActivity {

    public static String USER = "domain.muktevi.cityguide.USER";
    public static ListView listView;
    public static Schedule[] scheduleArray;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        Intent intent = getIntent();
        if(intent.getStringExtra(Home.USER)!= null) {
            USER = intent.getStringExtra(Home.USER);
        }
        else if(intent.getStringExtra(MySchedule.USER) != null){
            USER = intent.getStringExtra(MySchedule.USER);
        }
        else if(intent.getStringExtra(RestaurantDetails.USER) != null){
            USER = intent.getStringExtra(RestaurantDetails.USER);
        }
        String urlEncodedName = URLEncoder.encode(USER);
        String requestUrl = "https://api.mlab.com/api/1/databases/ase_assignment/collections/schedule?q=%7B%22user%22%3A%22" + urlEncodedName + "%22%7D&apiKey=T4RmCJ4GWaqs1nRLHnFoA--K8wrdzly4";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(requestUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Failure form server", e.getMessage());
            }

            public void onResponse(Response response) throws IOException {
                final String respJson = response.body().string();
                Log.i("Json String: ", respJson);
                if (respJson.isEmpty()) {
                    Log.e("response json is empty", respJson);
                }
                try {

                    Log.i("++++++++", "++++++++");
                    JSONArray responseJson = new JSONArray(respJson);
                    scheduleArray = new Schedule[responseJson.length()];
                    for (int i = 0; i < responseJson.length(); i++) {
                        Schedule schedule = new Schedule();
                        JSONObject obj = (JSONObject) responseJson.get(i);
                        schedule.setUser((String) obj.get("user"));
                        schedule.setVenueName((String) obj.get("venuename"));
                        schedule.setDate((String) obj.get("date"));
                        schedule.setTime((String) obj.get("time"));
                        schedule.setCategory((String) obj.get("category"));
                        schedule.setOid(obj.getJSONObject("_id").getString("$oid"));
                        scheduleArray[i] = schedule;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            populateSchedule(scheduleArray);
                        }
                    });

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }

        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_home:
                homeNavigate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void homeNavigate() {
        Intent intent=new Intent(this, Home.class);
        intent.putExtra(USER, USER);
        startActivity(intent);
    }

    private void populateSchedule(Schedule[] scheduleArray) {
        final ArrayAdapter adapter = new ScheduleAdapter(MySchedule.this,scheduleArray);
        listView = (ListView) findViewById(R.id.theListView);
        listView.setAdapter(adapter);
    }
}
