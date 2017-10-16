package com.blkxltng.redditreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blkxltng.redditreader.reddit.Child;
import com.blkxltng.redditreader.reddit.RedditJSON;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.blkxltng.redditreader.R.id.imageView;

/**
 * Created by firej on 10/15/2017.
 */

public class SubRedditFragment extends Fragment {

    private RecyclerView mSubRedditRecyclerView;
    SubRedditHolder mSubRedditHolder;
    private List<Child> mChildren = new ArrayList<>();
    private SubRedditAdapter mAdapter;

    public static SubRedditFragment newInstance() {
        return new SubRedditFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("https://www.reddit.com/")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//
//        RedditClient client = retrofit.create(RedditClient.class);
//        Call<RedditJSON> call = client.jsonForSubreddit("3DS");
//
//        call.enqueue(new Callback<RedditJSON>() {
//            @Override
//            public void onResponse(Call<RedditJSON> call, Response<RedditJSON> response) {
//                mChildren = response.body().getData().getChildren();
//                Log.d(TAG, "onResponse: download successful");
//                Log.d(TAG, "onResponse: " + mChildren.get(0).getData().getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<RedditJSON> call, Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(getActivity(), "It naah work", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reddit, container, false);

        mSubRedditRecyclerView = (RecyclerView) view.findViewById(R.id.post_recycler_view);
        mSubRedditRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RedditClient client = retrofit.create(RedditClient.class);
        Call<RedditJSON> call = client.jsonForSubreddit("3DS");

        call.enqueue(new Callback<RedditJSON>() {
            @Override
            public void onResponse(Call<RedditJSON> call, Response<RedditJSON> response) {
                mChildren = response.body().getData().getChildren();
                updateUI();
            }

            @Override
            public void onFailure(Call<RedditJSON> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "It naah work", Toast.LENGTH_SHORT).show();
            }
        });

//        Log.d(TAG, "onCreateView: post size 1 is " + mChildren.size());
//        List<Child> posts = mChildren;
//        Log.d(TAG, "onCreateView: post size " + posts.size());
//        if(mAdapter == null) {
//            mAdapter = new SubRedditAdapter(posts);
//            mSubRedditRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.setPosts(posts);
//            mAdapter.notifyDataSetChanged();
//        }

        return  view;
    }

    private class SubRedditHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Child mSubRedditPost;
        private TextView mTextView;
        private ImageView mImageView;

        private SubRedditHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.post_item, parent, false));

            itemView.setOnClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
            mImageView = (ImageView) itemView.findViewById(imageView);
        }

        private void bind(Child post) {
            //Bind the values of the subreddit post to the views
            mSubRedditPost = post;
            mTextView.setText(mSubRedditPost.getData().getTitle());
            Picasso.with(getContext()).load(mSubRedditPost.getData().getThumbnail()).into(mImageView);
        }

        @Override
        public void onClick(View view) {
            //To be implemented later
        }
    }

    private class SubRedditAdapter extends RecyclerView.Adapter<SubRedditHolder> {

        private List<Child> mPosts = new ArrayList<>();

        public SubRedditAdapter(List<Child> posts) {
            mPosts = posts;
        }

        @Override
        public SubRedditHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SubRedditHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(SubRedditHolder holder, int position) {
            Child post = mPosts.get(position);
            holder.bind(post);
        }

        @Override
        public int getItemCount() {
//            Log.d(TAG, "getItemCount: " + mPosts.size());
            return mPosts.size();
        }

        public void setPosts(List<Child> posts) {
            mPosts = posts;
        }
    }

    public void updateUI() {
        List<Child> posts = mChildren;
        if(mAdapter == null) {
            mAdapter = new SubRedditAdapter(posts);
            mSubRedditRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setPosts(posts);
            mAdapter.notifyDataSetChanged();
        }
    }
}
