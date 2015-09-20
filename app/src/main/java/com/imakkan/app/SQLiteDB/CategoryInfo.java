package com.imakkan.app.SQLiteDB;

/**
 * Created by khaid on 9/18/2015.
 */
public class CategoryInfo
{
    int CategoryId, IsActive ;
    String CategoryName;
    public int getCategoryId()
    {
        return this.CategoryId;
    }
    public void setCategoryId(int CategoryId)
    {
        this.CategoryId = CategoryId;
    }
    public int getIsActive()
    {
        return this.IsActive;
    }
    public void setIsActive(int IsActive)
    {
        this.IsActive = IsActive;
    }
    public String getCategoryName()
    {
        return this.CategoryName;
    }
    public void setCategoryName(String CategoryName)
    {
        this.CategoryName = CategoryName;
    }
}
