package comandos;

import central_office.Instruccion;
import general.Main;

public class Logout extends Comandos implements Redirecter {

    public Logout(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(user.hasStarted()) {
            if(instruccion.hasParametros() || instruccion.hasArgumentoDelComando()) {
                System.out.println(NO_NECESITO_NINGUN_PARAMETRO_EXTRA);
            } else {
                logout();
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void logout() {
        user.login();
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "logout: el programa cierra la sesion activa y solicita la contrasena nuevamente\n"
                + "* este comando es inutil si no ha establecido una contrasena\n"
                + "____________________________________________________________________________");
    }
}
