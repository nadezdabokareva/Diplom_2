package diplom2.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class User implements Serializable {
    private String email;
    private String password;
    private String name;
    private String token;
}
