package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class RepechageUIUtils {
  public static void showErrorDialog(Context context, int errorMessage) {
    showErrorDialog(context, context.getString(errorMessage));
  }

  public static void showErrorDialog(Context context, String errorMessage) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(errorMessage).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    builder.show();
  }

  public static void showDialogWithCallback(Context context, String message, final DialogCallBack dialogCallBack) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        dialogCallBack.onSuccess();
      }
    });
    builder.show();
  }
}
