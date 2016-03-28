package ro.pub.cs.systems.eim.practicaltest01var01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartService extends Service {
    public StartService() {
    }

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("lala", "ma rulez in service");

        int firstNumber = intent.getIntExtra("sum", -1);
        processingThread = new ProcessingThread(this, firstNumber);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    public void onDestroy() {
        processingThread.stopThread();
    }
}
