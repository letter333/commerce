package org.letter33.commerce.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.letter33.commerce.common.entity.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE users SET delete_flag = true, deleted_at = now() WHERE id = ?")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 10)
    private String tag;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 13)
    private String phone;

    @Column(length = 8)
    private LocalDate birthdate;

    @Column
    private String gender;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private boolean active = true;

    @Column
    private boolean deleteFlag = false;

    @Column
    private LocalDateTime deletedAt;

    @Builder
    public User(String name, String nickname, String tag, String email, String phone, LocalDate birthdate, String gender, String password, UserRole role) {
        this.name = name;
        this.nickname = nickname;
        this.tag = tag;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.gender = gender;
        this.password = password;
        this.role = role != null ? role : UserRole.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !deleteFlag;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public int getAge() {
        if(birthdate == null) {
            return 0;
        }

        return Period.between(birthdate, LocalDate.now()).getYears();
    }
}


