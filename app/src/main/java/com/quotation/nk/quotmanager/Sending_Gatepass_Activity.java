package com.quotation.nk.quotmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quotation.nk.quotmanager.Fragments.Compelete_Send_Fragment;
import com.quotation.nk.quotmanager.Fragments.Pending_Send_Fragment;

import java.util.ArrayList;
import java.util.List;

public class Sending_Gatepass_Activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private SwipeDisable swipeDisable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending__gatepass_);

        swipeDisable = (SwipeDisable) findViewById(R.id.swipdisables);
        swipeDisable.setPagingEnabled(false);
        setupViewPager(swipeDisable);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(swipeDisable);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Pending_Send_Fragment(),"Pending Gatepass");
        adapter.addFragment(new Compelete_Send_Fragment(),"Completed Gatepass");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
