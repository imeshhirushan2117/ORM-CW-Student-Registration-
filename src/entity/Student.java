package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String regNo;
    private String name;
    private int age;
    private String contactNo;
    private String address;
    private String email;
    private String gender;

    @ManyToMany
    private List<Program> programList = new ArrayList<>();


    public Student() {
    }

    public Student(String regNo, String name, int age, String contactNo, String address, String email, String gender) {
        this.regNo = regNo;
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
        this.address = address;
        this.email = email;
        this.gender = gender;
    }

    public Student(String regNo, String name, int age, String contactNo, String address, String email, String gender, List<Program> programList) {
        this.regNo = regNo;
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.programList = programList;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "regNo='" + regNo + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", programList=" + programList +
                '}';
    }
}
