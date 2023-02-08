package com.example.travel_guide_app_1181390_1182126;

import android.app.Activity;
import android.os.AsyncTask;
import java.util.List;
public class ConnectionAsyncTask extends AsyncTask<String, String,
        String> {
    Activity activity;
    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {
        ((ConnectActivity) activity).setButtonText("connecting");
        super.onPreExecute();
       ((ConnectActivity) activity).setProgress(true);
    }
    @Override
    protected String doInBackground(String... params) {
        String data = HttpManager.getData(params[0]);
        return data;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
       ((ConnectActivity) activity).setProgress(false);
       ((ConnectActivity) activity).setButtonText("connected");
        List<Destination> destinations = DestinationJasonParser.getObjectFromJason(s);
        ((ConnectActivity) activity).fillDestination(destinations);
    }
}
