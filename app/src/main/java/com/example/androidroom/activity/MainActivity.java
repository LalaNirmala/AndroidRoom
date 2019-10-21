package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.adapter.RecyclerItemClickListener;
import com.example.androidroom.adapter.PegawaiListAdapter;
import com.example.androidroom.entity.Pegawai;
import com.example.androidroom.utils.ApplicationStrings;
import com.example.androidroom.viewmodel.PegawaiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private PegawaiViewModel mPegawaiViewModel;
    private Button editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PegawaiListAdapter adapter = new PegawaiListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mPegawaiViewModel = ViewModelProviders.of(this).get(PegawaiViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mPegawaiViewModel.getmAllPegawai().observe(this, new Observer<List<Pegawai>>() {
            @Override
            public void onChanged(@Nullable final List<Pegawai> pegawais) {
                // Update the cached copy of the words in the adapter.
                adapter.setmPegawais(pegawais);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PegawaiBaruActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        editButton = v.findViewById(R.id.button_edit);
                        //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                        deleteButton = v.findViewById(R.id.button_delete);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Delete button is called"+adapter.getPegawaiAt(position).getNamaPegawai() , Toast.LENGTH_SHORT).show();

                                mPegawaiViewModel.delete(adapter.getPegawaiAt(position));
                            }
                        });

                        editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Edit button is called"+adapter.getPegawaiAt(position).getNamaPegawai() , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, PegawaiBaruActivity.class);
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_ID_PEAGAWI, adapter.getPegawaiAt(position).getPegawaiId());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_NAMA_PEGAWAI, adapter.getPegawaiAt(position).getNamaPegawai());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_NIP, adapter.getPegawaiAt(position).getNip());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PEGAWAI_PHONE,  Integer.toString(adapter.getPegawaiAt(position).getPegawaiPhone()));
                                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST_CODE);
                            }
                        });
                    }
                })
        );


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String namaPegawai = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_NAMA_PEGAWAI);
            String nip = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_NIP);
            String pegawaiPhone = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PEGAWAI_PHONE);
            int phone = Integer.parseInt(pegawaiPhone);

            Pegawai pegawai = new Pegawai(namaPegawai,nip,phone);
            mPegawaiViewModel.insert(pegawai);
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(ApplicationStrings.EXTRA_REPLY_ID_PEAGAWI, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String namaPegawai = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_NAMA_PEGAWAI);
            String nip = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_NIP);
            String pegawaiPhone = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PEGAWAI_PHONE);
            int phone = Integer.parseInt(pegawaiPhone);

            Pegawai pegawai = new Pegawai(namaPegawai,nip, phone);
            pegawai.setPegawaiId(id);
            mPegawaiViewModel.update(pegawai);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }



}
