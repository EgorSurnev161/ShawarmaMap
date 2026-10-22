package ru.codekitchen.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id")
    private ShawarmaPlace shawarmaPlace;

    public Comment() {
    }

    public Comment(LocalDateTime date, User user, String text, ShawarmaPlace shawarmaPlace) {
        this.shawarmaPlace = shawarmaPlace;
        this.date = date;
        this.user = user;
        this.text = text;
    }
    public Comment(User user, String text, ShawarmaPlace shawarmaPlace) {
        this.shawarmaPlace = shawarmaPlace;
        this.date = LocalDateTime.now();
        this.user = user;
        this.text = text;
    }

    public ShawarmaPlace getShawarmaPlace() {
        return shawarmaPlace;
    }

    public void setShawarmaPlace(ShawarmaPlace shawarmaPlace) {
        this.shawarmaPlace = shawarmaPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
