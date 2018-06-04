package com.example.admin.mascots.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.admin.mascots.R;
import com.example.admin.mascots.data.CurrentUser;
import com.example.admin.mascots.data.EmailSanitized;
import com.example.admin.mascots.data.Nodes;
import com.example.admin.mascots.models.Mascot;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfoActivityFragment extends Fragment {

    public static final String MASCOT = "com.example.admin.mascots.main.KEY.MASCOT";
    private Mascot mascot;
    CurrentUser user = new CurrentUser();
    String emailSanitized = new EmailSanitized().emailSanitized(user.email());
    private EditText editTextDescription;



    public InfoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mascot = (Mascot) getActivity().getIntent().getSerializableExtra(MainActivityFragment.MASCOT);
        getActivity().setTitle(mascot.getName());

        editTextDescription = view.findViewById(R.id.mascotDescriptionEt);

        }

    @Override
    public void onPause() {
        super.onPause();

        mascot.setDescription(editTextDescription.getText().toString());

        new Nodes().pending(emailSanitized).child(mascot.getUid()).child("description").setValue(mascot.getDescription());
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mascot.getDescription() != null){
            editTextDescription.setText(mascot.getDescription());
        }

    }
}
