package com.jying.taobao.Module.Home;

import com.jying.taobao.Base.BasePresenter;
import com.jying.taobao.Base.BaseView;
import com.jying.taobao.Bean.HomeBottom;
import com.jying.taobao.Bean.HomeTop;

import java.util.List;

/**
 * Created by Jying on 2017/11/21.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showTop(HomeTop homeTop);
        void showBottom(HomeBottom homeBottom);
    }

    interface Presenter extends BasePresenter {
        List<String> getCarouselImages();

        HomeTop getTopLists();

        HomeBottom getBottomLists(int page);
    }
}
