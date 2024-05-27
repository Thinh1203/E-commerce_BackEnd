package com.ecommerce.WatchStore.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "numberPhone", nullable = false)
    private String numberPhone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "totalMoney")
    private Float totalMoney;

    @Column(name = "shippingMethod")
    private String shippingMethod;

    @Column(name = "shippingAddress")
    private String shippingAddress;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "status")
    private String status;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
