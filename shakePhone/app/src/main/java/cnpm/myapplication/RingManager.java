package cnpm.myapplication;

import android.media.MediaPlayer;

/**
 * Created by Admin-PC on 12/24/2017.
 */

public class RingManager {
    public static boolean managerRing(MediaPlayer m, int count){
        if (count == 5 ){
            m.stop();
        }
        if(!m.isPlaying()){
            return false;
        }
        return true;

    }
}
