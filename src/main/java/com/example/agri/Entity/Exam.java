    package com.example.agri.Entity;

    import com.example.agri.AbstractAuditingEntity;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import org.springframework.data.annotation.CreatedBy;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedBy;
    import org.springframework.data.annotation.LastModifiedDate;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;

    import java.io.Serializable;
    import java.sql.Timestamp;
    import java.time.Instant;

    @Entity
    public class Exam extends AbstractAuditingEntity {
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
        private long id;

        @Enumerated(EnumType.STRING)
        @JsonProperty("examName")  // Map JSON property to Java field
        private ExamType examName;

        @JsonProperty("telugu")
        private String telugu;

        @JsonProperty("hindi")
        private String hindi;

        @JsonProperty("english")
        public String english;

        @JsonProperty("maths")
        public String maths;

        @JsonProperty("science")
        public String science;

        @JsonProperty("social")
        public String social;

        // Getters and Setters
        public Object getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }



        @ManyToOne
        @JoinColumn(name = "user_id")
        @NotNull
        private User user;


        public ExamType getExamName() {
            return examName;
        }

        public void setExamName(ExamType examName) {
            this.examName = examName;
        }

        public String getTelugu() {
            return telugu;
        }

        public void setTelugu(String telugu) {
            this.telugu = telugu;
        }

        public String getHindi() {
            return hindi;
        }

        public void setHindi(String hindi) {
            this.hindi = hindi;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public String getMaths() {
            return maths;
        }

        public void setMaths(String maths) {
            this.maths = maths;
        }

        public String getScience() {
            return science;
        }

        public void setScience(String science) {
            this.science = science;
        }

        public String getSocial() {
            return social;
        }

        public void setSocial(String social) {
            this.social = social;
        }

        // Constructors
        public Exam(long id, ExamType examName, String telugu, String  hindi, String english, String maths, String science,
                    String social) {
            super();
            this.id = id;
            this.examName = examName;
            this.telugu = telugu;
            this.hindi = hindi;
            this.english = english;
            this.maths = maths;
            this.science = science;
            this.social = social;
        }

        public Exam() {
            super();
            // Default constructor
        }

        @Override
        public String toString() {
            return "Exam [id=" + id + ", examName=" + examName + ", telugu=" + telugu + ", hindi=" + hindi + ", english="
                    + english + ", maths=" + maths + ", science=" + science + ", social=" + social + "]";
        }

        public enum ExamType {
            UNIT_EXAM,
            HALF_YEARLY,
            ANNUAL
        }
    }
