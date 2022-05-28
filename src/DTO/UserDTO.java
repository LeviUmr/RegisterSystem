package DTO;

public class UserDTO {
    private String nome;
    private String password;
    int userId;


    public UserDTO(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "\nnome "+getNome()+"\nid "+getUserId();
    }
}


