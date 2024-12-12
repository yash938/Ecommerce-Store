package com.Ecommerce.web.application.Model;

import com.Ecommerce.web.application.Domain.ACCOUNT_STATUS;
import com.Ecommerce.web.application.Domain.USER_ROLE;
import com.Ecommerce.web.application.Dto.BankDetails;
import com.Ecommerce.web.application.Dto.BusinessDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sellerName;

    @Column(unique = true,nullable = false)
    private String email;
    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();
    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress = new Address();

    private String GSTIN;
    private USER_ROLE role = USER_ROLE.ROLE_SELLER;

    private boolean isEmailVerified = false;

    private ACCOUNT_STATUS accountStatus = ACCOUNT_STATUS.PENDING;



}
