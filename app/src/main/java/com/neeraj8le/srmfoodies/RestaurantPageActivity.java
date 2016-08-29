package com.neeraj8le.srmfoodies;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.neeraj8le.srmfoodies.fragment.DescriptionFragment;
import com.neeraj8le.srmfoodies.fragment.InfoFragment;
import com.neeraj8le.srmfoodies.fragment.MapFragment;
import com.neeraj8le.srmfoodies.fragment.OrderFragment;
import com.neeraj8le.srmfoodies.fragment.PhotosFragment;
import com.neeraj8le.srmfoodies.model.Mapping;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPageActivity extends AppCompatActivity {

    ArrayList<Mapping> tempMapping;

    ImageSwitcher imageSwitcher;
    int images[];
    int currentImage;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra("restaurantName"));

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        images = new int[]{R.drawable.restaurant_image_1, R.drawable.restaurant_image_2, R.drawable.restaurant_image_3};
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        currentImage = 0;
        imageSwitcher.setImageResource(images[currentImage]);
        imageSwitcher.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentImage < images.length-1) {
                    currentImage++;
                    imageSwitcher.setImageResource(images[currentImage]);
                }
                else {
                    currentImage = 0;
                    imageSwitcher.setImageResource(images[currentImage]);
                }
                imageSwitcher.postDelayed(this, 4000);
            }
        }, 4000);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        tempMapping = getIntent().getExtras().getParcelableArrayList("tempMapping");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        setupViewPager(mViewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }

    public void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DescriptionFragment(), "DESCRIPTION", null);
        adapter.addFragment(new OrderFragment(), "ORDER", tempMapping);
        adapter.addFragment(new PhotosFragment(), "PHOTOS", null);
        adapter.addFragment(new MapFragment(), "MAP", null);
        adapter.addFragment(new InfoFragment(), "INFO", null);
        viewPager.setAdapter(adapter);
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title, ArrayList<Mapping> tempMapping)
        {
            if (tempMapping != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("tempMapping", tempMapping);
                fragment.setArguments(bundle);
            }
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
