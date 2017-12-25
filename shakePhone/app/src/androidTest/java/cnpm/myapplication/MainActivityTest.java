package cnpm.myapplication;

import android.media.MediaPlayer;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by deedee on 24/12/2017.
 */
public class MainActivityTest {
    MediaPlayer mediaPlayer;
    ShakeDetector shakeDetector;

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);
    @Before
    public void createLogHistory(){
        MainActivity activity = rule.getActivity();
        mediaPlayer= MediaPlayer.create(activity,R.raw.song);
        mediaPlayer.start();
        shakeDetector = new ShakeDetector();
        shakeDetector.setmShakeCount(5);
    }
    @Test
    public void ensureListViewIsPresent() throws Exception {


        //Kiem tra  sau 5 lan lac
        boolean result=  RingManager.managerRing(mediaPlayer,shakeDetector.getmShakeCount());
        assertThat("Checking that the rington has been  off",result,
                is(equalTo(false)));



    }
}