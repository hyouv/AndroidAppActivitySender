package com.hyv.sendreq;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class NetworkHelper {

    private static final String BASE_URL = "https://example.com"; // 替换为实际服务器地址
    private static RequestQueue requestQueue;

    public static void initialize(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    /**
     * 获取当前状态 (/query)
     */
    public static void getCurrentStatus(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String endpoint = "/query";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                BASE_URL + endpoint,
                null,
                listener,
                errorListener
        );
        requestQueue.add(request);
    }

    /**
     * 获取可用状态列表 (/get/status_list)
     */
    public static void getStatusList(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        String endpoint = "/get/status_list";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                BASE_URL + endpoint,
                null,
                listener,
                errorListener
        );
        requestQueue.add(request);
    }

    /**
     * 设置状态 (/set?secret=<secret>&status=<status>)
     */
    public static void set(String secret, String status, String AppName, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String endpoint = "/set?secret=" + secret + "&status=" + status + "&app_name=" + AppName;
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                BASE_URL + endpoint,
                null,
                listener,
                errorListener
        );
        requestQueue.add(request);
    }

    /**
     * 设置状态 (/set/<secret>/<status>)
     */
    public static void setStatusWithPath(String secret, String AppPackageName, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String endpoint = "/set/" + secret + "/" + AppPackageName;
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                BASE_URL + endpoint,
                null,
                listener,
                errorListener
        );
        requestQueue.add(request);
    }
}
