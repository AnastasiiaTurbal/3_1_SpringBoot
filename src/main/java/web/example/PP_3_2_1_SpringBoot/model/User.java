package web.example.PP_3_2_1_SpringBoot.model;

//Норм ли, что вместо javax теперь jakarta? Из-за версии Spring?
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String password;

    public User(){

    }

    public User(String name, String surname, int age, String password) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public User(Long id, String name, String surname, int age, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

