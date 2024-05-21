package com.example.colorfinder.entity;

import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PERSONALCOLOR")
public class PERSONALCOLOR {
    @Id
    @Column(name = "colorId")
    private String colorId;

    @Column(name = "colorName")
    private String colorName;

    public PERSONALCOLOR(String colorId) {
        this.colorId = colorId;
    }
}