package com.quotation.nk.quotmanager.Dailog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.quotation.nk.quotmanager.R;
import com.quotation.nk.quotmanager.SP;

/**
 * Created by Nk on 10/28/2018.
 */

public class CommonDialog {


    SP sp;
    Context context;
    public CommonDialog(Context context)
    {
        this.context=context;
    }



    public void showDialogExit()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Guideo");
        builder.setMessage("Do you want to exit.");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sp = SP.getInstance();
                        sp.setUsername("",context);
                        ((AppCompatActivity) context).finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ((AppCompatActivity) context).startActivity(intent);
                        android.os.Process.killProcess(android.os.Process.myPid());

                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}


