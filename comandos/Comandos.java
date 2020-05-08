package comandos;

import central_office.Instruccion;

public abstract class Comandos {
    protected final String HELP = "Consulte la ayuda especifica del comando o la ayuda general con el comando help";
    protected final String DEBE_INICIAR_MSG = "Debe iniciar para usar este comando. utilice el comando [start -help]";
    
    protected final String NO_NECESITO_NINGUN_PARAMETRO_EXTRA = "Este comando no requiere ningun dato o parametro extra.";
    protected final String NO_NECESITO_ARGUMENTO_DEL_COMANDO = "Este comando no necesita argumento";
    protected final String NO_NECESITO_PARAMETROS = "Este comando no necesita parametros";
    
    protected Instruccion instruccion;
    
    public Comandos(Instruccion instruccion) {
        this.instruccion = instruccion;
    }
    
    public void help() {
        System.out.println("Algo no esta bien con su instruccion... \n"
                + HELP);
    }
}
