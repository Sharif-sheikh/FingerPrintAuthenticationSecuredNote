package com.sharif111.fingerprintathentication;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Notes extends RealmObject{
    String title;
    String description;
    long createdTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

}
