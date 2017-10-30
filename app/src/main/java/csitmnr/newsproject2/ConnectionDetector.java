package csitmnr.newsproject2;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Manoj Budha Ayer on 10/27/2017.
 */

public class ConnectionDetector {


    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);

        if (connectivityManager != null){
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null){

                if (info.getState() == NetworkInfo.State.CONNECTED);
                return true;
            }
        }
        return false;
    }

}
