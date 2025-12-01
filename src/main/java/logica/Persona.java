
package logica;

public abstract class Persona {
    private int idUser;
    private String name;
    private int nId;
    private String email;
    private String role;

    public Persona(int idUser, String name, int nId, String email, String role) {
        this.idUser = idUser;
        this.name = name;
        this.nId = nId;
        this.email = email;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
