package com.aqif.tweetssearcher.searcher.activity.observer;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchActivityObservable
{
    void registerOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver);
    void unregisterOnTweetsSearchActivityObserver(ITweetsSearchActivityObserver tweetsSearchActivityObserver);

    void notifyActivityCreateCalled(AppCompatActivity activity);
    void notifyActivityDestroyCalled();
    void notifyBackPressed();
    void notifyMenuCreated(Menu menu);
}
