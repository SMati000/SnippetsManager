package comandos;

import central_office.Instruccion;

public class Exit extends Comandos implements Redirecter {

    public Exit(Instruccion instruccion) {
        super(instruccion);
    }
    
    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(instruccion.hasParametros() || instruccion.hasArgumentoDelComando()) {
            System.out.println(NO_NECESITO_NINGUN_PARAMETRO_EXTRA);
        } else {
            exit();
        }
    }
    
    private void exit() {
        System.exit(0);
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "exit: le permite salir del programa\n"
                + "____________________________________________________________________________");
    }
}
