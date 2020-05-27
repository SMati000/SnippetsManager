package comandos;

import central_office.Instruccion;
import java.io.IOException;

public class Clear extends Comandos implements Redirecter {

    public Clear(Instruccion instruccion) {
        super(instruccion);
    }
    
    @Override
    public void redirecter() {
        if(instruccion.hasParametros() || instruccion.hasArgumentoDelComando()) {
            System.out.println(NO_NECESITO_NINGUN_PARAMETRO_EXTRA);
        } else {
            clear();
        }
    }
    
    private void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {}
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "clear: limpia la consola\n"
                + "____________________________________________________________________________");
    }
}
