package domain.muktevi.cityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    public static String USER = "domain.muktevi.cityguide.USER";
    public static String CATEGORY = "domain.muktevi.cityguide.CATEGORY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView txtname=(TextView) findViewById(R.id.name);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if (intent.getStringExtra(Login.USER) != null)
        {
            USER = intent.getStringExtra(Login.USER);
        }
        else if (intent.getStringExtra(MySchedule.USER) != null)
        {
            USER = intent.getStringExtra(MySchedule.USER);
        }
        txtname.setText("Welcome" + USER + "!!");

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void getEvents(View view) {

        Intent intent = new Intent(this,RestaurantSRP.class);
        intent.putExtra(CATEGORY, "EVENTS");
        intent.putExtra(USER, USER);
        startActivity(intent);
    }

    public void getFood(View view) {

        Intent intent = new Intent(this,RestaurantSRP.class);
        intent.putExtra(CATEGORY,"FOOD");
        intent.putExtra(USER, USER);
        startActivity(intent);
    }

    public void getPlaces(View view){
        Intent intent = new Intent(this,RestaurantSRP.class);
        intent.putExtra(CATEGORY,"PLACES");
        intent.putExtra(USER, USER);
        startActivity(intent);
    }
    public void getTransport(View view){
        Intent intent = new Intent(this,RestaurantSRP.class);
        intent.putExtra(CATEGORY,"TRANSPORT");
        intent.putExtra(USER, USER);
        startActivity(intent);
    }
    public void gotoSchedule(View view){
        Intent intent = new Intent(this,MySchedule.class);
        intent.putExtra(USER, USER);
        startActivity(intent);
    }

    public void getTrends(View view) {

        

        Intent intent = new Intent(this,TrendsActivity.class);

        intent.putExtra(USER, USER);
        startActivity(intent);
    }
}
