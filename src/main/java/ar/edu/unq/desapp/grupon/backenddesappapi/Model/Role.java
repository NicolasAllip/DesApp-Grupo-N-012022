package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    public static final String ADMIN = "ADMIN";

    private String authority;

    public String getAuthority() {
        return authority;
    }

}