package com.pelepolya.designerplanner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProjectPagerAdapter extends FragmentStateAdapter {

    public ProjectPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ProjectNotesFragment();
            case 1:
                return new ProjectCostListFragment();
            case 2:
                return new ProjectAlbumFragment();
            default:
                return new ProjectNotesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
