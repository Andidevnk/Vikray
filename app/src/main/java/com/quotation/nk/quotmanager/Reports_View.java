package com.quotation.nk.quotmanager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quotation.nk.quotmanager.Fragments.Report_fragment4;
import com.quotation.nk.quotmanager.Fragments.Report_fragment5;
import com.quotation.nk.quotmanager.Fragments.report_fragment1;
import com.quotation.nk.quotmanager.Fragments.report_fragment2;
import com.quotation.nk.quotmanager.Fragments.report_fragment3;

import java.util.ArrayList;
import java.util.List;

public class Reports_View extends AppCompatActivity {

    private TabLayout tabLayout;
    private SwipeDisable swipeDisable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports__view);


        swipeDisable = (SwipeDisable) findViewById(R.id.swipdisables);
        swipeDisable.setPagingEnabled(false);
        setupViewPager(swipeDisable);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(swipeDisable);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new report_fragment1(),"Quotation Details");
        adapter.addFragment(new report_fragment2(),"Next Follow Details");
        adapter.addFragment(new report_fragment3(),"Booking Details");
        adapter.addFragment(new Report_fragment4(),"Not Intrested");
        adapter.addFragment(new Report_fragment5(),"Converted");
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
