package comandos;

import central_office.Instruccion;
import funcionalidades.dirlist.DirListAll;
import general.Main;
import java.io.File;

public class Rename extends Comandos implements Redirecter {

    public Rename(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(Main.getUser().hasStarted()) {
            StringBuilder renameTo = new StringBuilder();
            
            if(instruccion.hasParametros()) {
                
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    if(paramYArg.getParametro().equals("-to")) {
                        renameTo.append(paramYArg.getArgumento());
                    } else {
                        System.out.println("Parametro " + paramYArg.getParametro() + " inexistente");
                    }
                });
            }
            
            if(instruccion.hasArgumentoDelComando()) {
                rename(instruccion.getArgumentoDelComando(), renameTo.toString());
            } else {
                dirList = new DirListAll();
                rename(dirList.ask(), renameTo.toString());
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void rename(String archivo, String renameTo) {
        archivo = pedirDatos("Archivo a renombrar> ", archivo).toString();
        renameTo = pedirDatos("Nombre nuevo> ", renameTo).toString();
        
        try {
            File archivoARenombrar = new File(Main.getUser().getEjecutandoseEnFile(), archivo);
            
            if(archivoARenombrar.exists() && !archivo.isEmpty()) {
                archivoARenombrar.renameTo(new File(archivoARenombrar.getParent(), renameTo));
            } else {
                System.out.println("El archivo indicado no existe");
            }
            
        } catch(NullPointerException | SecurityException e) {
            System.out.println("No se ha podido renombrar el archivo");
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "rename \"archivo\": renombrar archivos y carpetas\n"
                        + "     -to \"nombrenuevo\"\n"
                + "____________________________________________________________________________");
    }
    
}