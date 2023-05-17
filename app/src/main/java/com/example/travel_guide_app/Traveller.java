package com.example.travel_guide_app_1181390_1182126;

public class Traveller {
    private long id;
    private String email;
    private String fname;
    private String lname;
    private String password;
    private String confirmpassword;
    private String preferred;

    public Traveller (){

    }

    public Traveller(long id, String email, String fname, String lname, String password, String confirmpassword, String preferred) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.preferred = preferred;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public String getPreferred() {
        return preferred;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public void setPreferred(String preferred) {
        this.preferred = preferred;
    }

    @Override
    public String toString() {
        return "Traveller{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                ", preferred='" + preferred + '\'' +
                '}';
    }
}


