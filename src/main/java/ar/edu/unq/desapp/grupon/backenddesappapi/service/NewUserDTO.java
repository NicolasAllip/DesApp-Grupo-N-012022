package ar.edu.unq.desapp.grupon.backenddesappapi.Service;

import javax.validation.constraints.*;

public class NewUserDTO {
    /*
    Nombre, Obligatorio, Min:3, Max:30
    Apellido, Obligatorio, Min:3, Max:30
    Email, Obligatorio, Formato de email
    Dirección, Obligatorio, Min:10, Max:30
    Contraseña
    CVU MercadoPago, Obligatorio (22 digitos)
    Dirección Billetera de CriptoActivos, Obligatorio (8 dígitos)
     */

    @NotBlank(message = "User must have a name")
    @Size(min = 3, max = 30, message = "User's name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "User must have a surname")
    @Size(min = 3, max = 30, message = "User's surname must be between 3 and 30 characters")
    private String surname;

    @NotBlank(message = "User must have an e-mail")
    @Pattern(regexp = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", message = "Must be valid email")
    private String email; //TODO: va a ser unique

    @NotBlank(message = "User must have an address")
    @Size(min = 10, max = 30, message = "User's address must be between 10 and 30 characters")
    private String address;

    @NotBlank(message = "User must have a password")
    @Size(min = 6, message = "Password must contain at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$", message = "The password must contain at least one uppercase letter, one lowercase letter and one special character")
    private String password;

    @NotBlank(message = "User must have a CVU")
    @Size(min = 22, max = 22, message = "User's CVU must be 22 characters")
    @Pattern(regexp = "^[0-9]*$", message = "")
    private String cvu;

    @NotBlank(message = "User must have a wallet address")
    @Size(min = 8, max = 8, message = "User's wallet address must be 8 characters")
    @Pattern(regexp = "^[0-9]*$", message = "")
    private String walletAddress;

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

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public static NewUserDTOBuilder builder() {
        return new NewUserDTOBuilder();
    }

    public static final class NewUserDTOBuilder {
        private NewUserDTO newUserDTO;

        private NewUserDTOBuilder() {
            newUserDTO = new NewUserDTO();
        }

        public NewUserDTOBuilder name(String name) {
            newUserDTO.setName(name);
            return this;
        }

        public NewUserDTOBuilder surname(String surname) {
            newUserDTO.setSurname(surname);
            return this;
        }

        public NewUserDTOBuilder email(String email) {
            newUserDTO.setEmail(email);
            return this;
        }

        public NewUserDTOBuilder address(String address) {
            newUserDTO.setAddress(address);
            return this;
        }

        public NewUserDTOBuilder password(String password) {
            newUserDTO.setPassword(password);
            return this;
        }

        public NewUserDTOBuilder cvu(String cvu) {
            newUserDTO.setCvu(cvu);
            return this;
        }

        public NewUserDTOBuilder walletAddress(String walletAddress) {
            newUserDTO.setWalletAddress(walletAddress);
            return this;
        }

        public NewUserDTO build() {
            return newUserDTO;
        }
    }
}
