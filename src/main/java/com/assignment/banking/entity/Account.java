package com.assignment.banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // getters setters
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOUNT_DETAILS")
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private double balance;

    @ManyToOne /* This means that many Account instances can be associated with one User instance.*/
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
}
