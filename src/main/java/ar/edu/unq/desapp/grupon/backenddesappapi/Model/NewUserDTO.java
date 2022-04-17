package ar.edu.unq.desapp.grupon.backenddesappapi.Model;

import ar.edu.unq.desapp.grupon.backenddesappapi.Utils.RegexValues;
import org.springframework.beans.factory.annotation.Value;

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
    //TODO: regex para email
    private String email; //TODO: va a ser unique

    @NotBlank(message = "User must have an address")
    @Size(min = 10, max = 30, message = "User's address must be between 10 and 30 characters")
    private String address;

    @NotBlank(message = "User must have a password")
    private String password; //TODO: agregar regex: al menos 1 minuscula, 1 mayuscula, 1 carac especial y min 6

    @NotBlank(message = "User must have a CVU")
    @Size(min = 22, max = 22, message = "User's CVU must be 22 characters")
    private Long cvu;

    @NotBlank(message = "User must have a wallet address")
    @Size(min = 8, max = 8, message = "User's wallet address must be 8 characters")
    private Long walletAddress;
}
