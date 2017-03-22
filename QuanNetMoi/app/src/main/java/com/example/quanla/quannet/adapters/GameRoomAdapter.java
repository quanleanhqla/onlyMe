package com.example.quanla.quannet.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.activities.CoreActivity;
import com.example.quanla.quannet.activities.Maps2Activity;
import com.example.quanla.quannet.adapters.viewholders.GameRoomHolder;
import com.example.quanla.quannet.database.DbContext;
import com.example.quanla.quannet.database.models.GameRoom;
import com.google.android.gms.games.Game;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by QuanLA on 3/19/2017.
 */

public class GameRoomAdapter extends RecyclerView.Adapter<GameRoomHolder> {
    private Context context;
    public void setContext(Context context){
        this.context = context;
    }

    public GameRoomAdapter() {
    }


    @Override
    public GameRoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_main, parent, false);

        GameRoomHolder gameRoomHolder = new GameRoomHolder(itemView);
        return gameRoomHolder;
    }

    @Override
    public void onBindViewHolder(GameRoomHolder holder, int position) {
        final GameRoom gameRoom = DbContext.instance.getAllRooms().get(position);

        holder.bind(gameRoom, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(gameRoom);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.getAllRooms().size();
    }

}
