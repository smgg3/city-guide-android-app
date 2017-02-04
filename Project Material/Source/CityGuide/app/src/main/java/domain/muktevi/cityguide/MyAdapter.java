package domain.muktevi.cityguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import domain.muktevi.cityguide.beans.Category;
import domain.muktevi.cityguide.beans.Venue;

/**
 * Created by Muktevi on 2/15/2016.
 */
public class MyAdapter extends ArrayAdapter<Object> {

    public static String imageUrl = "https://foursquare.com/img/categories/food/default_64.png";
    private String cat_id;
    public MyAdapter(Context context, Venue[] venues, String category_id) {
        super(context, R.layout.row_layout,venues);
        cat_id = category_id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        Venue venue = (Venue) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView_hotel_name);
        textView.setText(venue.getName());
        TextView textView1 =(TextView) view.findViewById(R.id.textView_address);
        textView1.setText(venue.getLocation().getCity() + " , " + venue.getLocation().getState());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_icon);
        switch (cat_id){
            case "4d4b7105d754a06374d81259":     //food
                imageView.setImageResource(R.drawable.food_icon);
                break;
            case "4d4b7105d754a06373d81259":       //events
                imageView.setImageResource(R.drawable.events);
                break;
            case "4d4b7104d754a06370d81259":        //places
                imageView.setImageResource(R.drawable.places);
                break;
            case "4d4b7105d754a06379d81259":        //transport
                imageView.setImageResource(R.drawable.transport);
                break;
        }

        return view;
    }
}
