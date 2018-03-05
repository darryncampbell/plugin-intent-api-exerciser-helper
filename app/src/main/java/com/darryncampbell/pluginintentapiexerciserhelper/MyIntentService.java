package com.darryncampbell.pluginintentapiexerciserhelper;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class MyIntentService extends IntentService {

    Handler mHandler;

    public MyIntentService() {
        super("MyIntentService");
        mHandler = new Handler();
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            String randomNumber = "";
            if (intent.hasExtra("random.number"))
                randomNumber = intent.getStringExtra("random.number");
            String message = "StartService received: " + randomNumber + " (" + action + ")";
            Log.i("IntentShimHelper", message);
            mHandler.post(new DisplayToast(this, message));
        }
    }

}
