package com.ulul.medbuddies.contract;

import com.google.firebase.auth.FirebaseUser;
import com.ulul.medbuddies.base.BasePresenter;
import com.ulul.medbuddies.base.BaseView;

public class HospitalContract {
    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter {
        void setHospital(String nama_rumahsakit, String alamat_rumahsakit, String no_telp_rumahsakit);
        void registerHospital();
    }
}
