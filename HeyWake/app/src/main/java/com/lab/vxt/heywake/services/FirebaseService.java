package com.lab.vxt.heywake.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Thuy Nguyen on 12/22/2017.
 */

public class FirebaseService {
    public static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final DatabaseReference myRef = database.getReference(ConstantsService.REFERENCE);


}
