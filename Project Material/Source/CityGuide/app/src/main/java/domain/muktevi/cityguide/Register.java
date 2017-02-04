package domain.muktevi.cityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import domain.muktevi.cityguide.beans.User;

public class Register extends AppCompatActivity {

    public static String USER = "domain.muktevi.cityguide.USER";
    public static String NAME = "domain.muktevi.cityguide.NAME";
    public static String EMAIL = "domain.muktevi.cityguide.EMAIL";
    public static String PASSWORD = "domain.muktevi.cityguide.PASSWORD";
    public static String PHONE = "domain.muktevi.cityguide.PHONE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public void doSignup(View view)  {
        Intent intent = new Intent(this,Home.class);
        final Intent mainIntent = new Intent(this,Main.class);
        EditText name = (EditText) findViewById(R.id.editText_name);
        EditText email = (EditText) findViewById(R.id.editText_user);
        EditText password = (EditText) findViewById(R.id.editText_password);
        EditText phone = (EditText) findViewById(R.id.editText_phone);
        User user = new User();
        Gson gson = new Gson();
        user.setEmail(email.getText().toString());
        user.setName(name.getText().toString());
        user.setPassword(password.getText().toString());
        user.setPhone(phone.getText().toString());
        String userJSON = gson.toJson(user);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, userJSON);
        String requestUrl = "https://api.mlab.com/api/1/databases/ase_assignment/collections/customer?apiKey=T4RmCJ4GWaqs1nRLHnFoA--K8wrdzly4";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(requestUrl).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Error from the service",e.getMessage());
                startActivity(mainIntent);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String respJson = response.body().string();
                Log.i("Json String: ", respJson);
                if (respJson.isEmpty() ){
                    Log.e("response json is empty",respJson);
                }

            }
        });
        intent.putExtra(USER,name.getText().toString());
        startActivity(intent);
    }

    public void loginRedirect(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}
