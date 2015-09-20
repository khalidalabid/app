package com.imakkan.app.SQLiteDB;

/**
 * Created by khaid on 9/10/2015.
 */
public class UserInfo {
    int GroupId;
    String UserName,UserPassword;


    public String getUserName()
    {
        return this.UserName;
    }
    public void setUserName(String UserName)
    {
        this.UserName = UserName;
    }
    public String getUserPassword()
    {
        return this.UserPassword;
    }
    public void setUserPassword(String UserPassword)
    {
        this.UserPassword = UserPassword;
    }
    public int getGroupId()
    {
        return this.GroupId;
    }
    public void setGroupId(int GroupId)
    {
        this.GroupId = GroupId;
    }
}
