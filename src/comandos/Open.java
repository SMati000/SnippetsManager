package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import static comandos.Vim.VIM;
import funcionalidades.dirlist.*;
import general.Main;
import java.io.File;
import java.io.IOException;

public class Open extends Comandos implements Redirecter {

    public Open(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(Main.getUser().hasStarted()) {
            StringBuilder program = new StringBuilder(Main.getUser().getDefaultProgram());
            
            if(this.instruccion.hasParametros()) {
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    if(paramYArg.getParametro().equalsIgnoreCase("-openWith")) {
                        program.replace(0, program.length(), paramYArg.getArgumento());
                    }
                });
            }
            
            if(instruccion.hasArgumentoDelComando()) {
                open(this.instruccion.getArgumentoDelComando(), program.toString());
            } else {
                dirList = new DirListFilesOnly();
                open(dirList.ask(), program.toString());
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void open(String file, String program) {
        
        file = pedirDatos("Abrir> ", file).toString();
        
        file = Ficheros.eliminarComillas(file);

        File snippet = new File(Main.getUser().getEjecutandoseEnFile(), file);

        if(snippet.exists() && snippet.isFile()) {
            
            try {
                
                if(new File(program).isFile()) {
                    Runtime runtime = Runtime.getRuntime();
                    
                    Process process = runtime.exec(program + " " + snippet);
                } else {
                    String comando = "\"" + VIM.getCanonicalPath() + "\" \"" + snippet.getCanonicalPath() + "\"";
                    
                    new ProcessBuilder(comando).inheritIO().start().waitFor();
                }
                
            } catch(IOException | InterruptedException e) {}
            
        } else {
            System.out.println("El archivo no existe");
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "open \"categoria/snippet.txt\": este comando abrira el snippet indicado para que pueda verlo y editarlo\n"
                + "     -openwith: le permite indicar un programa con el cual abrir el snippet"
                + "____________________________________________________________________________");
    }
    
}
