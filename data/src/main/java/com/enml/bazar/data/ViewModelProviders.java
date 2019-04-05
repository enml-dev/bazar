package com.enml.bazar.data;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

public class ViewModelProviders {
    public static ViewModel get(FragmentActivity fragmentActivity, Class viewModelClass) {
        return androidx.lifecycle.ViewModelProviders.of(fragmentActivity).get(viewModelClass);
    }

    public static ViewModel get(Fragment fragment, Class viewModelClass) {
        return androidx.lifecycle.ViewModelProviders.of(fragment).get(viewModelClass);
    }
}
