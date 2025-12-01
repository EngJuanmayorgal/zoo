
package logica;

public class Usuario extends Persona {
    
    private String nRegistro;

    public Usuario(String nRegistro, int idUser, String name, int nId, String email, String role) {
        super(idUser, name, nId, email, role);
        this.nRegistro = nRegistro;
    }

    public String getnRegistro() {
        return nRegistro;
    }

    public void setnRegistro(String nRegistro) {
        this.nRegistro = nRegistro;
    }

   
    
}
