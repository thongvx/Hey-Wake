package com.lab.vxt.heywake.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lab.vxt.heywake.R;
import com.lab.vxt.heywake.services.ILoadService;
import com.lab.vxt.heywake.services.QuoteService;
import com.lab.vxt.heywake.untils.InternetDetector;

public class QuoteActivity extends AppCompatActivity {
    private TextView textViewQuote;
    private String quote = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        textViewQuote = (TextView) findViewById(R.id.textViewQuote);

        InternetDetector internetDetector = new InternetDetector(QuoteActivity.this);
        if(internetDetector.isConnected()){
            final QuoteService quoteService = QuoteService.getInstance();
            quoteService.getData(new ILoadService() {
                @Override
                public void onLoadSuccess(Object object) {
                    quote = (String) object;
                    textViewQuote.setText("\""+ quote+"\"");
                }

                @Override
                public void onLoadFail(Object object) {
                    quote = getQuoteRandom();
                    textViewQuote.setText("\""+ quote+"\"");
                }
            });
        }
        else{
            quote = getQuoteRandom();
            textViewQuote.setText("\""+ quote+"\"");
        }






    }

    private String getQuoteRandom(){
        String[] quotes = getResources().getStringArray(R.array.quotes);
        int random = (int) ((Math.random()) * (quotes.length - 1));
        return quotes[random];
    }

}
