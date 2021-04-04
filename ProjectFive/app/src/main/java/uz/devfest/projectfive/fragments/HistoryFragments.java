package uz.devfest.projectfive.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import uz.devfest.projectfive.R;
import uz.devfest.projectfive.fragments.historic.places.MonumentsFragment;
import uz.devfest.projectfive.fragments.historic.places.FamousPlaceFragment;

public class HistoryFragments extends Fragment {
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.places_fragments, container, false);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        viewPager = view.findViewById(R.id.tab_viewpager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MonumentsFragment();
                case 1:
                    return new FamousPlaceFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.historic_place);
                case 1:
                    return getResources().getText(R.string.new_place);

            }
            return null;
        }
    }
}
