package com.aqif.tweetssearcher.searcher.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by aqifhamid on 2/7/17.
 */

public interface ITweetsSearchActivityViewModel
{

    void onActivityCreateCalled(AppCompatActivity activity);
    void onActivityStartCalled();
    void onActivityResumeCalled();
    void onActivityPauseCalled();
    void onActiviyStopCalled();
    void onActivityDestroyCalled();
    void onMenuCreated(Menu menu);
    boolean onBackPressed();
}
