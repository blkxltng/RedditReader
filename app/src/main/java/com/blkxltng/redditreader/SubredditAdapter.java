package com.blkxltng.redditreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blkxltng.redditreader.reddit.Child;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by firej on 10/13/2017.
 */

public class SubredditAdapter extends ArrayAdapter<Child> {

    private Context context;
    private List<Child> values;

    public SubredditAdapter(Context context, List<Child> values) {
        super(context, R.layout.post_item, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.post_item, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.textView);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);

        Child item = values.get(position);

        String message = item.getData().getTitle();
        textView.setText(message);
        String url = item.getData().getThumbnail();
        Picasso.with(getContext()).load(url).into(imageView);

        return row;
    }
}
