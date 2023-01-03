package com.androidgpu.samples.load_asset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lib.androidgpulib.AndroidGPU;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            AndroidGPU androidGPU = new AndroidGPU(getAssets(), "compute.glsl");

            Double[] input = new Double[256];
            Double[] output = new Double[256];

            for (int i = 0; i < input.length; i++) {
                input[i] = (double) i;
            }
            androidGPU.run(256, 1, 1, 256, 1, 1, output, input);
            for (double d : output) {
                Log.i("####", Double.toString(d));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}