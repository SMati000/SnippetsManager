package comandos;

import central_office.Instruccion;
import general.Main;

public class Config extends Comandos implements Redirecter {

    public Config(Instruccion instruccion) {
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
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    if(paramYArg.getParametro().equals("-dp")) {
                        setDefaultProgram(paramYArg.getArgumento());
                    }
                });
            } else {
                System.out.println("Este comando necesita parametros");
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void setDefaultProgram(String program) {
        Main.getLog().escribirEnLogTxt(2, program);
        Main.getUser().setDefaultProgram(program);
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "config: te permite configurar y personalizar el programa\n"
                + "     -dp \"programa\": te permite establecer un editor predeterminado para ver y editar tus snippets.\n"
                + "____________________________________________________________________________");
    }
    
}