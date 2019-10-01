package com.ulul.medbuddies.base;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void onLoad();
    void onError();
    void onSuccess();
    void message(String msg);
}
