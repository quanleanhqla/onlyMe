package com.example.quanla.quannet.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.quanla.quannet.R;
import com.example.quanla.quannet.adapters.PagerAdapter;
import com.example.quanla.quannet.events.ActivityReplaceEvent;
import com.yalantis.guillotine.animation.GuillotineAnimation;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoreActivity extends AppCompatActivity {

    private static final long RIPPLE_DURATION = 250;

    private LinearLayout llProfile;
    private LinearLayout llNear;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root)
    FrameLayout root;
    @BindView(R.id.content_hamburger)
    View contentHamburger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        ButterKnife.bind(this);



        final int[] ICON = new int[]{
                R.drawable.ic_thumb_up_white_24px,
                R.drawable.ic_fiber_new_white_24px
        };

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.function, null);
        root.addView(guillotineMenu);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();


        llProfile =(LinearLayout) guillotineMenu.findViewById(R.id.profile_group);

        llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoreActivity.this, AccountActivity.class));
            }
        });

        llNear = (LinearLayout) guillotineMenu.findViewById(R.id.near_group);

        llNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoreActivity.this, MapsActivity.class));
            }
        });

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(fragmentManager);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.getTabAt(0).setIcon(ICON[0]);
        tabLayout.getTabAt(1).setIcon(ICON[1]);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void replace(ActivityReplaceEvent activityReplaceEvent){
        startActivity(new Intent(CoreActivity.this, DetailActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
