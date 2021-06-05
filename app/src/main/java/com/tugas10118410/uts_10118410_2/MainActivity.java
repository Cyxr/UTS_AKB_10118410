//31 Mei 2021, 10118410, Ridwan Caesarahman Julian, IF-10
package com.tugas10118410.uts_10118410_2;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    profile_frag fragprofile;
    catatan_frag fragcatatan;
    info_frag fraginfo;
    MenuItem menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        setupViewPager(viewPager);


        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.catatan:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.info:
                        viewPager.setCurrentItem(2);
                        break;

                }
                return false;
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapt adapter = new ViewPagerAdapt(getSupportFragmentManager());
        fragprofile = new profile_frag();
        fragcatatan = new catatan_frag();
        fraginfo = new info_frag();

        adapter.addFragment(fragprofile);
        adapter.addFragment(fragcatatan);
        adapter.addFragment(fraginfo);
        viewPager.setAdapter(adapter);
    }
}