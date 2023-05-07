package com.app.edu_academic_solution.model;

public class pdfClass {

    public  String name, url;
    public int getName;

    public  pdfClass(){

    }
    public  pdfClass(String name,String url){
        this.name=name;
        this.url = url;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
