package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {
    /*
    Nombre, Obligatorio, Min:3, Max:30
    Apellido, Obligatorio, Min:3, Max:30
    Email, Obligatorio, Formato de email
    Dirección, Obligatorio, Min:10, Max:30
    Contraseña
    CVU MercadoPago, Obligatorio (22 digitos)
    Dirección Billetera de CriptoActivos, Obligatorio (8 dígitos)
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String address;
    private String password;
    @Column(nullable = false)
    private Long cvu;
    private Float reputation;
    
    @Column(name="wallet_address", nullable = false)
    private Long walletAddress;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCvu() {
        return cvu;
    }

    public void setCvu(Long cvu) {
        this.cvu = cvu;
    }

    public Float getReputation() {
        return reputation;
    }

    public void setReputation(Float reputation) {
        this.reputation = reputation;
    }

    public Long getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(Long walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(address, user.address) && Objects.equals(password, user.password) && Objects.equals(cvu, user.cvu) && Objects.equals(reputation, user.reputation) && Objects.equals(walletAddress, user.walletAddress);
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    private static final long serialVersionUID = 1L;

    public static final class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public UserBuilder name(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder surname(String surname) {
            user.setSurname(surname);
            return this;
        }

        public UserBuilder email(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder address(String address) {
            user.setAddress(address);
            return this;
        }

        public UserBuilder password(String password) {
            user.setPassword(password);
            return this;
        }

        public UserBuilder cvu(Long cvu) {
            user.setCvu(cvu);
            return this;
        }

        public UserBuilder reputation(Float reputation) {
            user.setReputation(reputation);
            return this;
        }

        public UserBuilder walletAddress(Long walletAddress) {
            user.setWalletAddress(walletAddress);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
