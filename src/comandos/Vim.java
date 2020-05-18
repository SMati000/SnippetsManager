package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import general.Main;
import java.io.File;

public class Vim extends Comandos implements Redirecter {
    public static final File VIM = new File(Ficheros.miDir, "recursos\\vim\\usr\\bin\\vim.exe"); 
    
    public Vim(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            if(instruccion.hasParametros()) {
                System.out.println(NO_NECESITO_PARAMETROS);
            } else {
                vim();
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    public void vim() {
        String snippet = Main.getUser().getEjecutandoseEnFile() + "\\" + instruccion.getArgumentoDelComando();
        
        try {
            String comando = "\"" + VIM.getCanonicalPath() + "\" \"" + snippet + "\"";
            
            if(new File(snippet).isFile()) {
                new ProcessBuilder(comando).inheritIO().start().waitFor();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "vim \"snippet\": te abre el snippet en vim, el editor integrado en el programa\n"
                + "____________________________________________________________________________");
    }
    
}
