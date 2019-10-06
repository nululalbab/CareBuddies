package com.ulul.carebuddies.contract;

import com.ulul.carebuddies.base.BasePresenter;
import com.ulul.carebuddies.base.BaseView;
import com.ulul.carebuddies.model.DataInformation;

public class DataInformationContract {

    public interface View extends BaseView<Presenter>{
        void dataUser(DataInformation dataInformation);
    }

    public interface Presenter extends BasePresenter {
        void getData();
        void setData(String nama, String alamat, String no_telp, String ttl, String jenis_kelamin, String sumber_biaya, String role);
        void submitData();
    }
}
