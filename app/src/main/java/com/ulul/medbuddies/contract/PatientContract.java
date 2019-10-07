package com.ulul.medbuddies.contract;

import com.ulul.medbuddies.base.BasePresenter;
import com.ulul.medbuddies.base.BaseView;
import com.ulul.medbuddies.model.DataInformation;

import java.util.List;

public class PatientContract {
    public interface View extends BaseView {
        void listPatient(List<DataInformation> list);
        void detailPatient(DataInformation user);
    }

    public interface Presenter extends BasePresenter {
        void getList();
        void getPatient(String id);
        void connectPatient(String no_telp);
    }
}
