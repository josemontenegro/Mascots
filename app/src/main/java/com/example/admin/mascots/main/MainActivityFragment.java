package com.example.admin.mascots.main;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.mascots.R;
import com.example.admin.mascots.adapters.MascotAdapter;
import com.example.admin.mascots.data.CurrentUser;
import com.example.admin.mascots.data.EmailSanitized;
import com.example.admin.mascots.data.Nodes;
import com.example.admin.mascots.models.Mascot;
import com.example.admin.mascots.models.MascotListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MascotListener {

    public static final String MASCOT = "com.example.admin.mascots.main.KEY.MASCOT";
    private MascotAdapter adapter;
    CurrentUser user = new CurrentUser();
    String emailSanitized = new EmailSanitized().emailSanitized(user.email());


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerRW);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter = new MascotAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);

        getActivity().setTitle(new CurrentUser().email());


    }

    @Override
    public void marked(Mascot mascot) {
        Intent intent = new Intent(getActivity(),InfoActivity.class);
        intent.putExtra(MASCOT, (Parcelable) mascot);
        startActivity(intent);

    }

    @Override
    public void markedTwo(Mascot mascot) {
        new Nodes().pending(emailSanitized).child(mascot.getUid()).removeValue();


    }


}
