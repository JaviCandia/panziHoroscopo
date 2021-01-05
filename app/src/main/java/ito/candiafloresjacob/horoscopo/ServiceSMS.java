package ito.candiafloresjacob.horoscopo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class ServiceSMS extends Service{

    private SMSReceiver smsReceiver = new SMSReceiver();

    @Override
    public void onCreate(){
        //
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int idProcess){
        smsReceiver.onReceive(this, intent);
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        //
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
