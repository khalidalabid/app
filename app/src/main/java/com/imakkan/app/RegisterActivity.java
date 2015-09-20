package com.imakkan.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.imakkan.app.SQLiteDB.DBAdapter;
import com.imakkan.app.SQLiteDB.GroupInfo;
import com.imakkan.app.SQLiteDB.UserInfo;

import java.util.List;


/**
 * Created by khaid on 9/8/2015.
 */
public class RegisterActivity extends Activity {

    //DatabaseHelper dbHelper =  new DatabaseHelper(this);
    private DBAdapter ImakanDB;
    private Spinner mSpinGroup;
    private SQLiteDatabase db;
    private Cursor groupCur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ImakanDB =  new DBAdapter(this);
        mSpinGroup = (Spinner) findViewById(R.id.spinLocCategory);
        //mSpinGroup.setOnItemSelectedListener(this);
        groupCur = ImakanDB.getAllRows("Categories");
        loadUserGroupsList();
    }
    public void OnRegisterBtClick(View v)
    {
        if (v.getId()==R.id.btRegisterGo)
        {
            EditText edUserName  = (EditText)findViewById(R.id.TFUserName);
            String   strUname    = edUserName.getText().toString();
            EditText edUserPass1 = (EditText)findViewById(R.id.TFPass1);
            String   strPass1    = edUserPass1.getText().toString();
            EditText edUserPass2 = (EditText)findViewById(R.id.TFPass2);
            String   strPass2    = edUserPass2.getText().toString();
            int   strUGroup      = 1;
            if (!strPass1.equals(strPass2))
            {
                Toast NEPasswords = Toast.makeText(RegisterActivity.this,"كلمة المرور غير متطابقة ، الرجاء المحاولة مرة أخرى.",Toast.LENGTH_SHORT);
                NEPasswords.show();
            }
            else
            {
                //insert the user details into imakkan.db
                UserInfo CurrentUserInfo = new UserInfo();
                CurrentUserInfo.setUserName(strUname);
                CurrentUserInfo.setUserPassword(strPass1);
                CurrentUserInfo.setGroupId(1);
                if (ImakanDB.RegisterNewUser(CurrentUserInfo)=="Existing")
                {
                    Toast ExistingUserMsg = Toast.makeText(RegisterActivity.this,"المعرف المدخل موجود، سيتم إنتقالك إلى صفحة الدخول.",Toast.LENGTH_SHORT);
                    ExistingUserMsg.show();
                    Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                    i.putExtra("CurrentUserName",strUname);
                    i.putExtra("CurrentUserPass", strPass1);
                }
                else if (ImakanDB.RegisterNewUser(CurrentUserInfo)=="New")
                {
                    Toast ExistingUserMsg = Toast.makeText(RegisterActivity.this,"تم تسجيل المعرف بنجاح ، سيتم إنتقالك إلى صفحة الدخول.",Toast.LENGTH_SHORT);
                    ExistingUserMsg.show();
                    Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                    i.putExtra("CurrentUserName",strUname);
                    i.putExtra("CurrentUserPass",strPass1);
                    startActivity(i);
                }
            }
        }
    }

     private void loadUserGroupsList()
     {
        ArrayAdapter<String> spinnerAdapter;
        DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
        List<String> GroupList = dbAdapter.getallUserGroups();
        spinnerAdapter = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_item, GroupList);
        mSpinGroup.setAdapter(spinnerAdapter);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     }

    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
    {
        if (parentView== findViewById(R.id.spinLocCategory))
        {
            GroupInfo selected = (GroupInfo) parentView.getItemAtPosition(position);

        }
    }
}
