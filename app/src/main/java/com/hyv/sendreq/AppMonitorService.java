package com.hyv.sendreq;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class AppMonitorService extends AccessibilityService {

    private static final String TAG = "AppMonitorService";
    private static final String SECRET = "YOUR_SECRET_KEY"; // 替换为实际的 secret

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) return;

        // 检测窗口状态变化事件
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            CharSequence packageName = event.getPackageName();
//            String packageNameString = packageName.toString();
            if (packageName != null) {
                Log.d(TAG, "App in foreground: " + packageName.toString());

                // 调用 /set/<secret>/<status> 接口
                NetworkHelper.set(SECRET, String.valueOf(0),packageName.toString(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Status updated successfully: " + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error updating status: ", error);
                    }
                });
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Service interrupted");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "AppMonitorService connected");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "AppMonitorService unbound");
        return super.onUnbind(intent);
    }
}
