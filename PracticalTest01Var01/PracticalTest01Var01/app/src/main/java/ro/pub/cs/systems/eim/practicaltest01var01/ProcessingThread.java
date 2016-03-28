package ro.pub.cs.systems.eim.practicaltest01var01;

/**
 * Created by root on 3/28/16.
 */
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;


    private int nr;

    public ProcessingThread(Context context, int firstNumber) {
        this.context = context;
        nr = firstNumber;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep();
            break;
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("lala");
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + nr);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}