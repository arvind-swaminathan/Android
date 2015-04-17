package patrol.npd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SecondService extends Service {

    int nextStartMode; // indicates how to behave if service is killed
    IBinder theBinder; // interface for client that bind
    boolean justAlloweRebind; // indicates whether onRebind should be used

    public SecondService() {
    }
    @Override
    public void onCreate(){

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        return nextStartMode;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return theBinder;
    }
    @Override
    public boolean onUnbind(Intent intent){
        return justAlloweRebind;
    }
    @Override
    public void onRebind(Intent intent){

    }
    public void onDestroy(){

    }
}
