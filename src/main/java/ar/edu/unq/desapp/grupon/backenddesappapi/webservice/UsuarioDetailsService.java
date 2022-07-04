package ar.edu.unq.desapp.grupon.backenddesappapi.webservice;




@Service
public class UsuarioDetailsService implements UserDetailsService {

    private Map<String, String> usuarios = Map.of(
        "test", "USER",
        "admin", "ADMIN"
    );

    public void addUser(String username) {
        usuarios.put(username, "USER")
    }

    @Override
    public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
        var rol = usuarios.get(username);
        if(rol != null) {
            User.UserBuilder userBuilder = User.withUsername(username);
            String encryptedPassord = "2a$10$56VCAiApL08NQYe0Piu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq";
            userBuilder.password(encryptedPassord).rol(rol);
            return userBuilder.build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}