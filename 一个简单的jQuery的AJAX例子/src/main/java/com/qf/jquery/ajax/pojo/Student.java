package com.qf.jquery.ajax.pojo;

/**
 * 这个类是一个实体类,他对应了数据库中的实际属性
 */
public class Student {

  private String stuId;
  private String stuName;
  private long gradeId;
  private java.util.Date bornDate;
  private String stuPhone;
  private String stuEmail;
  private String stuGender;
  private String stuAddress;
  private String stuPassword;


  public String getStuId() {
    return stuId;
  }

  public void setStuId(String stuId) {
    this.stuId = stuId;
  }


  public String getStuName() {
    return stuName;
  }

  public void setStuName(String stuName) {
    this.stuName = stuName;
  }


  public long getGradeId() {
    return gradeId;
  }

  public void setGradeId(long gradeId) {
    this.gradeId = gradeId;
  }


  public java.util.Date getBornDate() {
    return bornDate;
  }

  public void setBornDate(java.util.Date bornDate) {
    this.bornDate = bornDate;
  }


  public String getStuPhone() {
    return stuPhone;
  }

  public void setStuPhone(String stuPhone) {
    this.stuPhone = stuPhone;
  }


  public String getStuEmail() {
    return stuEmail;
  }

  public void setStuEmail(String stuEmail) {
    this.stuEmail = stuEmail;
  }


  public String getStuGender() {
    return stuGender;
  }

  public void setStuGender(String stuGender) {
    this.stuGender = stuGender;
  }


  public String getStuAddress() {
    return stuAddress;
  }

  public void setStuAddress(String stuAddress) {
    this.stuAddress = stuAddress;
  }


  public String getStuPassword() {
    return stuPassword;
  }

  public void setStuPassword(String stuPassword) {
    this.stuPassword = stuPassword;
  }

  @Override
  public String toString() {
    return "Student{" +
            "stuId='" + stuId + '\'' +
            ", stuName='" + stuName + '\'' +
            ", gradeId=" + gradeId +
            ", bornDate=" + bornDate +
            ", stuPhone='" + stuPhone + '\'' +
            ", stuEmail='" + stuEmail + '\'' +
            ", stuGender='" + stuGender + '\'' +
            ", stuAddress='" + stuAddress + '\'' +
            ", stuPassword='" + stuPassword + '\'' +
            '}';
  }
}
