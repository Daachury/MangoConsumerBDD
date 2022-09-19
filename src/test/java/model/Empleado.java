package model;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter

public class Empleado implements Serializable {

    private Integer id;
    private String employee_name;
    private double employee_salary;
    private int employee_age;
    private String profile_image;


    public Empleado(){}

    @Override
    public String toString() {
        return "id:"+this.getId()+","+
                "employee_name:" +this.getEmployee_name()+","+
                "employee_salary:"+this.getEmployee_salary()+","+
                "employee_age:"+this.getEmployee_age()+","+
                "profile_image:"+this.getProfile_image();
    }

}
