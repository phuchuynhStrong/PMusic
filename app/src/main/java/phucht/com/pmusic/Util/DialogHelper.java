package phucht.com.pmusic.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import phucht.com.pmusic.R;

public class DialogHelper {

    public static DialogHelper instance = null;

    public static DialogHelper getInstance() {
        if (instance == null)
            instance = new DialogHelper();
        return instance;
    }

    public DialogHelper() {
    }

    public AlertDialog createDialogYesNo(Context context, String question,
                                         DialogInterface.OnClickListener yesAction,
                                         DialogInterface.OnClickListener noAction) {
        return new AlertDialog.Builder(context).setMessage(question)
                .setPositiveButton(R.string.no, noAction)
                .setNegativeButton(R.string.yes, yesAction)
                .create();
    }

    public AlertDialog createDialogOK(Context context, String message,
                                      DialogInterface.OnClickListener okAction) {
        return new AlertDialog.Builder(context).setMessage(message)
                .setPositiveButton(R.string.ok, okAction)
                .create();
    }
}