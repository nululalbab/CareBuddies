package com.ulul.carebuddies;

import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.presenter.SchedulePresenter;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SchedulePresenterTest {
    private SchedulePresenter presenter;

    @Mock
    ScheduleContract.View view;

    @Captor
    private ArgumentCaptor<ScheduleContract.View> callback;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        presenter = new SchedulePresenter(view);
    }


}
