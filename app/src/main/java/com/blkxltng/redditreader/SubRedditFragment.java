package com.blkxltng.redditreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by firej on 10/15/2017.
 */

public class SubRedditFragment extends Fragment {

    public static SubRedditFragment newInstance() {
        return new SubRedditFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class SubRedditHolder extends RecyclerView.ViewHolder {

        private SubRedditHolder() {
            super();
        }

        private void bind() {

        }
    }

    private class SubRedditAdapter extends RecyclerView.Adapter<SubRedditHolder> {

        @Override
        public SubRedditHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }
        
        @Override
        public void onBindViewHolder(SubRedditHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
