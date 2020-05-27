package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import funcionalidades.dirlist.DirList;
import java.util.Scanner;

public abstract class Comandos implements Redirecter {
    protected final String HELP = "Consulte la ayuda especifica del comando o la ayuda general con el comando help";
    protected final String DEBE_INICIAR_MSG = "Debe iniciar para usar este comando. utilice el comando [start -help]";
    
    protected final String NO_NECESITO_NINGUN_PARAMETRO_EXTRA = "Este comando no requiere ningun dato o parametro extra.";
    protected final String NO_NECESITO_ARGUMENTO_DEL_COMANDO = "Este comando no necesita argumento";
    protected final String NO_NECESITO_PARAMETROS = "Este comando no necesita parametros";
    
    protected Instruccion instruccion;
    protected DirList dirList;
    
    public Comandos(Instruccion instruccion) {
        this.instruccion = instruccion;
        if(!needHelp()) { 
            this.redirecter();
        }
    }
    
    public Object pedirDatos(String mensaje, Object variable) {
        if(variable == null || variable.toString().isEmpty()) {
            Scanner in = new Scanner(System.in);
        
            System.out.print(mensaje);
            variable = Ficheros.eliminarComillas(in.nextLine());
        }
        
        return variable;
    }
    
    private boolean needHelp() {
        if(this.instruccion.hasParametro("-help")) {
            this.help();
            return true;
        }
        
        return false;
    }
    
    public void help() {
        System.out.println("Algo no esta bien con su instruccion... \n"
                + HELP);
    }
}
