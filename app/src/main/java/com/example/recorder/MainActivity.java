package com.example.recorder;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaDescrambler;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private String fileName;
    private MediaPlayer mediaPlayer;
    private Button recordStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName = Environment.getStorageDirectory() + "/record.3gpp";
        recordStart = findViewById(R.id.start);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    releaseMedia();

                    File outFile = new File(fileName);
                    if(outFile.exists())
                        outFile.delete();

                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setOutputFile(fileName);
                    mediaRecorder.prepare();
                    mediaRecorder.start();

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Record Now!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

        // присвоим обработчик кнопке OK (btnOk)
        recordStart.setOnClickListener(oclBtnOk);
    }

    /*public void recordStart(View v){
        try {
            releaseMedia();

            File outFile = new File(fileName);
            if(outFile.exists())
                outFile.delete();

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(fileName);
            mediaRecorder.prepare();
            mediaRecorder.start();

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Record Now!", Toast.LENGTH_SHORT);
            toast.show();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }*/

    public void recordPlay(View v) {
        try {
            releaseMedia();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Record Play!", Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void releaseMedia(){
        if(mediaRecorder != null){
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}