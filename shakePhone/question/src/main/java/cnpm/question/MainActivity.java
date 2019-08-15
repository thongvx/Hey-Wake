package cnpm.question;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    protected final int TIME = 30000;
    private ProgressBar progressBar;
    private boolean isActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(TIME);
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                isActive = true;
                int timePass = 0;
                while (isActive && timePass < TIME){
                    try {
                        sleep(100);
                        timePass += 100;
                        progressBar.setProgress(timePass);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
    }
}
