package com.example.pc.android_advance_lesson3_le_thanh_dat_oe10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import javax.net.ssl.HandshakeCompletedEvent;

public class MainActivity extends AppCompatActivity {

    private File f[];
    private static final int REQUEST_READ_EXTERNAL = 1;
    private ArrayList<Bitmap> mImageBitmaps;
    private RecyclerView mRecyclerGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File extStore = Environment.getExternalStorageDirectory();
        String path = extStore.getPath() + "/DCIM/Camera/";
        File file = new File(path);
        f = file.listFiles();

        mImageBitmaps = new ArrayList<>();
        mRecyclerGallery = findViewById(R.id.recycler_view);

        checkPermission();
        setDataRecycler();

    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                        REQUEST_READ_EXTERNAL);
            }

            return;
        }
        loadImg();
//        new ImageAsynTask().execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_EXTERNAL) {
            checkPermission();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
public void loadImg(){

    final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what != -1) {
                mImageBitmaps.add(BitmapFactory.decodeFile(f[msg.what].getPath()));
            }

            return true;
        }
    });
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            if (f != null) {
                for (int i = 0; i < f.length; i++) {
                    handler.sendEmptyMessage(i);
                }
            }
        }
    });
    thread.start();
}

//    public class ImageAsynTask extends AsyncTask<String, String, Void> {
//        @Override
//        protected Void doInBackground(String... voids) {
//            File extStore = Environment.getExternalStorageDirectory();
//            String path = extStore.getPath() + "/DCIM/Camera/";
//            File file = new File(path);
//            File f[] = file.listFiles();
//            if (f.length == 0) {
//                return null;
//            }
//
//            for (File aF : f) {
//                mImageBitmaps.add(BitmapFactory.decodeFile(aF.getPath()));
//            }
//            setDataRecycler();
//            return null;
//        }
//    }

    private void setDataRecycler() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mImageBitmaps);
        mRecyclerGallery.setAdapter(recyclerAdapter);
        mRecyclerGallery.setLayoutManager(new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL));
        recyclerAdapter.notifyDataSetChanged();
    }
}
