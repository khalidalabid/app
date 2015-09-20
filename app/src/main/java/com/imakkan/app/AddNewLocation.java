package com.imakkan.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.imakkan.app.SQLiteDB.CategoryInfo;
import com.imakkan.app.SQLiteDB.DBAdapter;
import com.imakkan.app.SQLiteDB.LocationInfo;
import com.imakkan.app.SQLiteDB.UserInfo;

import java.util.List;

/**
 * Created by khaid on 9/9/2015.
 */
public class AddNewLocation extends Activity
{

    private DBAdapter ImakanDB;
    private Spinner mSpinCatGroup;
    private SQLiteDatabase db;
    private Cursor CatCur;
    private String CurrentUser;
    private int SelectedLocCat=0;
    public static final int REQUEST_CAPTURE =1;
    ImageView LocPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewlocation);
        ImakanDB = new DBAdapter(this);
        mSpinCatGroup = (Spinner) findViewById(R.id.spinLocCategory);
        //mSpinGroup.setOnItemSelectedListener(this);
        CatCur = ImakanDB.getAllRows("Categories");
        CurrentUser = getIntent().getStringExtra("CurrentUserName");
        TextView tvCUser = (TextView) findViewById(R.id.tvUserName);
        tvCUser.setText(tvCUser.getText() + CurrentUser);
        loadLocationCategoryList();
        Button click = (Button) findViewById(R.id.btCaptureImg);
        LocPhoto = (ImageView) findViewById(R.id.TVLocImage);
        if (!hasCamera()) {
            click.setEnabled(false);
        }
        Button btShowLocation;
        GPSLocation gpsLocation;
        gpsLocation = new GPSLocation(AddNewLocation.this);
        if (gpsLocation.canGetLocation())
        {
            double latitude = gpsLocation.getLatitude();
            double longitude= gpsLocation.getLongtitude();
            TextView tvloclat = (TextView) findViewById(R.id.tvLocLat);
            tvloclat.setText(Double.toString(latitude));
            TextView tvloclong = (TextView) findViewById(R.id.tvLocLng);
            tvloclong.setText(Double.toString(longitude));
        }
        else
        {
            gpsLocation.showSettingsAlert();
        }
    }

    public boolean hasCamera()
    {
                return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void luanchCamera(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    if (requestCode==REQUEST_CAPTURE && resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            LocPhoto.setImageBitmap(photo);
        }
    }
    public void GoAddNewLoc(View v)
    {

    if (v.getId() == R.id.btAddNewLoc)
        {
            LocationInfo locInfo = new LocationInfo();
            EditText edlocname     = (EditText)findViewById(R.id.TELocname);
            locInfo.setLocName(edlocname.getText().toString());
            EditText edloctel      = (EditText)findViewById(R.id.TEloctel);
            locInfo.setLocTel(edloctel.getText().toString());
            TextView edloclat     = (TextView)findViewById(R.id.tvLocLat);
            locInfo.setLocLat(edloclat.getText().toString());
            TextView edloclng     = (TextView)findViewById(R.id.tvLocLng);
            locInfo.setLocLong(edloclng.getText().toString());
            Spinner edloccat     = (Spinner)findViewById(R.id.spinLocCategory);
            locInfo.setLocCatId(edloccat.getSelectedItemPosition());
            locInfo.setUserId(CurrentUser);
            if (ImakanDB.insertRow(null, locInfo, null)>0)
            {
                Toast AdditionSuccess = Toast.makeText(AddNewLocation.this,"تم إضافة الموقع بنجاح",Toast.LENGTH_SHORT);
                AdditionSuccess.show();
            }
        }
    }
    private void loadLocationCategoryList()
    {
        ArrayAdapter<String> spinnerAdapter;
        DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
        List<String> CatGroupList = dbAdapter.getallLocationCategories();
        spinnerAdapter = new ArrayAdapter<String>(AddNewLocation.this,android.R.layout.simple_spinner_item, CatGroupList);
        mSpinCatGroup.setAdapter(spinnerAdapter);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
    {
        if (parentView== findViewById(R.id.spinLocCategory))
        {
            CategoryInfo selected = (CategoryInfo) parentView.getItemAtPosition(position);

        }
    }
}
