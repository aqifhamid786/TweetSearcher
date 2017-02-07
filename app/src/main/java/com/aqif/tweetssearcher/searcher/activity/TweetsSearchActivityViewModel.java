package com.aqif.tweetssearcher.searcher.activity;

import com.aqif.tweetssearcher.searcher.activity.observer.ITweetsSearchActivityObserver;
import com.aqif.tweetssearcher.searcher.activity.observer.TweetsSearchActivityObservable;
import com.aqif.tweetssearcher.searcher.search.TweetsSearchViewModel;

import javax.inject.Inject;

/**
 * Created by aqifhamid on 2/7/17.
 */

public class TweetsSearchActivityViewModel implements
        ITweetsSearchActivityObserver,
        ITweetsSearchActivityViewModel
{
    @Inject
    private TweetsSearchViewModel mSearchViewModel;

    @Inject
    public TweetsSearchActivityViewModel()
    {

    }


    @Override
    public void onActivityCreated()
    {

    }

    @Override
    public void onMenuCreated()
    {

    }
}
