package com.ulul.carebuddies.contract;

import com.ulul.carebuddies.base.BasePresenter;
import com.ulul.carebuddies.base.BaseView;
import com.ulul.carebuddies.model.DataInformation;

import java.util.List;

public class PatientContract {
    public interface View extends BaseView {
        void listPatient(List<DataInformation> list);
        void detailPatient(DataInformation user);
    }

    public interface Presenter extends BasePresenter {
        void getList(String id);
        void getPatient(String id);
        void connectPatient(String no_telp);
    }
}
