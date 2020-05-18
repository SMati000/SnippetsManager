package general;

import central_office.InicioDelPrograma;
import backend.Log;
import user.User;

public class Main {
    
    private static final Log log = new Log("log.txt"); // se usa en todo el programa
    private static User user; // se usa en todo el programa
    
    public static void main(String[] args) {
        log.crearLogTxt(); // no lo crea si ya esta creado
        user = new User();
        
        InicioDelPrograma.bienvenida();
        
        user.login();
    }
    
    public static Log getLog() {
        return log;
    }
    
    public static User getUser() {
        return user;
    }
    
}
