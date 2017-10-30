package com.example.khowoatt.homebakery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khowoatt.homebakery.Database.MemberTable;
import com.example.khowoatt.homebakery.Database.MenuTable;
import com.example.khowoatt.homebakery.Database.OrderTable;
import com.example.khowoatt.homebakery.Database.OrderlistTable;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    private MemberTable objMemberTable;
    private MenuTable objMenuTable;
    private OrderTable objOrderTable;
    private OrderlistTable objOrderListTable;
    private EditText user,pass,confirmPass,email,phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Cdatabase();
    }
    private void Cdatabase() {

        objMemberTable = new MemberTable(this);
        objMenuTable = new MenuTable(this);
        objOrderTable = new OrderTable(this);
        objOrderListTable = new OrderlistTable(this);
    }

    public void btnregis(View view) {
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        confirmPass = (EditText) findViewById(R.id.confirmpass);

        final String strUSERR = user.getText().toString().trim();
        final String strPASSR = pass.getText().toString().trim();
        final String strCONFIRM = confirmPass.getText().toString().trim();
        final String strEMAILR = email.getText().toString().trim();
        final String strPHONER = phone.getText().toString().trim();


        try{
            if (strUSERR.equals("") || strPASSR.equals("") || strEMAILR.equals("") || strPHONER.equals("") || strCONFIRM.equals("")) {
                errorDialog("มีช่องว่าง", "กรุณากรอกให้ครบ ทุกช่อง");
            } else {

                if (strPASSR.equals(strCONFIRM)) {

                    String[] check = objMemberTable.searchUSERPASSWORD(strUSERR);
                    if (strUSERR.equals(check[1])) {
                        Toast.makeText(Register.this, "ชื่อผู้ใช้นี้มีอยู่แล้วในระบบ", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (strPASSR != strCONFIRM) {
                    confirmPass.setError("รหัสผ่านไม่ตรงกัน");
                }
            }
        }catch(Exception e){
            Regissuccess(strUSERR, strPASSR, strEMAILR, strPHONER);
        }
    }

    private void Regissuccess(final String strname,final String strPASSR,final String strEMAILR,final String strPHONER) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.welcome);
        objBuilder.setTitle("HomeBakery");
        objBuilder.setMessage("Register Success");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {
                    //Setup New Policy
                    StrictMode.ThreadPolicy myPolicy = new StrictMode
                            .ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(myPolicy);
                    ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                    objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
                    objNameValuePairs.add(new BasicNameValuePair("user", strname));
                    objNameValuePairs.add(new BasicNameValuePair("pass", strPASSR));
                    objNameValuePairs.add(new BasicNameValuePair("email", strEMAILR));
                    objNameValuePairs.add(new BasicNameValuePair("phone", strPHONER));
                    objNameValuePairs.add(new BasicNameValuePair("type", "user"));
                    HttpClient objHttpClient = new DefaultHttpClient();
                    HttpPost objHttpPost = new HttpPost("http://5711020660011.sci.dusit.ac.th/addDataMember.php");
                    objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                    objHttpClient.execute(objHttpPost);
                }    catch (Exception e) {
                        Toast.makeText(Register.this, "Cannot Regis", Toast.LENGTH_SHORT).show();
                    }   //try
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        objBuilder.show();

    }

    private void errorDialog(String strTitle,String strMessage) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.usererror);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();
    }

    public void onClickBack(View view) {
        finish();
    }

}
