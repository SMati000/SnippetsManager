package general;

import central_office.InicioDelPrograma;
import central_office.Configurations.Configurations;
import user.User;

public class Main {    
    public static void main(String[] args) {
        Configurations.setUp();
        
        InicioDelPrograma.bienvenida(new User());
    }
}
