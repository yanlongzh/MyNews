package com.example.hui.mynews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hui.mynews.R;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class SettingFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.draftlayout,container,false);
        return view;
    }

}
