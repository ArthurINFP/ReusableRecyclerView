package com.example.lab05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<MultipleViewHolder.AbstractViewHolder> {
    public static int TASK_ONE = 1, TASK_TWO = 2, TASK_THREE = 3, TASK_FOUR = 4,TASK_FIVE=5;
    private int taskID;
    private Context context;
    private List data;
    private LayoutInflater inflater;

    public RecyclerAdapter(Context context, List data, int taskID) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.taskID = taskID;
    }

    // The onCreateViewHolder just need to call the Factory class to get the ViewHolder corresponding taskID
    // The Layout to inflate also work the same way
    // In this way the function can return the matching ViewHolder without using if else thus very reusable
    @NonNull
    @Override
    public MultipleViewHolder.AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return FactoryViewHolder.getViewHolder(inflater
                .inflate(FactoryViewHolder.getLayoutID(taskID),parent,false)
                ,this,context,taskID);
    }

    // In this function The ViewHolder argument is the AbstractViewHolder that we have created
    // This function doesn't know which ViewHolder class it's dealing with but when to run
    // the corresponding update() method in that class will be called
    @Override
    public void onBindViewHolder(@NonNull MultipleViewHolder.AbstractViewHolder holder, int position) {
        holder.update(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public Context getContext() {
        return context;
    }

    public List getData() {
        return data;
    }

    public Object getDataAtPosition(int adapterPosition) {
        return data.get(adapterPosition);
    }

    public void removeDataAtPosition(int adapterPosition) {
        data.remove(adapterPosition);
    }

    public void addFiveStudent() {
        for (int i = 0; i < 5; i++) {
            data.add("User " + data.size());
            notifyItemInserted(data.size());
        }

    }

    public void deleteFiveStudent() {
        if (data.size() == 0) {
            Toast.makeText(context, "There is no user available", Toast.LENGTH_SHORT).show();

        } else if (data.size() < 5) {
            data.clear();
            notifyDataSetChanged();
        } else {
            notifyItemRangeRemoved(data.size() - 5, 5);
            for (int i = 0; i < 5; i++) {
                data.remove(data.size()-1);
            }
        }
    }
}
