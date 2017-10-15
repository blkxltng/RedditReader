package com.blkxltng.redditreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.blkxltng.redditreader.reddit.Child;
import com.blkxltng.redditreader.reddit.RedditJSON;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView mListView;
    List<Child> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

//        GetRawData getRawData = new GetRawData(this);
//        getRawData.execute("https://www.reddit.com/.json");

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RedditClient client = retrofit.create(RedditClient.class);
        Call<RedditJSON> call = client.jsonForSubreddit("3DS");

        call.enqueue(new Callback<RedditJSON>() {
            @Override
            public void onResponse(Call<RedditJSON> call, Response<RedditJSON> response) {
//                List<SubRedditPost> posts = response.body();
//                ArrayList<SubRedditPost> posts = response.body().get(0).getChildren().get(0).getData();
                posts = response.body().getData().getChildren();
                mListView.setAdapter(new SubredditAdapter(getApplicationContext(), posts));
            }

            @Override
            public void onFailure(Call<RedditJSON> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "It naah work", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public void onDownloadComplete(String data, DownloadStatus status) {
//        if(status == DownloadStatus.OK) {
//            Log.d(TAG, "onDownloadComplete: data is " + data);
//        } else {
//            //Download or processing failed
//            Log.e(TAG, "onDownloadComplete failed with status " + status);
//        }
//    }
}
