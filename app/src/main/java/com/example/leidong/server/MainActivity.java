package com.example.leidong.server;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.leidong.aidltest.R;

public class MainActivity extends AppCompatActivity {
    IMyAidlInterface RemoteService; //监听服务
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.i("mConnection", service+"");
            RemoteService = IMyAidlInterface.Stub.asInterface(service);

            try {
                String s= RemoteService.hello("finch");
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();
    }
    //连接服务
    private void initService() {
        Intent i = new Intent( );
        i.setAction("android.intent.action.AIDLService");
        boolean ret = bindService(i, mConnection, Context.BIND_AUTO_CREATE);
    }

    //断开服务
    private void releaseService() {
        unbindService(mConnection);
        mConnection = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }
}
