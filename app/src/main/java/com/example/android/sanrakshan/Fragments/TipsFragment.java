package com.example.android.sanrakshan.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.sanrakshan.Adapters.TipsAdapter;
import com.example.android.sanrakshan.R;

import java.util.ArrayList;

/**
 * Created by HP on 07-Oct-17.
 */

public class TipsFragment extends Fragment {

    Context ctx;
    ArrayList<String> tips;
    ListView listView;
    TipsAdapter tipsAdapter;
    public TipsFragment(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tips = new ArrayList<>();
        tips.add("The first, and probably most important, component in self-defense is awareness.");
        tips.add("Learn to trust your sixth sense and use it to your full advantage.");
        tips.add("Take Self Defense Training.");
        tips.add("Escape is always the best  option.");
        tips.add("It is important to understand that you CAN and SHOULD defend yourself physically.");
        tips.add("Never depend on any self-defense tool or weapon to stop an attacker.");
        tips.add("Never, ever open your door unless you either are certain you know who’s on the other side.");
        tips.add("Be prepared when you Travel.");
        tipsAdapter = new TipsAdapter(ctx,tips);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tips,container,false);
        listView = (ListView) rootView.findViewById(R.id.lvTips);
        listView.setAdapter(tipsAdapter);
        return  rootView;
    }
}
