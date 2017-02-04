package domain.muktevi.cityguide;

import android.content.Intent;
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
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import domain.muktevi.cityguide.beans.Constants;

public class TwitterTrends extends AppCompatActivity {

    public static ListView listView;

    public static String USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_trends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int woeid = 2430683;
        Intent intent = getIntent();
        USER = intent.getStringExtra(Home.USER);
        String requestUrl = Constants.TRENDS_URL+woeid;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(requestUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Error in Trends", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String respJson = response.body().string();
                Log.i("Json String: ", respJson);
                if (respJson.isEmpty()) {
                    Log.e("response json is empty", respJson);
                }
                Gson gson = new Gson();
                TrendsBO trendsBO = gson.fromJson(respJson, TrendsBO.class);
                int lenght = trendsBO.getTrends().size();
                final String[] trendsArray = (String[]) trendsBO.getTrends().toArray(new String[lenght]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateListView(trendsArray);
                    }
                });
            }
        });

    }

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

    private void populateListView(String[] trendsArray) {
        ArrayAdapter adapter = new TrendsAdapter(this,trendsArray);
        listView = (ListView) findViewById(R.id.trendsListView);
        listView.setAdapter(adapter);
    }

}
