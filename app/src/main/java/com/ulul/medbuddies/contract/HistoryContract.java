package com.ulul.medbuddies.contract;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.ulul.medbuddies.base.BasePresenter;
import com.ulul.medbuddies.base.BaseView;

import java.util.List;

public class HistoryContract {
    public interface View extends BaseView {
        void showResume();
        void listSuccess(List<CalendarDay> list);
        void listFailure(List<CalendarDay> list);
        void listHistory(List<CalendarDay> success, List<CalendarDay> failure);
    }

    public interface Presenter extends BasePresenter {
        void getListSuccess();
        void getListFailure();
        void countHistory();
        void downloadResume();
    }
}
