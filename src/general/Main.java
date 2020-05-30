package general;

import central_office.InicioDelPrograma;
import central_office.Configurations.Configurations;
import user.User;

public class Main {
    private static User user; // se usa en todo el programa
    
    public static void main(String[] args) {
        Configurations.setUp();
        user = new User();
        
        InicioDelPrograma.bienvenida();
        
        user.login();
    }
    
    public static User getUser() {
        return user;
    }
    
    public static void setUser(User user) {
        Main.user = user;
    }
}
