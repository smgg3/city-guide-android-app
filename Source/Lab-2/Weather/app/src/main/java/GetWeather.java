package ase.weather;
import android.graphics.Path;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;

import java.lang.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;
import android.opengl.EGLObjectHandle;
public class GetWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Button ObjBtuWeather = (Button)findViewById(R.id.Button_Temp);
        final TextView ObjSetTemp = (TextView)findViewById(R.id.TextView_Result);
        String Result ="";
        ObjBtuWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    ObjSetTemp.setText(getWeatherData());

                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

    }



    public String getWeatherData() {
        HttpURLConnection con = null ;
        InputStream is = null;
        String Result = "";
        String Weather = "";

        try {

            //4393217//4400860
            String URI = "http://api.openweathermap.org/data/2.5/weather?id=4273837&units=metric&appid=a149d0dfbedce700edce4a82cccb6c32";


            con = (HttpURLConnection)( new URL(URI)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            Integer responseCodee = con.getResponseCode();

            if(responseCodee.equals(200))
            {
                is = con.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while((line = rd.readLine()) != null)
                {
                    response.append(line);
                    response.append('\r');
                    response.append('\n');
                }
                is.close();
                rd.close();
                Result =  response.toString();

                JSONObject object = new JSONObject(Result);
                JSONObject ObjWeather =  object.getJSONObject("main");
                Weather = ObjWeather.getString("temp");
            }

            return Result = Weather;

        }
        catch(Exception ex) {

            ex.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return Result ;

    }

    public void Close(View view){
        finish();
    }
}
