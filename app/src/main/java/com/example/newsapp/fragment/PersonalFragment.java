package com.example.newsapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.newsapp.FirstPage;
import com.example.newsapp.R;
import com.example.newsapp.about;
import com.example.newsapp.changepwd;
import com.example.newsapp.collect;


public class PersonalFragment extends Fragment {

    private RelativeLayout collect;
    private RelativeLayout change;
    private RelativeLayout exit;
    private RelativeLayout about;

    private View rootView;

    public PersonalFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        }
        init();
        return rootView;
    }

    private void init() {
        collect=rootView.findViewById(R.id.rl_collect);
        change=rootView.findViewById(R.id.rl_settings);
        exit=rootView.findViewById(R.id.rl_exit);
        about=rootView.findViewById(R.id.rl_about);
//收藏
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.newsapp.collect.class);
                startActivity(intent);
            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), changepwd.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), FirstPage.class);
                startActivity(intent);
            }
        });
//关于我们
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), about.class);
                startActivity(intent);
            }
        });



    }

}
