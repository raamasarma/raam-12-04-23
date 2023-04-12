package com.raithanna.dairy.RaithannaDairy.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class purchaseOrder {
    @Id
    @GeneratedValue
    private int id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate invDate;
    private Double quantity;
    private Double fatP;
    private Double snfP;
    private Double tsRate;
    private String code;
    private Double ltrRate;
    private String milkType;
    private String supplier;
    private int invNo;
    private LocalDate recDate;
}
