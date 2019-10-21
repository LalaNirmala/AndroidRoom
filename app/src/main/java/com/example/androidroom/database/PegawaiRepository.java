package com.example.androidroom.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidroom.dao.PegawaiDAO;
import com.example.androidroom.entity.Pegawai;

import java.util.List;

public class PegawaiRepository {
    private PegawaiDAO pegawaiDAO;
    private LiveData<List<Pegawai>> mAllPegawai;

   public PegawaiRepository(Application application) {
        PegawaiAppDatabase db = PegawaiAppDatabase.getDatabase(application);
        pegawaiDAO = db.pegawaiDAO();
       mAllPegawai = pegawaiDAO.getAllPegawai();
    }

    public LiveData<List<Pegawai>> getmAllPegawai() {
        return mAllPegawai;
    }


    public void insert (Pegawai pegawai) {
        new insertAsyncTask(pegawaiDAO).execute(pegawai);
    }

    public void delete(Pegawai pegawai) {
        new DeletePegawaiAsyncTask(pegawaiDAO).execute(pegawai);
    }

    public void update(Pegawai pegawai) {
        new UpdatePegawaiAsyncTask(pegawaiDAO).execute(pegawai);
    }

    private static class insertAsyncTask extends AsyncTask<Pegawai, Void, Void> {

        private PegawaiDAO mAsyncTaskDao;

        insertAsyncTask(PegawaiDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pegawai... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdatePegawaiAsyncTask extends AsyncTask<Pegawai, Void, Void> {
        private PegawaiDAO pegawaiDAO;

        private UpdatePegawaiAsyncTask(PegawaiDAO pegawaiDAO) {
            this.pegawaiDAO = pegawaiDAO;
        }

        @Override
        protected Void doInBackground(Pegawai... pegawais) {
            pegawaiDAO.update(pegawais[0]);
            return null;
        }
    }

    private static class DeletePegawaiAsyncTask extends AsyncTask<Pegawai, Void, Void> {
        private PegawaiDAO pegawaiDAO;

        private DeletePegawaiAsyncTask(PegawaiDAO pegawaiDAO) {
            this.pegawaiDAO = pegawaiDAO;
        }

        @Override
        protected Void doInBackground(Pegawai... pegawais) {
            pegawaiDAO.delete(pegawais[0]);
            return null;
        }
    }
}
