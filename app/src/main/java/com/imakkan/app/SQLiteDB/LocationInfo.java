package com.imakkan.app.SQLiteDB;

import android.location.Location;

import java.sql.Blob;

/**
 * Created by khalid on 9/9/2015.
 */
public class LocationInfo {
    int    LocationId,IsSynched,LocCatId;
    String UserId,LocTel,LocName,LocLat,LocLong;
    Blob   LocPicURI;

    public int getLocationId()
    {
        return this.LocationId;
    }
    public void setLocationId(int LocId)
    {
        this.LocationId = LocationId;
    }

    public int getIsSynched()
    {
        return this.IsSynched;
    }
    public void setIsSynched(int IsSynched)
    {
        this.IsSynched = IsSynched;
    }
    public String getUserId()
    {
        return this.UserId;
    }
    public void setUserId(String UserId)
    {
        this.UserId = UserId;
    }
    public String getLocLat()
    {
        return this.LocLat;
    }
    public void setLocLat(String LocLat)
    {
        this.LocLat = LocLat;
    }
    public String getLocLong()
    {
        return this.LocLong;
    }
    public void setLocLong(String LocLong)
    {
        this.LocLong = LocLong;
    }
    public int getLocCatId()
    {
        return this.LocCatId;
    }
    public void setLocCatId(int LocCatId)
    {
        this.LocCatId = LocCatId;
    }
    public String getLocTel()
    {
        return this.LocTel;
    }
    public void setLocTel(String LocTel)
    {
        this.LocTel = LocTel;
    }
    public String getLocName()
    {
        return this.LocName;
    }
    public void setLocName(String LocName)
    {
        this.LocName = LocName;
    }
    public Blob getLocPicURI()
    {
        return this.LocPicURI;
    }
    public void setLocPicURI(Blob LocPicURI)
    {
        this.LocPicURI = LocPicURI;
    }
}