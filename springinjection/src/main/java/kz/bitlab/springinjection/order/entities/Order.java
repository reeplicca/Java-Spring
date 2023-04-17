package kz.bitlab.springinjection.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "foodname")
    private String foodName;

    @Column(name = "foodprice")
    private int foodPrice;

    @Column(name = "foodamount")
    private int foodAmount;

    @Column(name = "useraddress")
    private String userAddress;

    @Column(name = "linktoimage")
    private String linkToImage;
}
