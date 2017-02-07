package com.aqif.tweetssearcher.searcher.swiperefresh.dagger;

import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.ITweetsSwipeRefreshViewModel;
import com.aqif.tweetssearcher.searcher.swiperefresh.viewmodel.TweetsSwipeRefreshViewModel.InjectableTweetsSwipeRefreshLayoutViewModelField;

import dagger.Component;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Component(modules = {TweetsSwipeRefreshLayoutModule.class})

public interface TweetsSwipeRefreshLayoutComponent
{
    void inject(InjectableTweetsSwipeRefreshLayoutViewModelField field);
    ITweetsSwipeRefreshViewModel getTweetsSwipeRefreshViewModel();
}
