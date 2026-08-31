package ru.codekitchen.entity.dto;

import ru.codekitchen.entity.ShawarmaPlace;
import ru.codekitchen.entity.User;

import java.time.LocalDate;

public class ShawarmaPlaceDto {
    private int id;
    private int userId;
    private int numberOfDislikes;
    private int numberOfLikes;
    private  String name;
    private  String city;
    private  String address;
    private LocalDate createDate;

    public static ShawarmaPlaceDto toDto(ShawarmaPlace place) {
        ShawarmaPlaceDto dto = new ShawarmaPlaceDto();
        dto.setId(place.getId());
        dto.setName(place.getName());
        dto.setCity(place.getCity());
        dto.setAddress(place.getAddress());
        return dto;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }
}
