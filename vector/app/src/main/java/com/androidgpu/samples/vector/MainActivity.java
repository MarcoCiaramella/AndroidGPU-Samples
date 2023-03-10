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
            Float[] input = new Float[dim * vecSize];
            Float[] output = new Float[dim * vecSize];

            for (int i = 0; i < input.length; i++) {
                input[i] = (float) i;
            }
            androidGPU.run(this, () -> {
                Log.i("####", Float.toString(output[output.length - 1]));
            }, dim, 1, 1, localSize, 1, 1, output, input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}