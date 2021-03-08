package com.apakhun.arabicverbstestssecond.fragments;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class ParentFragment extends Fragment {

    private static final String TAG = ParentFragment.class.getSimpleName();

    protected abstract String getFragmentTag();
    protected abstract int getLayoutId();
    protected abstract boolean allowToBackStack();
    protected abstract void onGotArguments(Bundle bundle);

    private static String CURRENT_OPENED_FRAGMENT_TAG;

    private AppCompatActivity activity;
    private void init(AppCompatActivity activity) {
        this.activity = activity;
    }

    public static ParentFragment getCurrentOpenedFragment(AppCompatActivity activity) {
        if (CURRENT_OPENED_FRAGMENT_TAG == null) {
            Log.e(TAG,"CURRENT_OPENED_FRAGMENT_TAG is null");
            return null;
        }

        FragmentManager transaction = activity.getSupportFragmentManager();
        ParentFragment currentOpenedFragment = (ParentFragment) transaction.findFragmentByTag(CURRENT_OPENED_FRAGMENT_TAG);

        return currentOpenedFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            onGotArguments(getArguments());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + getFragmentTag());
        //CURRENT_OPENED_FRAGMENT_TAG = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " +  getFragmentTag());
        CURRENT_OPENED_FRAGMENT_TAG = getFragmentTag();
    }

    public static <T extends ParentFragment> T getInstance(AppCompatActivity activity, Class<? extends ParentFragment> fragmentClass)  {
        FragmentManager transaction = activity.getSupportFragmentManager();

        ParentFragment newFragment = null;
        try {
            newFragment = fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        String fragmentTag = newFragment.getFragmentTag();
        if (fragmentTag == null)
            Log.e("EXCEPTION","Tag of the derived Fragment from ParentFragment is null");

        ParentFragment alreadyExistsFragment = (ParentFragment) transaction.findFragmentByTag(fragmentTag);

        if (alreadyExistsFragment != null) {
            Log.i(TAG, "get already existence fragment");
            return (T) alreadyExistsFragment;
        }

        newFragment.init(activity);
        Log.i(TAG, "get new fragment");
        return (T) newFragment;
    }

    public static <T extends ParentFragment> T getInstance(AppCompatActivity activity, Class<? extends ParentFragment> fragmentClass, Bundle bundle)  {
        ParentFragment newFragment = getInstance(activity, fragmentClass);
        if (bundle == null)
            return (T) newFragment;

        newFragment.setArguments(bundle);
        return (T) newFragment;
    }

    public void open() {
        if (activity == null)
            return;

        if (isAdded()) {
            return;
        }

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
//        transaction.add(getLayoutId(), this, getFragmentTag());
        transaction.replace(getLayoutId(), this, getFragmentTag());
        if (allowToBackStack())
            transaction.addToBackStack(null);

        transaction.commit();
    }

    public void close() {
        if (activity == null)
            return;

        if (!isAdded()) {
            return;
        }

        if (allowToBackStack()) {
            activity.getSupportFragmentManager().popBackStack();
        }
        else {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.remove(this);
            transaction.commit();
        }
    }
}
