package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lab05.DataClass.ObjectTwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private RecyclerAdapter adapter1,adapter2,adapter3,adapter4,adapter5;
    private LinearLayout lnTaskFour;
    private List<ApplicationInfo> infos;
    private List<ObjectTwo> monitors,items;
    private List<String> students,users;
    private TextView tvTotalUser;
    private Button btnAdd, btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv = findViewById(R.id.rcv);
        btnAdd = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        lnTaskFour = findViewById(R.id.ln_task_four);
        tvTotalUser = findViewById(R.id.tv_total_user);
        initData();

    }

    private void initData() {
        infos = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        students = new ArrayList<>();
        monitors = new ArrayList<>();
        users = new ArrayList<>();
        items = new ArrayList<>();
        for (int i =0;i<51;i++){
            monitors.add(new ObjectTwo("PC "+i,false));
            items.add(new ObjectTwo("Item "+i,false));
            students.add("Student "+i);
            users.add("User "+i);
        }
        tvTotalUser.setText("Total User: "+users.size());
        adapter1 = new RecyclerAdapter(this,infos,RecyclerAdapter.TASK_ONE);
        adapter2 = new RecyclerAdapter(this,monitors,RecyclerAdapter.TASK_TWO);
        adapter3 = new RecyclerAdapter(this, students,RecyclerAdapter.TASK_THREE);
        adapter4 = new RecyclerAdapter(this, users,RecyclerAdapter.TASK_FOUR);
        adapter5 = new RecyclerAdapter(this, items,RecyclerAdapter.TASK_FIVE);
    }

    public void onClick(View view) {
        int taskID = view.getId();
        if (taskID == R.id.btn_add){
            adapter4.addFiveStudent();
            tvTotalUser.setText("Total User: "+users.size());
        }
        else if (taskID == R.id.btn_remove) {
            adapter4.deleteFiveStudent();
            tvTotalUser.setText("Total User: "+users.size());
        }
        else if (taskID == R.id.btn_task1){
            rcv.setAdapter(adapter1);
            rcv.setLayoutManager(new LinearLayoutManager(this));
        }
        else if (taskID == R.id.btn_task2){
            rcv.setAdapter(adapter2);
            rcv.setLayoutManager(new GridLayoutManager(this,3));
        }
        else if (taskID == R.id.btn_task3){
            rcv.setAdapter(adapter3);
            rcv.setLayoutManager(new LinearLayoutManager(this));
        }
        else if (taskID == R.id.btn_task4){
            rcv.setAdapter(adapter4);
            rcv.setLayoutManager(new LinearLayoutManager(this));
        }
        else {
            rcv.setAdapter(adapter5);
            rcv.setLayoutManager(new LinearLayoutManager(this));
        }
        tvTotalUser.setVisibility((rcv.getAdapter() == adapter4) ? View.VISIBLE : View.INVISIBLE);
        lnTaskFour.setVisibility((rcv.getAdapter() == adapter4) ? View.VISIBLE : View.INVISIBLE);
    }
}