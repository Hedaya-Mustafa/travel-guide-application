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
        super.onPreExecute();

    }
    @Override
    protected String doInBackground(String... params) {
        String data = HttpManager.getData(params[0]);
        return data;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s!=null){
            List<Destination> destinations = DestinationJasonParser.getObjectFromJson(s);
            ((MainActivity) activity).store_Destination(destinations);
        } else {

        }

    }
}
