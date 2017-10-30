package com.example.khowoatt.homebakery;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khowoatt.homebakery.Database.MemberTable;
import com.example.khowoatt.homebakery.Database.MenuTable;
import com.example.khowoatt.homebakery.Database.OrderTable;
import com.example.khowoatt.homebakery.Database.OrderlistTable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {
    private MemberTable objMemberTable;
    private MenuTable objMenuTable;
    private OrderTable objOrderTable;
    private OrderlistTable objOrderListTable;
    private EditText userEditText,passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Cdatabase();
        deleteAllData();
        synJSONtoSQLite();
    }
    private void deleteAllData() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("Homebakery.db",MODE_APPEND,null);
        objSqLiteDatabase.delete("membertable",null,null);
        objSqLiteDatabase.delete("menutable",null,null);
        objSqLiteDatabase.delete("ordertable",null,null);
        objSqLiteDatabase.delete("orderlisttable",null,null);
    }

    private void synJSONtoSQLite() {
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Loop 2 Time
        int intTimes = 0;
        while(intTimes <= 3){

            //Variable and Constant
            InputStream objInputStream = null;
            String strJSON = null;
            String strMemberURL = "http://5711020660011.sci.dusit.ac.th/getAllDataMember.php";
            String strMenuURL = "http://5711020660011.sci.dusit.ac.th/getAllDataMenu.php";
            String strOrderURL = "http://5711020660011.sci.dusit.ac.th/getAllDataOrder.php";
            String strOrderListURL = "http://5711020660011.sci.dusit.ac.th/getAllDataOrderlist.php";
            HttpPost objHttpPost = null;


                    case 2:
                        objHttpPost = new HttpPost(strOrderURL);
                        break;
                    default:
                        objHttpPost = new HttpPost(strOrderListU //1.Create InputStream
                        try{
                            HttpClient objHttpClient = new DefaultHttpClient();
                            switch (intTimes){
                                case 0:
                                    objHttpPost = new HttpPost(strMemberURL);
                                    break;
                                case 1:
                                    objHttpPost = new HttpPost(strMenuURL);
                                    break;RL);
                        break;
                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
            }catch (Exception e){
                Log.d("bakery","InputStream ==> "+e.toString());
            }
            //2.Create strJSON
            try{
                InputStreamReader objInputStreamReader = new InputStreamReader(objInputStream,"UTF-8");
                BufferedReader objBufferedReader = new BufferedReader(objInputStreamReader);
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while((strLine = objBufferedReader.readLine()) != null){
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            }catch (Exception e){
                Log.d("masterUNG","strJSON"+e.toString());
            }
            //3.Update to SQLite
            try{
                JSONArray objJsonArray = new JSONArray(strJSON);
                for(int i =0;i<objJsonArray.length();i++){
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes){
                        case 0:
                            //update MemberTable
                            String stridmember = jsonObject.getString("id_member");
                            String struser = jsonObject.getString("user");
                            String strpass = jsonObject.getString("pass");
                            String stremail = jsonObject.getString("email");
                            String strphone = jsonObject.getString("phone");
                            String strtype = jsonObject.getString("type");
                            objMemberTable.AddNewMember(stridmember,struser,strpass,stremail,strphone,strtype);
                            break;
                        case 1:
                            //update MenuTable
                            String idmenu = jsonObject.getString("id_menu");
                            String name_menu = jsonObject.getString("name_menu");
                            String detail_menu = jsonObject.getString("detail_menu");
                            String price_menu = jsonObject.getString("price_menu");
                            String picture_menu = jsonObject.getString("picture_menu");
                            String type = jsonObject.getString("type");
                            objMenuTable.AddNewMenuTable(idmenu,name_menu,detail_menu,price_menu,picture_menu,type);
                            break;
                        case 2:
                            //update OrderTable
                            String idorder = jsonObject.getString("id_order");
                            String id_member = jsonObject.getString("id_member");
                            String date_order = jsonObject.getString("date_order");
                            String price_order = jsonObject.getString("price_order");
                            String status = jsonObject.getString("status");
                            objOrderTable.AddNewOrderTable(idorder,id_member,date_order,price_order,status);
                            break;

                        default:
                            //update OrderListTable
                            String id_order = jsonObject.getString("id_order");
                            String id_menulist = jsonObject.getString("id_menu");
                            String amount = jsonObject.getString("amount");
                            String total = jsonObject.getString("Price");
                            objOrderListTable.AddNewOrderListTable(id_order,id_menulist,amount,total);
                            break;
                    }
                }
            }catch (Exception e){
                Log.d("mbakery","strJSON"+e.toString());
            }
            //Increase intTimes
            intTimes += 1;
        }
    }

    private void Cdatabase() {

        objMemberTable = new MemberTable(this);
        objMenuTable = new MenuTable(this);
        objOrderTable = new OrderTable(this);
        objOrderListTable = new OrderlistTable(this);
    }

    public void btnLogin(View view){
        userEditText = (EditText) findViewById(R.id.editUser);
        passEditText = (EditText) findViewById(R.id.editPass);

        String struser = userEditText.getText().toString().trim();
        String strpass = passEditText.getText().toString().trim();

        //Check char zero
        if(struser.equals("")||strpass.equals("")){
            Toast.makeText(Login.this,"มีช่องว่าง กรุณากรอกให้ครบทุกช่อง",Toast.LENGTH_SHORT).show();
        } else {
            checkUSERPASSWORD(struser, strpass);
        }
    }

    private void checkUSERPASSWORD(String struser, String strpass) {
        try{
            String[] strMyResult = objMemberTable.searchUSERPASSWORD(struser);
            if(strpass.equals(strMyResult[2])){
                //password True
                welcomDialog(struser);
            }else {
                //password False
                errorDialog("รหัสผ่านไม่ถูกต้อง","กรุณาลองใหม่");
            }

        }catch(Exception e){

            errorDialog("ชื่อผู้ใช้ไม่ถูกต้อง","ไม่มี "+struser+" ในระบบ");
        }
    }

    private void welcomDialog(final String strname) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.welcome);
        objBuilder.setTitle("HomeBakery");
        objBuilder.setMessage("ยินดีต้อนรับคุณ "+strname+" เข้าสู่ระบบ กดปุ่ม OK เพื่อเข้าใช้งาน");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Login.this, MainMenu.class);
                intent.putExtra("name",strname);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        objBuilder.show();
    } //Builder ERROR

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
    } //Builder ERROR

    public void toregis(View view) {
        Intent intent = new Intent(Login.this,Register.class);
        startActivity(intent);
    }
}
