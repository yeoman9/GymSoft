package com.gymsoft.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table( name = "payment" )
@EqualsAndHashCode( callSuper = false )
public class Payment extends Auditable<Long>
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "customer_id" )
    private Customer customer;

    @Temporal( TemporalType.DATE )
    private Date paymentFrom;
    
    @Temporal( TemporalType.DATE )
    private Date paymentTo;
       
    private int amount;
    
    private int months;
    
    private String mode;
}
