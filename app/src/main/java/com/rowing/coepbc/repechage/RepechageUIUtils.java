package com.rowing.coepbc.repechage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class RepechageUIUtils {
  public static void showErrorDialog(Context context, int errorMessage) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(errorMessage).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    builder.show();
  }
}
