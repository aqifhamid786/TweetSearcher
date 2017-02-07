package com.aqif.tweetssearcher.searcher.refresh.dagger;

import com.aqif.tweetssearcher.searcher.refresh.viewmodel.ITweetsRefreshViewModel;
import com.aqif.tweetssearcher.searcher.refresh.viewmodel.TweetsRefreshViewModel.InjectableTweetsRefreshLayoutViewModelField;

import dagger.Component;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Component(modules = {TweetsRefreshLayoutModule.class})

public interface TweetsRefreshLayoutComponent
{
    void inject(InjectableTweetsRefreshLayoutViewModelField field);
    ITweetsRefreshViewModel getTweetsRefreshViewModel();
}
