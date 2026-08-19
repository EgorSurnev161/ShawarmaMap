package ru.codekitchen.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 20)
    private  String name;

    @Column(name = "email",unique = true, nullable = false, length = 30)
    private  String email;

    @Column(name = "password", nullable = false,  length = 60)
    private  String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL)
    private List<ShawarmaPlace> shawarmaPlaces;

    public User() {
    }

    public User(String name, String email, String password, List<Comment> comments, List<ShawarmaPlace> shawarmaPlaces) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.comments = comments;
        this.shawarmaPlaces = shawarmaPlaces;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<ShawarmaPlace> getShawarmaPlaces() {
        return shawarmaPlaces;
    }

    public void setShawarmaPlaces(List<ShawarmaPlace> shawarmaPlaces) {
        this.shawarmaPlaces = shawarmaPlaces;
    }
}
