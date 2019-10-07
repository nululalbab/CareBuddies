package com.ulul.medbuddies;

import com.ulul.medbuddies.contract.ScheduleContract;
import com.ulul.medbuddies.presenter.SchedulePresenter;

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
