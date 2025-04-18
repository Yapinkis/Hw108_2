package model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name", nullable = false)
    private String name;
    @Column(name ="email", nullable = false, unique = true)
    private String email;
    @Column(name ="age", nullable = false)
    private Integer age;
    @Column(name ="created_at", nullable = false)
    private LocalDateTime createdAt;
}
