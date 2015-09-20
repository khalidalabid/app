package com.imakkan.app.SQLiteDB;

/**
 * Created by khaid on 9/18/2015.
 */
public class GroupInfo
{
    int GroupId, IsPublic ;
    String GroupName;
    public int getGroupId()
    {
        return this.GroupId;
    }
    public void setGroupId(int GroupId)
    {
        this.GroupId = GroupId;
    }
    public int getIsPublic()
    {
        return this.IsPublic;
    }
    public void setIsPublic(int IsActive)
    {
        this.IsPublic = IsPublic;
    }
    public String getGroupName()
    {
        return this.GroupName;
    }
    public void setGroupName(String GroupName)
    {
        this.GroupName = GroupName;
    }
}
