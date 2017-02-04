package domain.muktevi.cityguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Muktevi on 5/2/2016.
 */
public class TrendsAdapter extends ArrayAdapter<Object> {

    public TrendsAdapter (Context context, String[] trends){
        super(context,R.layout.trends_list_item,trends);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.trends_list_item, parent, false);
        String trend = (String) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView_trend);
        textView.setText(trend);
        return view;
    }
}
