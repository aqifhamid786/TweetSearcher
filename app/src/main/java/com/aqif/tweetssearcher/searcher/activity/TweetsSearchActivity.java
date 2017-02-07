package com.aqif.tweetssearcher.searcher.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.aqif.tweetssearcher.R;
import com.aqif.tweetssearcher.searcher.activity.dagger.DaggerTweetsSearchActivityComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.TweetsSearchActivityComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.TweetsSearchActivityModule;

import javax.inject.Inject;

public class TweetsSearchActivity extends AppCompatActivity
{

    @Inject public ITweetsSearchActivityViewModel mTweetsSearchActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets_search);

        TweetsSearchActivityComponent component = DaggerTweetsSearchActivityComponent.builder()
                .tweetsSearchActivityModule(new TweetsSearchActivityModule(this))
                .build();
        component.inject(this);

        mTweetsSearchActivityViewModel.onActivityCreateCalled(this);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mTweetsSearchActivityViewModel.onActivityStartCalled();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mTweetsSearchActivityViewModel.onActivityResumeCalled();
    }

    @Override
    protected void onPause()
    {

        mTweetsSearchActivityViewModel.onActivityPauseCalled();
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        mTweetsSearchActivityViewModel.onActiviyStopCalled();
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        mTweetsSearchActivityViewModel.onActivityDestroyCalled();
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        if(!mTweetsSearchActivityViewModel.onBackPressed())
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.tweets_search_menu, menu);
        mTweetsSearchActivityViewModel.onMenuCreated(menu);
        return true;
    }


}
