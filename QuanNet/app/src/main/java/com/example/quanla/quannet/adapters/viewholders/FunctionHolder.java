package com.example.quanla.quannet.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.database.models.Function;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QuanLA on 3/13/2017.
 */

public class FunctionHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_function)
    ImageView imgFunction;

    @BindView(R.id.txt_function)
    TextView txtFunction;

    public FunctionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Function function){
        imgFunction.setImageResource(function.getImgIcon());
        txtFunction.setText(function.getNameIcon());
    }
}
