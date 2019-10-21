package com.example.androidroom.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidroom.database.PegawaiRepository;
import com.example.androidroom.entity.Pegawai;

import java.util.List;

public class PegawaiViewModel extends AndroidViewModel {

    private PegawaiRepository mRepository;

    private LiveData<List<Pegawai>> mAllPegawai;

    public PegawaiViewModel(Application application) {
        super(application);
        mRepository = new PegawaiRepository(application);
        mAllPegawai = mRepository.getmAllPegawai();
    }

    public LiveData<List<Pegawai>> getmAllPegawai() { return mAllPegawai; }

    public void insert(Pegawai pegawai) { mRepository.insert(pegawai); }
    public void update(Pegawai pegawai) { mRepository.update(pegawai); }
    public void delete(Pegawai pegawai) { mRepository.delete(pegawai); }
}