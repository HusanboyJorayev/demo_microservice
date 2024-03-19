package demo.userregistry.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.userregistry.feign.CardDto;
import lombok.*;
import org.apache.catalina.LifecycleState;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
