package domain.muktevi.cityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

import domain.muktevi.cityguide.beans.Category;
import domain.muktevi.cityguide.beans.Constants;
import domain.muktevi.cityguide.beans.RestaurantResult;
import domain.muktevi.cityguide.beans.Venue;

public class RestaurantSRP extends AppCompatActivity {

    public static String USER = "domain.muktevi.cityguide.USER";
    public static String CATEGORY = "domain.muktevi.cityguide.CATEGORY";
    public static String imageUrl = "https://foursquare.com/img/categories/food/default_64.png";
    public static String msg = "You have opted : ";
    public static String category_id = "";
    public static ListView listView;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_srp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView img_cat = (ImageView) findViewById(R.id.imageView_icon);
        Intent intent = getIntent();
        String category = intent.getStringExtra(Home.CATEGORY);
        USER = intent.getStringExtra(Home.USER);

        switch (category){
            case "FOOD": category_id = "4d4b7105d754a06374d81259";
                break;
            case "EVENTS": category_id = "4d4b7105d754a06373d81259";
                break;
            case "PLACES" : category_id = "4d4b7104d754a06370d81259";
                break;
            case "TRANSPORT": category_id = "4d4b7105d754a06379d81259";
                break;

        }

        String requestUrl = Constants.URL+"near=Kansas City&"+"categoryId="+category_id+"&"+"limit=15&"+"client_id="+Constants.CLIENT_ID+"&"+"client_secret="+Constants.CLIENT_SECRET+"&v=20160212";
        OkHttpClient client = new OkHttpClient();
        // Date date = new Date();
        Request request = new Request.Builder().url(requestUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Failure form server", e.getMessage());
            }

            public void onResponse(Response response) throws IOException {
                final String respJson = response.body().string();
                Log.i("Json String: ", respJson);
                if (respJson.isEmpty() ){
                    Log.e("response json is empty",respJson);
                }
                gson = new Gson();
                RestaurantResult restaurantResult = gson.fromJson(respJson,RestaurantResult.class);
                int length = restaurantResult.getResponse().getVenues().size();
                final Venue[] venues = (Venue[]) restaurantResult.getResponse().getVenues().toArray(new Venue[length]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateListView(venues);
                    }
                });
            }
        });

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

    public void populateListView(Venue[] venuList) {
        final ArrayAdapter adapter = new MyAdapter(RestaurantSRP.this, venuList,category_id);
        listView = (ListView) findViewById(R.id.theListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //String optedMessage = msg + String.valueOf(adapterView.getItemAtPosition(position));
                Venue venue = (Venue) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(RestaurantSRP.this, RestaurantDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.SERIAL_KEY,venue);
                intent.putExtras(bundle);
                intent.putExtra(USER, USER);
                intent.putExtra(CATEGORY,category_id);
                startActivity(intent);
            }
        });
    }
}
