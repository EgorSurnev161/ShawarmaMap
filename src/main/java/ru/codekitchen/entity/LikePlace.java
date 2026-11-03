package ru.codekitchen.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "place_id"})
})
public class LikePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id")
    private ShawarmaPlace shawarmaPlace;

    @Column(name = "is_like")
    private Boolean isLike;

    public LikePlace() {
    }

    public LikePlace(User user, ShawarmaPlace shawarmaPlace, Boolean isLike) {
        this.user = user;
        this.shawarmaPlace = shawarmaPlace;
        this.isLike = isLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShawarmaPlace getShawarmaPlace() {
        return shawarmaPlace;
    }

    public void setShawarmaPlace(ShawarmaPlace shawarmaPlace) {
        this.shawarmaPlace = shawarmaPlace;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}