package com.example.quanla.quannet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.adapters.FunctionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FunctionActivity extends AppCompatActivity {
    @BindView(R.id.rv_function)
    RecyclerView rvFunction;

    private FunctionAdapter functionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);

        functionAdapter = new FunctionAdapter();
        rvFunction.setAdapter(functionAdapter);
        rvFunction.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
