package com.qa.bank.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Vinay
 */

@Entity
@Table(name = "user")
public class User {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @NotNull
    @Size(min = 2, max = 15)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 15)
    private String lastName;

    @Min(18)
    @Max(120)
    private int age;

    /**
     * Default Constructor to be used by JPA
     */
    public User() {
    }

    /**
     * Constructor without ID field
     * @param username Username should be unique and it's not nullable
     * @param password Password - not null
     * @param firstName First name - not null
     * @param lastName Last name - not null
     * @param age age - can be left empty
     */
    public User(String username, String password, String firstName, String lastName, int age) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Constructor with all fields for testing purpose
     * @param id ID is unique and auto generated field
     * @param username Username should be unique and it's not nullable
     * @param password Password - not null
     * @param firstName First name - not null
     * @param lastName Last name - not null
     * @param age age - can be left empty
     */
    public User(Long id, String username, String password, String firstName, String lastName, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // >> Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Override toString, equals and hashCode Methods
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!Objects.equals(id, user.id)) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (!firstName.equals(user.firstName)) return false;
        return lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age;
        return result;
    }
}
