package com.example.simon.represent;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Simon on 3/3/16.
 */
public class RepFragment extends Fragment implements View.OnClickListener {


    public static RepFragment create(String title, String text) {
        RepFragment fragment = new RepFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", text);
        int index = 0;
        if (Objects.equals(title, "Barbara Boxer")) {
            index = 1;
        }
        if (Objects.equals(title, "Barbara Lee")) {
            index = 2;
        }
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(
                R.layout.rep_grids, container, false);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView des = (TextView) view.findViewById(R.id.description);
        name.setText(getArguments().getString("title"));
        des.setText(getArguments().getString("description"));
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent(v.getContext(), WatchToPhoneService.class);
        sendIntent.putExtra("row", "" + getArguments().getInt("index"));
        getActivity().startService(sendIntent);

    }
}