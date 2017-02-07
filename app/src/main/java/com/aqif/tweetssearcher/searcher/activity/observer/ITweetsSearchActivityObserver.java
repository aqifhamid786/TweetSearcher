package com.aqif.tweetssearcher.searcher.activity.observer;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchActivityObserver
{
    void onActivityCreateCalled(AppCompatActivity activity);
    void onMenuCreated(Menu menu);
    void onActivityDestroyCalled();
    void onBackPressed();
}
