package domain.muktevi.cityguide;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import domain.muktevi.cityguide.beans.Schedule;
import domain.muktevi.cityguide.beans.Venue;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Vijay Kumar Tummala on 4/13/2016.
 */
public class ScheduleAdapter extends ArrayAdapter<Object> {

    public static String USER = "domain.muktevi.cityguide.USER";
    private Context context;

    public ScheduleAdapter(Context context, Schedule[] schedules) {
        super(context, R.layout.schedule_layout,schedules);
        this.context = context;
    }

    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.schedule_layout, parent, false);
        final Schedule schedule= (Schedule) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView_venue_name);
        textView.setText(schedule.getVenueName());
        TextView tvdate = (TextView) view.findViewById(R.id.tv_date);
        tvdate.setText("Date : "+schedule.getDate());
        TextView tvtime = (TextView) view.findViewById(R.id.tv_time);
        tvtime.setText("Time : "+schedule.getTime());
        ImageView imgview_icon = (ImageView) view.findViewById(R.id.image_icon);
        switch (schedule.getCategory()){
            case "4d4b7105d754a06374d81259":     //food
                imgview_icon.setImageResource(R.drawable.food_icon);
                break;
            case "4d4b7105d754a06373d81259":       //events
                imgview_icon.setImageResource(R.drawable.events);
                break;
            case "4d4b7104d754a06370d81259":        //places
                imgview_icon.setImageResource(R.drawable.places);
                break;
            case "4d4b7105d754a06379d81259":        //transport
                imgview_icon.setImageResource(R.drawable.transport);
                break;
        }
        ImageButton delbtn=(ImageButton) view.findViewById(R.id.img_del);
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestUrl = "https://api.mlab.com/api/1/databases/ase_assignment/collections/schedule/"+schedule.getOid()+"?apiKey=T4RmCJ4GWaqs1nRLHnFoA--K8wrdzly4";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(requestUrl).delete().build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.e("Error from the service", e.getMessage());
                    }
                    @Override
                    public void onResponse(Response response) throws IOException {
                        final String respJson = response.body().string();
                        Log.i("Json String: ", respJson);
                        Intent intent = new Intent(context, MySchedule.class);
                        intent.putExtra(USER ,schedule.getUser());
                        context.startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
}
