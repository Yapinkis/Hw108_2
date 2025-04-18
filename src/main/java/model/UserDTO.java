package model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private String name;
    private String email;
    private Integer age;
    private LocalDateTime createdAt;
}
