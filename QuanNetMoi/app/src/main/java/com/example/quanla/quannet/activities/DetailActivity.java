package com.example.quanla.quannet.activities;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.adapters.CommentAdapter;
import com.example.quanla.quannet.adapters.PhotoAdapter;
import com.example.quanla.quannet.adapters.viewholders.GameRoomHolder;
import com.example.quanla.quannet.database.DbContext;
import com.example.quanla.quannet.events.ActivityReplaceEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.rv_image)
    RecyclerView rvImage;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.dt_comment)
    EditText dtComment;
    @BindView(R.id.dt_send)
    Button btSend;
    @BindView(R.id.dt_address)
    TextView dtAddress;
    @BindView(R.id.dt_title)
            TextView dtTitle;
    PhotoAdapter photoAdapter;
    CommentAdapter commentAdapter;
    String comment ;
    private String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        photoAdapter = new PhotoAdapter();
        commentAdapter = new CommentAdapter();
        rvComment.setAdapter(commentAdapter);
        rvComment.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvImage.setAdapter(photoAdapter);
        rvImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 comment = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment != null){
                    DbContext.instance.addComment(comment);
                    commentAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onClick: 1");
                    Log.d(TAG, String.format("onClick:%s", DbContext.instance.comments.size()));
                }
            }
        });
    }
@Subscribe
public void setDetail(GameRoomHolder gameRoomHolder){
    dtAddress.setText((CharSequence) gameRoomHolder.getTvAddress());
    dtTitle.setText((CharSequence) gameRoomHolder.getTvTitle());
    Log.d(TAG, "setDetail: 1");

}
    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }
}
