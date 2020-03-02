package com.example.cibertecproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cibertecproject.Modelo.Event;

import java.util.ArrayList;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private final ArrayList<Event> eventItems;

    public EventListAdapter(ArrayList<Event> eventItems ) {
        this.eventItems = eventItems;
    }

    @NonNull
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_events,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.ViewHolder holder, int position) {
        holder.txtEventItem.setText(eventItems.get(position).getName());
        holder.txtEventSchedule.setText(eventItems.get(position).getSchedule());
    }

    @Override
    public int getItemCount() {
        return eventItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout llRecycler;
        TextView txtEventItem,txtEventSchedule;
        ImageView imgEdit,imgDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEventItem=itemView.findViewById(R.id.txtEventItem);
            txtEventSchedule=itemView.findViewById(R.id.txtEventSchedule);
            imgEdit=itemView.findViewById(R.id.imgEdit);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            llRecycler=itemView.findViewById(R.id.llrecycler);
            itemView.setOnClickListener(this);
            imgEdit.setOnClickListener(this);
            imgDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            switch(view.getId()){
                case R.id.imgEdit:
                    Intent addintent=new Intent(view.getContext(),AddCourseEventActivity.class);
                    view.getContext().startActivity(addintent);
                    break;
                case R.id.imgDelete:
                    // Se elimina
                    Toast.makeText(view.getContext(), "Se Elimina uno", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.llrecycler:
                    Intent intent=new Intent(view.getContext(),EventCreateEditActivity.class);
                    view.getContext().startActivity(intent);
                    break;
            }
        }
    }
}
