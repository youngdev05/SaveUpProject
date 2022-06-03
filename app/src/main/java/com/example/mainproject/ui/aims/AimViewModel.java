package com.example.mainproject.ui.aims;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AimViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AimViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is aim fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
