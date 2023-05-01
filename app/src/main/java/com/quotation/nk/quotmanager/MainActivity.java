package com.quotation.nk.quotmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class MainActivity extends AppCompatActivity {


    EditText edit_user_id, editPassword;
    String username, pwd, url;
    CheckBox passshow;
    Button submit_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_user_id = (EditText) findViewById(R.id.userid);
        editPassword = (EditText) findViewById(R.id.password);
        submit_btn = (Button) findViewById(R.id.btnlogon);
        passshow = (CheckBox) findViewById(R.id.passwordshow);


        passshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    // hide password
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        if (pref.contains("Name") && pref.contains("Pwd")) {
            startActivity(new Intent(MainActivity.this, Welcome_Activity.class));
            finish();

        }

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final Dialog shippingDialog = new Dialog(MainActivity.this);
                shippingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                shippingDialog.setContentView(R.layout.waitscreen);
                shippingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                shippingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                shippingDialog.setCancelable(true);
                shippingDialog.setCanceledOnTouchOutside(false);

                shippingDialog.show();

                username = edit_user_id.getText().toString();
                pwd = editPassword.getText().toString();

                if (username.length() == 0) {
                    Toast.makeText(MainActivity.this, "Please Enter ID", Toast.LENGTH_SHORT).show();
                } else if (pwd.length() == 0) {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else {



                    url = "http://loadcrm.com/quotationdiwali/api/LoginCredentials/1?UserID=" + username + "&Password=" + pwd + "";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {



                            shippingDialog.dismiss();
                            Log.d("Response", response.toString());


                            // Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            response = response.trim();
                            response = response.substring(1, 20);
                            // Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            if (response.equals("Login Successfully.")) {



                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("Name", username);
                                        editor.putString("Pwd", pwd);
                                        editor.commit();
                                        editor.apply();
                                        Intent intent = new Intent(MainActivity.this, Welcome_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 1000);


                                //Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Please Check Details", Toast.LENGTH_SHORT).show();
                            }


                            //  Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                            try {
                                JSONObject jsonobject = new JSONObject(response);

                                if (jsonobject != null) {
                                    String Message = jsonobject.getString("Message");
                                    String Error = jsonobject.getString("Error");
                                    if (Error.equalsIgnoreCase(null) || Error.equalsIgnoreCase("null")) {
                                        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), Error, Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    MainController.getInstance().addToRequestQueue(stringRequest);
                    MainController.getInstance().getRequestQueue().getCache().clear();

                }
            }

        });

    }
}
