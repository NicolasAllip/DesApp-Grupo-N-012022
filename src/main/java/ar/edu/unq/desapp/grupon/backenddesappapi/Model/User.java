

public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String password;
    private Integer cv;
    private Float reputation;
    private Integer walletAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname
    }

    public String getemail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password
    }


    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu
    }


    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputatione) {
        this.reputation = reputation
    }


    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress
    }


}

