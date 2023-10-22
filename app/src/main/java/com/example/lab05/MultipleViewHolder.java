package com.example.lab05;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab05.DataClass.ObjectTwo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

// Applying Factory Design Pattern to create Multiple ViewHolders
// The outer class MultipleViewHolder is to wrap all the Products instead of each fil for each product
public class MultipleViewHolder {
    // an Abstract Factory class is created instead of interface because the Abstract class can extend
    // Recycler.ViewHolder class which is required for the RecyclerAdapter
    public abstract class AbstractViewHolder extends RecyclerView.ViewHolder {
        RecyclerAdapter adapter;
        Context context;
        public AbstractViewHolder(@NonNull View itemView,RecyclerAdapter adapter, Context context) {
            super(itemView);
            this.adapter = adapter;
            this.context = context;
        }
        // The purpose of Abstract Factory class also lied in this update function
        // All ViewHolder need to bind the data to the ViewHolder. This method will be called
        // from the onBindViewHolder from RecyclerAdapter class. When it's called, the Adapter
        // don't need to specify which class does this methods come from thus reduce the code
        // and make RecyclerAdapter class reusable, scalable and easier to maintain.
        public abstract void update(Object o);
    }
    public class TaskOneViewHolder extends AbstractViewHolder implements View.OnClickListener {
        ImageView icApp,icMore;
        TextView tvAppName,tvAppSize,tvInstalledDate;
        PackageManager pkManager;
        File file;

        public TaskOneViewHolder(@NonNull View itemView, RecyclerAdapter adapter, Context context) {
            super(itemView, adapter, context);
            icApp = itemView.findViewById(R.id.iv_icon);
            icMore = itemView.findViewById(R.id.iv_more);
            tvAppName = itemView.findViewById(R.id.tv_app_name);
            tvAppSize = itemView.findViewById(R.id.tv_app_size);
            tvInstalledDate = itemView.findViewById(R.id.tv_app_installed_date);
            pkManager = context.getPackageManager();
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(adapter.getContext(),AppDetailActivity.class);
//            adapter.getContext().startActivity(intent);
        }
        @Override
        public void update(Object o) {
            file = new File(((ApplicationInfo) o).sourceDir);
            long fileLengthKb = file.length() / 1024;
            long fileLengthMb = fileLengthKb / 1024;
            Date date = new Date(file.lastModified());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            tvAppName.setText(pkManager.getApplicationLabel((ApplicationInfo) o));
            tvAppSize.setText((fileLengthMb >= 1) ? Double.toString(fileLengthMb)+" MB": Double.toString(fileLengthKb)+" KB");
            tvInstalledDate.setText(format.format(date).toString());
            icApp.setImageDrawable(pkManager.getApplicationIcon((ApplicationInfo) o));
        }
    }

    public class TaskTwoViewHolder extends AbstractViewHolder {

        TextView tvMonitor;
        public TaskTwoViewHolder(@NonNull View itemView,RecyclerAdapter adapter,Context context) {
            super(itemView,adapter,context);
            tvMonitor = itemView.findViewById(R.id.tv_monitor);
            tvMonitor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ObjectTwo myObject = (ObjectTwo) adapter.getDataAtPosition(getAdapterPosition());
                    if (!myObject.isState()){
                        myObject.setState(true);
                        tvMonitor.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_monitor_on,0,0);
                    }
                    else {
                        myObject.setState(false);
                        tvMonitor.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_monitor_off,0,0);
                    }
                }
            });
        }
        @Override
        public void update(Object o) {
            ObjectTwo myObject = (ObjectTwo) o;
            tvMonitor.setText(myObject.getName());
            if (myObject.isState()){
                tvMonitor.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_monitor_on,0,0);
            }
            else {
                tvMonitor.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_monitor_off,0,0);
            }
        }
    }

    public class TaskThreeViewHolder extends AbstractViewHolder {

        TextView tvStudent;
        Button btnRemove;
        public TaskThreeViewHolder(@NonNull View itemView, RecyclerAdapter adapter, Context context) {
            super(itemView,adapter,context);
            this.adapter = adapter;
            this.context = context;
            tvStudent = itemView.findViewById(R.id.tv_student_name);
            btnRemove = itemView.findViewById(R.id.btn_remove);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    adapter.removeDataAtPosition(position);
                    adapter.notifyItemRemoved(position);
                }
            });
        }
        @Override
        public void update(Object o) {
            tvStudent.setText((String) o);
        }
    }

    public class TaskFourViewHolder extends AbstractViewHolder {
        TextView tvUser,tvEmail;
        public TaskFourViewHolder(View itemView, RecyclerAdapter adapter, Context context) {
            super(itemView,adapter,context);
            tvUser = itemView.findViewById(R.id.tv_user);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }
        @Override
        public void update(Object o) {
            tvUser.setText((String) o);
            tvEmail.setText(((String) o).trim()+"@gmail.com");
        }
    }

    public class TaskFiveViewHolder extends AbstractViewHolder {

        TextView tvItem;
        CheckBox cbItem;

        public TaskFiveViewHolder(View itemView, RecyclerAdapter adapter, Context context) {
            super(itemView,adapter,context);;
            tvItem = itemView.findViewById(R.id.tv_item);
            cbItem = itemView.findViewById(R.id.cb_item);
            cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    ((ObjectTwo) adapter.getDataAtPosition(getAdapterPosition())).setState(b);
                }
            });
        }

        public void update(Object o) {
            ObjectTwo myObject = (ObjectTwo) o;
            tvItem.setText(myObject.getName());
            cbItem.setChecked(myObject.isState());
        }
    }
}
