package com.example.ali_hasnain_2k18_bscs_116;

public class view_user_class {

    int id;
    String studentname;
    String srollno;
    String degree;
    String section;
    int session;
    String semail;


    view_user_class(int id, String studentname, String srollno, String degree,String section, int session, String semail)
    {
        this.id = id;
        this.studentname=studentname;
        this.srollno=srollno;
        this.degree=degree;
        this.section=section;
        this.session=session;
        this.semail=semail;
    }

    public int getId() {
        return id;
    }

    public String getStudentname() {
        return studentname;
    }


    public String getSrollno() {
        return srollno;
    }

    public String getDegree() {
        return degree;
    }

    public String getSection() {
        return section;

    }

    public int getSession() {
        return session;
    }

    public String getSemail() {
        return semail;
    }




}
