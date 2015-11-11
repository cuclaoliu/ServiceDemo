package edu.cuc.stephen.servicedemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Intent intentStatic, intentBind;
    private BindService service;
    private ServiceConnection conn = new ServiceConnection() {
        @Override                   //当启动源与成功连接后会调用
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = ((BindService.MyBinder)iBinder).getService();
        }

        @Override                   //当启动源与Service的连接意外丢失时调用；如service崩溃或被强行杀死
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        if(intentBind!=null) {
            stopService(intentBind);
            unbindService(conn);
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick(View view) {
        switch(view.getId()){
            case R.id.button_start_service:
                intentStatic = new Intent(MainActivity.this, StartService.class);
                startService(intentStatic);
                break;
            case R.id.button_stop_service:
                stopService(intentStatic);
                break;
            case R.id.button_bind_service:
                intentBind = new Intent(MainActivity.this, BindService.class);
                bindService(intentBind, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.button_play:
                service.play();
                if (service.playing){
                    ((Button)findViewById(R.id.button_play)).setText("||");
                }else{
                    ((Button)findViewById(R.id.button_play)).setText("▷");
                }
                break;
            case R.id.button_stop:
                service.stop();
                break;
            case R.id.button_previous:
                service.previous();
                break;
            case R.id.button_next:
                service.next();
                break;
            case R.id.button_unbind_service:
                unbindService(conn);
                break;
            default:
                break;
        }
    }
}
