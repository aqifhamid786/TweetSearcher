package com.aqif.tweetssearcher.searcher.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.aqif.tweetssearcher.R;
//import com.aqif.tweetssearcher.searcher.activity.dagger.DaggerTweetsSearchActivityComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.DaggerTweetsSearchActivityComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.TweetsSearchActivityComponent;
import com.aqif.tweetssearcher.searcher.activity.dagger.TweetsSearchActivityModule;
import com.aqif.tweetssearcher.searcher.activity.observer.ITweetsSearchActivityObservable;
import com.aqif.tweetssearcher.searcher.activity.observer.TweetsSearchActivityObservable;

import javax.inject.Inject;
//import com.aqif.tweets.BR;


public class TweetsSearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    @Inject public ITweetsSearchActivityObservable mTweetsSearchActivityObservable;
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

        mTweetsSearchActivityObservable.registerOnTweetsSearchActivityObserver(mTweetsSearchActivityViewModel);
        mTweetsSearchActivityObservable.notifyActivityCreateCalled(this);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mTweetsSearchActivityObservable.notifyActivityDestroyCalled();
    }

    @Override
    public void onBackPressed()
    {
        mTweetsSearchActivityObservable.notifyBackPressed();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.tweets_search_menu, menu);
        mTweetsSearchActivityObservable.notifyMenuCreated(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }


        return true;
    }
}
