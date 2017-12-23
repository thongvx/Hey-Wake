package com.lab.vxt.heywake.services;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.lab.vxt.heywake.models.MultipleChoiceQuestion;

import java.util.ArrayList;

/**
 * Created by Thuy Nguyen on 12/22/2017.
 */

public class MultipleChoiceQuestionService extends FirebaseService  {
    public static MultipleChoiceQuestionService multipleChoiceQuestionService;
    public static MultipleChoiceQuestionService getInstance(){
        if (multipleChoiceQuestionService != null)
            return multipleChoiceQuestionService;
        else {
            multipleChoiceQuestionService = new MultipleChoiceQuestionService();
            return multipleChoiceQuestionService;
        }

    }

    public void getData(final ILoadService iLoadService){
        final ArrayList<Object> result = new ArrayList<>();
        myRef.limitToFirst(com.lab.vxt.heywake.untils.Constants.NUMBER_OF_QUESTION_REQUEST).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dt: dataSnapshot.getChildren()){
                    String question = dt.child(ConstantsService.QUESTION).getValue(String.class);
                    String false1 = dt.child(ConstantsService.FALSE1).getValue(String.class);
                    String false2 = dt.child(ConstantsService.FALSE2).getValue(String.class);
                    String false3 = dt.child(ConstantsService.FALSE3).getValue(String.class);
                    String true4 = dt.child(ConstantsService.TRUE4).getValue(String.class);

                    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(question, false1, false2, false3, true4);
                    result.add(multipleChoiceQuestion);
                }
                iLoadService.onLoadSuccess(result);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                iLoadService.onLoadFail(ConstantsService.LOAD_DATA_FAIL);
            }
        });
    }
}
