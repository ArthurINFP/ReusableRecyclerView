package com.example.lab05;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

// This is an Factory Class to create the ViewHolder corresponding with the Task so Adapter doesn't need to
public class FactoryViewHolder {
    public static int TASK_ONE = 1, TASK_TWO = 2, TASK_THREE = 3, TASK_FOUR = 4, TASK_FIVE = 5;

    private FactoryViewHolder() {}

    // Create ViewHolder class match with Task
    public static MultipleViewHolder.AbstractViewHolder getViewHolder(@NonNull View itemView, RecyclerAdapter adapter, Context context, int id) {
        if (id == TASK_ONE) {
            return new MultipleViewHolder().new TaskOneViewHolder(itemView, adapter, context);
        } else if (id == TASK_TWO) {
            return new MultipleViewHolder().new TaskTwoViewHolder(itemView, adapter, context);
        } else if (id == TASK_THREE) {
            return new MultipleViewHolder().new TaskThreeViewHolder(itemView, adapter, context);
        } else if (id == TASK_FOUR) {
            return new MultipleViewHolder().new TaskFourViewHolder(itemView, adapter, context);
        } else {
            return new MultipleViewHolder().new TaskFiveViewHolder(itemView, adapter, context);
        }
    }

    // return the layout match with the task
    public static int getLayoutID(int id) {
        if (id == TASK_ONE) {
            return R.layout.task_one_view_holder;
        } else if (id == TASK_TWO) {
            return R.layout.task_two_view_holder;
        } else if (id == TASK_THREE) {
            return R.layout.task_three_view_holder;
        } else if (id == TASK_FOUR) {
            return R.layout.task_four_view_holder;
        } else if (id == TASK_FIVE) {
            return R.layout.task_five_view_holder;
        } else return 0;
    }
}
