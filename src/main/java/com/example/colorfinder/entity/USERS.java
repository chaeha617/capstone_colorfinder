package com.example.colorfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class USERS implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", updatable = false)
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "gender")
    private String gender;

    @ManyToOne
    @JoinColumn(name = "colorId", referencedColumnName = "colorId")
    private PERSONALCOLOR personalColor;

    @Builder
    public USERS(Long userId, String email, String password, String nickname, PERSONALCOLOR personalColor, String gender) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.personalColor = personalColor;
        this.gender = gender;
    }

    @Override
    public String getUsername() {
        return String.valueOf(userId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}