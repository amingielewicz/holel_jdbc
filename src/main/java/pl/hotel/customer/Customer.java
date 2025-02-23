package pl.hotel.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
    private int id;
    private String name;
    private String surname;
    private String pesel;
    private LocalDate createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    public Customer(int id, String name, String surname, String pesel, LocalDate createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }

    public Customer(int id, String name, String surname, String pesel, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.createDate = createDate;
    }

    public Customer(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }
}

