package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements UserDetails {

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
    @Column(nullable = false, unique = true)
    private String cvu;
    private Float reputation;
    @Column(name="wallet_address", nullable = false, unique = true)
    private String walletAddress;
    @Column(name="operation_amount", nullable = false)
    private Integer operationAmount;
    private String rol;

    public User(String name, String surname, String email, String address, String password, String cvu, Float reputation, String walletAddress) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvu = cvu;
        this.reputation = reputation;
        this.walletAddress = walletAddress;
        this.operationAmount = 0;
    }

    public User() {}

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

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public Float getReputation() {
        return reputation;
    }

    public void setReputation(Float reputation) {
        this.reputation = reputation;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public Integer getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(Integer operationAmount) {
        this.operationAmount = operationAmount;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(address, user.address) && Objects.equals(password, user.password) && Objects.equals(cvu, user.cvu) && Objects.equals(reputation, user.reputation) && Objects.equals(walletAddress, user.walletAddress) && Objects.equals(operationAmount, user.operationAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, address, password, cvu, reputation, walletAddress, operationAmount);
    }

    public void lowerReputationBy(Long x) {
        this.reputation -= x;
    }

    public void increaseReputationBy(Long x) {
        this.reputation += x;
    }

    public void increaseOperationAmount() {
        this.operationAmount += 1;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }
  
    @Override
    public boolean isAccountNonLocked() {
      return true;
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    public static final class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public UserBuilder id(Long id) {
            user.setId(id);
            return this;
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

        public UserBuilder cvu(String cvu) {
            user.setCvu(cvu);
            return this;
        }

        public UserBuilder reputation(Float reputation) {
            user.setReputation(reputation);
            return this;
        }

        public UserBuilder walletAddress(String walletAddress) {
            user.setWalletAddress(walletAddress);
            return this;
        }

        public UserBuilder rol(String rol) {
            user.setRol(rol);
            return this;
        }

        public UserBuilder operationAmount(Integer operationAmount) {
            user.setOperationAmount(operationAmount);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
