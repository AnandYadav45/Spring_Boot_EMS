package com.EMS.Employee_Management_System.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "address", schema = "users")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String Landmark;

    private String PostalCode;

    private String City;

    private String State;

    private String Country;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Address(Long id, String landmark, String postalCode, String city, String state, String country) {
        this.id = id;
        Landmark = landmark;
        PostalCode = postalCode;
        City = city;
        State = state;
        Country = country;
    }

    public Address() {
    }
}
