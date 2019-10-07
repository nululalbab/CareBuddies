package com.ulul.medbuddies.contract;

import com.google.firebase.auth.FirebaseUser;
import com.ulul.medbuddies.base.BasePresenter;
import com.ulul.medbuddies.base.BaseView;
import com.ulul.medbuddies.model.DataInformation;

public class AuthContract {
    public interface View extends BaseView<Presenter> {
        void getCurrentUser(FirebaseUser currentUser);
        void checkData();
        void dataUser(DataInformation dataInformation);
    }

    public interface Presenter extends BasePresenter {
        void setLogin(String username, String password);
        void setRegister(String username, String email, String password);
        void login();
        void register();
    }
}
