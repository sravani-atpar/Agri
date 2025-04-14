package com.example.agri.Entity;

import com.example.agri.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class Course extends AbstractAuditingEntity {
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String location;
    private String Category;
    private String Course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }


}
