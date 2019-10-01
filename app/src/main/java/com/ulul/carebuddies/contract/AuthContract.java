package com.ulul.carebuddies.contract;

import com.google.firebase.auth.FirebaseUser;
import com.ulul.carebuddies.base.BasePresenter;
import com.ulul.carebuddies.base.BaseView;

public class AuthContract {
    public interface View extends BaseView<Presenter> {
        void getCurrentUser(FirebaseUser currentUser);
        void checkData();
    }

    public interface Presenter extends BasePresenter {
        void setLogin(String username, String password);
        void setRegister(String username, String email, String password);
        void login();
        void register();
    }
}
