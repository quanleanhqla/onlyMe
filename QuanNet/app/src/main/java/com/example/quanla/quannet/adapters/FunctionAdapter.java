package com.example.quanla.quannet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.adapters.viewholders.FunctionHolder;
import com.example.quanla.quannet.database.DbContext;
import com.example.quanla.quannet.database.models.Function;

/**
 * Created by QuanLA on 3/13/2017.
 */

public class FunctionAdapter extends RecyclerView.Adapter<FunctionHolder> {
    @Override
    public FunctionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.function_item, parent, false);

        FunctionHolder functionHolder = new FunctionHolder(itemView);
        return functionHolder;
    }

    @Override
    public void onBindViewHolder(FunctionHolder holder, int position) {
        Function function = DbContext.instance.allFunctions().get(position);

        holder.onBind(function);

    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allFunctions().size();
    }
}
