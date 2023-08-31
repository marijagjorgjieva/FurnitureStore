package com.proekt.dnick.aldehyde.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "furnitures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Furniture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "furniture_id_seq")
    @SequenceGenerator(name = "furniture_id_seq", sequenceName = "furniture_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "furniture_title", nullable = false)
    private String furnitureTitle;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "material", nullable = false)
    private String material;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "volume", nullable = false)
    private String volume;

    @Column(name = "type", nullable = false)
    private String type;
}
