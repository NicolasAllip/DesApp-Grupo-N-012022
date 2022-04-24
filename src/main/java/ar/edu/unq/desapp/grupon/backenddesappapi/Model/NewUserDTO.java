package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

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
    private String password; //TODO: agregar regex: al menos 1 minuscula, 1 mayuscula, 1 carac especial y min 6

    @NotBlank(message = "User must have a CVU")
    @Size(min = 22, max = 22, message = "User's CVU must be 22 characters")
    @Pattern(regexp = "^[0-9]*$", message = "")
    private String cvu;

    @NotBlank(message = "User must have a wallet address")
    @Size(min = 8, max = 8, message = "User's wallet address must be 8 characters")
    @Pattern(regexp = "^[0-9]*$", message = "")
    private String walletAddress;
}
