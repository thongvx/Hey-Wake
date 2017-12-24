package com.lab.vxt.heywake.services;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Thuy Nguyen on 12/24/2017.
 */

public class QuoteService extends FirebaseService {
    public static QuoteService quoteService;
    public static QuoteService getInstance(){
        if (quoteService != null)
            return quoteService;
        else {
            quoteService = new QuoteService();
            return quoteService;
        }

    }

    public void getData(final ILoadService iLoadService){
        quoteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int length = (int) dataSnapshot.getChildrenCount();
                int random = (int) ((Math.random()) * (length - 1));
                int count = 0;

                for(DataSnapshot dt: dataSnapshot.getChildren()){
                    if(count == random){
                        String quote = dt.getValue(String.class);
                        iLoadService.onLoadSuccess(quote);
                        break;
                    }
                    count++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                iLoadService.onLoadFail(ConstantsService.LOAD_DATA_FAIL);
            }
        });
    }
}
