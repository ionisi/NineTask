package mine.spring.models;

import javax.persistence.*;

import javax.validation.constraints.*;

@Entity
@Table(name="user")
public class User {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
    @Column(name="name")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 120,message = "Age should be valid")
    @Column(name="age")
    private int age;
    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Email should be valid")
    @Column(name="email")
    private String email;

    public User() {}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
