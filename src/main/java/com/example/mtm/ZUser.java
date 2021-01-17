package com.example.mtm;

public class ZUser {
    String owner;
    String created;
    String passdate;
    String passinterval;
    String lastaccess;
    String authorizations;
    String installdata;

    public ZUser(String owner, String created, String passdate, String passinterval, String lastaccess,
            String authorizations, String installdata) {
        this.owner = owner;
        this.created = created;
        this.passdate = passdate;
        this.passinterval = passinterval;
        this.lastaccess = lastaccess;
        this.authorizations = authorizations;
        this.installdata = installdata;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPassdate() {
        return this.passdate;
    }

    public void setPassdate(String passdate) {
        this.passdate = passdate;
    }

    public String getPassinterval() {
        return this.passinterval;
    }

    public void setPassinterval(String passinterval) {
        this.passinterval = passinterval;
    }

    public String getLastaccess() {
        return this.lastaccess;
    }

    public void setLastaccess(String lastaccess) {
        this.lastaccess = lastaccess;
    }

    public String getAuthorizations() {
        return this.authorizations;
    }

    public void setAuthorizations(String authorizations) {
        this.authorizations = authorizations;
    }

    public String getInstalldata() {
        return this.installdata;
    }

    public void setInstalldata(String installdata) {
        this.installdata = installdata;
    }

}
