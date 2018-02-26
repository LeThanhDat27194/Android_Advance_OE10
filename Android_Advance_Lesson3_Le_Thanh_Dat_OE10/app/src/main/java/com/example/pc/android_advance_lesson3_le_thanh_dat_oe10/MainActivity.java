package com.example.pc.android_advance_lesson3_le_thanh_dat_oe10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_READ_EXTERNAL = 1;
    private ArrayList<Bitmap> mImageBitmaps;
    private RecyclerView mRecyclerGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerGallery = findViewById(R.id.recycler_view);
        checkPermission();
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
        getPathFromExternal();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_EXTERNAL) {
            checkPermission();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getPathFromExternal() {
        mImageBitmaps = new ArrayList<>();
        File extStore = Environment.getExternalStorageDirectory();
//        for (int i = 0; i < extStore.length(); i++) {
//            Log.d("Dulieu", "getPathFromExternal: "+extStore.getAbsoluteFile().getPath());
//        }
        String path = extStore.getPath()+"/DCIM/Camera/" ;
        File file = new File(path);
        File f[] = file.listFiles();
        if (f.length == 0) {
            Toast.makeText(this, "Không có ảnh để hiện",
                    Toast.LENGTH_SHORT).show();
            return;
        }
//        for (int i = 0; i < f.length; i++) {
//            mImageBitmaps.add(BitmapFactory.decodeFile(f[i].getPath()));
//        }

        for (File aF : f) {

//            Log.d("Dulieu", "getPathFromExternal: "+aF.getPath());
            mImageBitmaps.add(BitmapFactory.decodeFile(aF.getPath()));
        }

        setDataRecycler();
    }

    private void setDataRecycler() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mImageBitmaps);
        mRecyclerGallery.setAdapter(recyclerAdapter);
        mRecyclerGallery.setLayoutManager(new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL));
    }
}
