package com.example.androidroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.entity.Pegawai;
import com.example.androidroom.viewmodel.PegawaiViewModel;

import java.util.List;

public class PegawaiListAdapter extends RecyclerView.Adapter<PegawaiListAdapter.PegawaiViewHolder> {
private PegawaiViewModel pegawaiViewModel;

    class PegawaiViewHolder extends RecyclerView.ViewHolder {
        private final TextView namaPegawaiView, nipView, pegawaiPhoneView;
        private final Button editButton, deleteButton;
        private PegawaiViewHolder(final View itemView) {
            super(itemView);
            namaPegawaiView = itemView.findViewById(R.id.textView_nama_pegawai);
            nipView = itemView.findViewById(R.id.textView_nip);
            pegawaiPhoneView = itemView.findViewById(R.id.textView_pegawai_phone);

            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);

        }
    }

    private final LayoutInflater mInflater;
    private List<Pegawai> mPegawais; // Cached copy of words

    public PegawaiListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PegawaiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PegawaiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PegawaiViewHolder holder, int position) {
        if (mPegawais != null) {
            Pegawai current = mPegawais.get(position);
            holder.nipView.setText("NIM      : "+current.getNip());
            holder.namaPegawaiView.setText("Name   : "+current.getNamaPegawai());
            holder.pegawaiPhoneView.setText("Phone  : "+current.getPegawaiPhone());


        } else {
            // Covers the case of data not being ready yet.
            holder.namaPegawaiView.setText("No Pegawai");
        }

    }

    public void setmPegawais(List<Pegawai> pegawais){
        mPegawais = pegawais;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPegawais != null)
            return mPegawais.size();
        else return 0;
    }

    public Pegawai getPegawaiAt(int position) {
        return mPegawais.get(position);
    }
}
