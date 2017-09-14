package raj.saraogi.com.xentest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import raj.saraogi.com.xentest.holder.Event;

//package com.example.rajsaraogi.xenisis;

public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.Holder> {
    List<Event> events = new ArrayList<Event>();
    Context context;
    MainActivity mainActivity;
    AdapterView.OnItemClickListener mItemClickListener;
    public EventCardAdapter(List<Event> mainCards, Context context) {
        this.events=mainCards;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_card, viewGroup, false);
        Holder mainCardViewHolder = new Holder(view);
        return mainCardViewHolder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int i) {
        final Event object = events.get(i);
        Log.e("events", object.eventName);
        holder.image.setImageResource(object.imageName);
        holder.name.setText(object.eventName);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        public Holder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imEventIcon);
            name = (TextView) itemView.findViewById(R.id.tvEventName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Holder holder = (Holder) v.getTag();
            int pos = getPosition();
            String eventId = events.get(pos).getEventId();
           context.startActivity(new Intent(context,EventDetails.class).putExtra("event_id",eventId));
        }
    }
}

