package com.ulul.medbuddies.contract;

import com.google.firebase.auth.FirebaseUser;
import com.ulul.medbuddies.base.BasePresenter;
import com.ulul.medbuddies.base.BaseView;

public class CaretakerContract {
    public interface View extends BaseView<Presenter> {
        void getCurrentUser(FirebaseUser currentUser);
    }

    public interface Presenter extends BasePresenter {
        void setCaretaker(String nama_caretaker, String alamat_caretaker, String no_telp_caretaker);
        void registerCaretaker();
    }
}
