package com.blkxltng.redditreader;

import android.support.v4.app.Fragment;

/**
 * Created by firej on 10/15/2017.
 */

public class SubRedditActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SubRedditFragment.newInstance();
    }
}
