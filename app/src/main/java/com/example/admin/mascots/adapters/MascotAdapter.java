package com.example.admin.mascots.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mascots.data.CurrentUser;
import com.example.admin.mascots.data.EmailSanitized;
import com.example.admin.mascots.data.Nodes;
import com.example.admin.mascots.main.MainActivityFragment;
import com.example.admin.mascots.R;
import com.example.admin.mascots.models.Mascot;
import com.example.admin.mascots.models.MascotListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MascotAdapter  extends FirebaseRecyclerAdapter<Mascot,MascotAdapter.MascotHolder>{

          private MascotListener mascotListener;

    public MascotAdapter(LifecycleOwner lifecycleOwner,MascotListener listener) {
        super(new FirebaseRecyclerOptions.Builder<Mascot>()
        .setQuery(new Nodes().pending(new EmailSanitized().emailSanitized(new CurrentUser().email())),Mascot.class)
        .setLifecycleOwner(lifecycleOwner)
        .build()
        );
        this.mascotListener = mascotListener;
    }

    @NonNull
    @Override
    public MascotAdapter.MascotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mascot,parent,false);
        return new MascotHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MascotAdapter.MascotHolder holder, int position, @NonNull Mascot model) {


        holder.mascotNameTV.setText(model.getName());
        holder.mascotNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mascot auxMascot = getItem(holder.getAdapterPosition());
                mascotListener.marked(auxMascot);
            }
        });

        holder.mascotDeleteIw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mascot auxMascot = getItem(holder.getAdapterPosition());
                mascotListener.markedTwo(auxMascot);
            }
        });
    }




    public class MascotHolder extends RecyclerView.ViewHolder {

        private TextView mascotNameTV;
        private ImageView mascotDeleteIw;

        public MascotHolder(View itemView) {
            super(itemView);

            mascotNameTV = itemView.findViewById(R.id.mascotTv);
              mascotDeleteIw = itemView.findViewById(R.id.deleteImgBtn);


        }


    }





}
