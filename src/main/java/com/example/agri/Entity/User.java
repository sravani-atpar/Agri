package com.example.agri.Entity;

import com.example.agri.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
public class User  extends AbstractAuditingEntity<Long> implements UserDetails   {
    @MappedSuperclass
    @EntityListeners(AuditingEntityListener.class)
    @JsonIgnoreProperties(value = { "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, allowGetters = true)
    public abstract class AbstractAuditingEntity<T> implements Serializable {

        private static final long serialVersionUID = 1L;

        public abstract T getId();

        @CreatedBy
        @Column(name = "created_by", nullable = true, length = 50, updatable = false)
        private String createdBy;

        @CreatedDate
        @Column(name = "created_date", updatable = false)
        private Timestamp createdDate = Timestamp.from(Instant.now());

        @LastModifiedBy
        @Column(name = "last_modified_by", length = 50)
        private String lastModifiedBy;

        @LastModifiedDate
        @Column(name = "last_modified_date")
        private Timestamp lastModifiedDate = Timestamp.from(Instant.now());

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public Timestamp getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Timestamp createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public Timestamp getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(Timestamp lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }
    }
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String address;

        public String getUsername() {
            return username;
        }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Column(nullable = false)
        private String password;

        private Role role;

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public enum Role {
            STUDENT,
            TRAINER
        }

        // Getters and setters
    }


