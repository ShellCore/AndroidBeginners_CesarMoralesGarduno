package com.shellcore.android.flashstudy.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.shellcore.android.flashstudy.R;
import com.shellcore.android.flashstudy.data.dao.QuestionDao;
import com.shellcore.android.flashstudy.model.Question;

/**
 * Created by Cesar on 21/06/2017.
 */

public class AddQuestionDialogFragment extends DialogFragment {

    private ToggleAddQuestionListener listener;

    public static AddQuestionDialogFragment getInstance(String idTask) {
        Bundle bundle = new Bundle();
        bundle.putString("ID_TASK", idTask);
        AddQuestionDialogFragment fragment = new AddQuestionDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_add_question, null, false);
        final EditText edtQuestion = (EditText) v.findViewById(R.id.edit_question);
        final EditText edtAnswer = (EditText) v.findViewById(R.id.edit_answer);

        builder.setView(v);
        builder.setTitle(R.string.dialog_title);
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveQuestion(edtQuestion.getText().toString(), edtAnswer.getText().toString());
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    private void saveQuestion(String quest, String answer) {
        Question question =  new Question(quest, answer);
        int id = QuestionDao.insertQuestion(getContext(), question);
        if (id != 0) {
            listener.handleResult();
        }
    }

    public void setListener(ToggleAddQuestionListener listener) {
        this.listener = listener;
    }

    public interface ToggleAddQuestionListener {
        void handleResult();
    }
}
