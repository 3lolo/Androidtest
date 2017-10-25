package com.pozniak.anatolii.androidtest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.pozniak.anatolii.androidtest.coomon.OnTabSelected;
import com.pozniak.anatolii.androidtest.fragments.AnimalFragment;

import static com.pozniak.anatolii.androidtest.ProfileActivity.TAB_CAT;
import static com.pozniak.anatolii.androidtest.ProfileActivity.TAB_DOG;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TAB_POSITION = "current position";
    public static final String ANIMAL_TYPE_CAT = "cat";
    public static final String ANIMAL_TYPE_DOG = "dog";
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new OnTabSelected() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
        });

        int curTabPosition = (savedInstanceState != null) ? savedInstanceState.getInt(KEY_TAB_POSITION, 0) : 0;
        tabLayout.getTabAt(curTabPosition).select();
    }


    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case TAB_CAT:
                replaceFragment(AnimalFragment.newInstance(ANIMAL_TYPE_CAT), ANIMAL_TYPE_CAT);
                break;
            case TAB_DOG:
                replaceFragment(AnimalFragment.newInstance(ANIMAL_TYPE_DOG), ANIMAL_TYPE_DOG);
                break;
        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(tag) != null) {
            if (!fm.getBackStackEntryAt(0).getName().equals(tag)) {
                fm.beginTransaction().replace(R.id.container, fm.findFragmentByTag(tag)).commit();
            }
        } else {
            fm.beginTransaction()
                    .add(R.id.container, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_TAB_POSITION, tabLayout.getSelectedTabPosition());
    }
}
