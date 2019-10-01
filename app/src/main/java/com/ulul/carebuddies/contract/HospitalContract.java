package com.ulul.carebuddies.contract;

import com.ulul.carebuddies.base.BasePresenter;
import com.ulul.carebuddies.base.BaseView;

public class HospitalContract {
    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter {
        void setHospital(String nama_rumahsakit, String alamat_rumahsakit, String no_telp_rumahsakit);
        void registerHospital();
    }
}
