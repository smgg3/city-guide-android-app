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

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {

    public static String USER = "domain.muktevi.cityguide.USER";
    public static boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void login(View view){

        EditText username = (EditText) findViewById(R.id.editText_user);
        final EditText password = (EditText) findViewById(R.id.editText_pass);
        final Intent intent = new Intent(this,Home.class);
        final Intent mainIntent = new Intent(this,Main.class);
        String urlEncodedName = URLEncoder.encode(username.getText().toString());
        String url = "https://api.mlab.com/api/1/databases/ase_assignment/collections/customer?q=%7B%22email%22%3A%22"+urlEncodedName+"%22%7D&apiKey=T4RmCJ4GWaqs1nRLHnFoA--K8wrdzly4";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Error from the service", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String respJson = response.body().string();
                Log.i("Json String: ", respJson);
                if (respJson.isEmpty()) {
                    Log.e("response json is empty", respJson);
                } else {
                    try {
                        Log.i("++++++++", "++++++++");
                        JSONArray responseJson = new JSONArray(respJson);
                        JSONObject jObj = (JSONObject) responseJson.get(0);
                        String pass = jObj.get("password").toString();
                        String user = jObj.get("email").toString();
                        if (!password.getText().toString().equalsIgnoreCase(pass)) {
                            Log.i("password doesn't match", "-------");
                            startActivity(mainIntent);
                        } else {
                            Log.i("********", "*******");
                            isValid = true;
                            intent.putExtra(USER, user);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    public void signupRedirect(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}
