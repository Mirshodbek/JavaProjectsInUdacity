package uz.devfest.projectsix;

import androidx.fragment.app.Fragment;


public class NewsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return NewsFragment.newInstance();
    }
}