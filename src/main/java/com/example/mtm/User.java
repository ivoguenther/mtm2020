package com.example.mtm;

public class User {
    private String uid = "";
    private String username = "";
    private String gid = "";
    private String groupname = "";

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGroupname() {
        return this.groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public User(String uid, String username, String gid, String groupname) {
        this.uid = uid;
        this.username = username;
        this.gid = gid;
        this.groupname = groupname;
    }
}
