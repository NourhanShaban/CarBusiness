package com.example.norhan.firebasetry.sync;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.norhan.firebasetry.MainActivity;
import com.example.norhan.firebasetry.MainActivity2;
import com.example.norhan.firebasetry.SellUtils;
import com.example.norhan.firebasetry.utils.NotificationUtils;

/**
 * Created by Speed on 14/04/2018.
 */

public class HelpTasks {
    public static final String ACTION_ACCEPT_SELL_REQUEST = "accept-sell-request";

    public static final String ACTION_DISMISS_SELL_REQUEST = "dismiss-sell-request";

    private static final String TAG = "HelpTasks";

    public static void executeTask(Context context, String action) {
        if (ACTION_ACCEPT_SELL_REQUEST.equals(action)) {
            Log.d(TAG, "ACCEPT SELL "+action);

            SellUtils.getInstance(context).sendHelpTo(NotificationUtils.toDriverID,NotificationUtils.toCarID);

        } else if (ACTION_DISMISS_SELL_REQUEST.equals(action)) {
            Log.d(TAG, "executeTask: action "+action);
        }

    }


}
