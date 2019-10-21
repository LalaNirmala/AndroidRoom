package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidroom.R;

public class PegawaiBaruActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_PEGAWAI_ID = "com.example.androidroom.EXTRA_ID";
    public static final String EXTRA_REPLY_PEGAWAI_NAME = "com.example.androidroom.NAME";
    public static final String EXTRA_REPLY_PEGAWAI_NUMBER = "com.example.androidroom.NIM";
    public static final String EXTRA_REPLY_PEGAWAI_PHONE = "com.example.androidroom.PHONE";

    private EditText mEditNamaPegawaiView, mEditNipView, mEditPegawaiPhoneView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegawai_baru);

        mEditNamaPegawaiView = findViewById(R.id.editnama);
        mEditNipView = findViewById(R.id.editnip);
        mEditPegawaiPhoneView = findViewById(R.id.editphone);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REPLY_PEGAWAI_ID)) {
            setTitle("Edit Pegawai");
            mEditNamaPegawaiView.setText(intent.getStringExtra(EXTRA_REPLY_PEGAWAI_NAME));
            mEditNipView.setText(intent.getStringExtra(EXTRA_REPLY_PEGAWAI_NUMBER));
            mEditPegawaiPhoneView.setText(intent.getStringExtra(EXTRA_REPLY_PEGAWAI_PHONE));

        } else {
            setTitle("Add Pegawai");
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNamaPegawaiView.getText()) || TextUtils.isEmpty(mEditNipView.getText()) || TextUtils.isEmpty(mEditPegawaiPhoneView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String namaPegawai = mEditNamaPegawaiView.getText().toString();
                    String nip = mEditNipView.getText().toString();
                    String pegawaiPhone = mEditPegawaiPhoneView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_PEGAWAI_NAME, namaPegawai);
                    replyIntent.putExtra(EXTRA_REPLY_PEGAWAI_NUMBER, nip);
                    replyIntent.putExtra(EXTRA_REPLY_PEGAWAI_PHONE, pegawaiPhone);

                    int id = getIntent().getIntExtra(EXTRA_REPLY_PEGAWAI_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(EXTRA_REPLY_PEGAWAI_ID, id);
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
