package com.androidgpu.samples.vector;

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
            AndroidGPU androidGPU = new AndroidGPU(this, "compute.glsl");

            int localSize = 256;
            int dim = 8000;
            int vecSize = 4;
            Double[] input = new Double[dim * vecSize];
            Double[] output = new Double[dim * vecSize];

            for (int i = 0; i < input.length; i++) {
                input[i] = (double) i;
            }
            androidGPU.run(this, () -> {
                for (double d : output) {
                    Log.i("####", Double.toString(d));
                }
            }, dim, 1, 1, localSize, 1, 1, output, input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}