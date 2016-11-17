package com.example.leidong.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by leidong on 2016/11/16.
 */

public class AIDLService extends Service {
    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public String hello(String name) throws RemoteException {
                // TODO Auto-generated method stub
                return "hello"+name;
            }
        };
    }
}
