package com.sharif111.fingerprintathentication;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    RealmResults<Notes> noteList;

    public MyAdapter(Context context, RealmResults<Notes> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Notes note = noteList.get(position);
      holder.titleOutput.setText(note.getTitle());
      holder.descriptionOutput.setText(note.getDescription());

      String formatedTime = DateFormat.getTimeInstance().format(note.createdTime);
      holder.timeOutput.setText(formatedTime);
      // adding on click listener for item of recycler view.
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu menu = new PopupMenu(context, view);
                menu.getMenu().add("Delete Note");
                menu.getMenu().add("Edit");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("Delete Note")) {
                            // deleting note
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();

                            Toast.makeText(context, "Note Deleted Successfully", Toast.LENGTH_SHORT).show();

                        }

                        else if (menuItem.getTitle().equals("Edit")) {
                            // Editing note
//                            Intent editIntent = new Intent(context, AddNoteActivity.class);
//                            editIntent.putExtra("EDIT_MODE", true);
//                            editIntent.putExtra("EDIT_NOTE", noteUpdateActivity);
//                            context.startActivity(editIntent);
                        }
                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });
    }



    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
            timeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
