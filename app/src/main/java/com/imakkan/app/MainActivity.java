package com.imakkan.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.imakkan.app.SQLiteDB.DBAdapter;

public class MainActivity extends Activity {

    //DatabaseHelper dbHelper = new DatabaseHelper(this);
    DBAdapter ImakanDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImakanDB =  new DBAdapter(this);
        //Capture UserName if any
        String CurrentUserName  = getIntent().getStringExtra("CurrentUserName");
        String CurrentUserPass  = getIntent().getStringExtra("CurrentUserPass");
        TextView tvCUser    = (TextView)findViewById(R.id.TFUserName);
        tvCUser.setText(CurrentUserName);
        // Capture User Pass if any
        TextView tvCPass    = (TextView)findViewById(R.id.TFUserPassword);
        tvCPass.setText(CurrentUserPass);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onRegisterClick(View v){
        if (v.getId() == R.id.btRegister)
        {
            Intent i = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(i);
        }
    }
   public void OnLoginBtClick(View v)
   {
        if (v.getId() == R.id.btLogin)
        {
            EditText tfUName    = (EditText)findViewById(R.id.TFUserName);
            String strUname     = tfUName.getText().toString();

            EditText tfupass    = (EditText)findViewById(R.id.TFUserPassword);
            String strUPass     = tfupass.getText().toString();
            String aUserPass    = ImakanDB.UserLookup(strUname);
            if (strUPass.equals(aUserPass))
            {
                Intent i         = new Intent(MainActivity.this,AddNewLocation.class);
                i.putExtra("CurrentUserName",strUname);
                i.putExtra("CurrentUserPass",strUPass);
                startActivity(i);
            }
            else
            {
                Toast NEPasswords = Toast.makeText(MainActivity.this,"إسم المعرف أو كلمة المرور غير صحيحة، الرجاء المحاولة مرة أخرى.",Toast.LENGTH_SHORT);
                NEPasswords.show();
            }
        }
    }
}
