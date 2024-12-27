package self.project.eventmanagement.account.persistance.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "account")
@Getter
@Setter
@EqualsAndHashCode
public class Account {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "password_salt", nullable = false)
    private String passwordSalt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
