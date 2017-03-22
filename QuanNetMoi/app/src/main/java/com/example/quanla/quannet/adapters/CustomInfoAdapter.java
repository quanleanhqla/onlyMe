package com.example.quanla.quannet.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.database.models.GameRoom;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by minhh on 22/03/2017.
 */

public class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter {
    private Activity context;
    private GameRoom gameRoom;

    public CustomInfoAdapter(Activity context, GameRoom gameRoom) {
        this.context = context;
        this.gameRoom = gameRoom;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.item,null);
        ImageView imHinh = (ImageView) row.findViewById(R.id.imageView);
        RatingBar rb = (RatingBar) row.findViewById(R.id.rb_gaming);
        TextView tvName = (TextView) row.findViewById(R.id.tv_name);
        TextView tvDiaChi = (TextView) row.findViewById(R.id.tv_diachi);

        imHinh.setImageResource(R.drawable.quannet);
        if(gameRoom.getRate().equals("GOOD")){
            rb.setRating(4);
        }else if(gameRoom.getRate().equals("Excellent")){
            rb.setRating(5);
        }

        tvName.setText(gameRoom.getTitle());
        tvDiaChi.setText(gameRoom.getAddress());

        return row;
    }
}
