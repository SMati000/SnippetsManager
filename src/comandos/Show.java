package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import general.Main;
import java.io.File;

public class Show extends Comandos implements Redirecter {

    public Show(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            if(instruccion.hasArgumentoDelComando()) {
                System.out.println(NO_NECESITO_ARGUMENTO_DEL_COMANDO);
                return;
            }
            
            if(instruccion.hasParametros()) {
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    if(paramYArg.getParametro().equals("-this")) {
                        showThis();
                    } else {
                        System.out.println("Parametro inexistente");
                    }
                });
            } else {
                show();
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void show() {
        File dir = new File(Main.getLog().leerDeLogTxt(1));
        System.out.println(dir.getAbsolutePath());
        
        Ficheros.displayAll(dir, "    ");
    }
    
    private void showThis() {
        String[] rutas = Main.getUser().getEjecutandoseEnFile().list();
        
        for (String ruta : rutas) {
            if (new File(Main.getUser().getEjecutandoseEnFile(), ruta).isDirectory()) {
                System.out.println("> " + ruta);
            } else {
                System.out.println(ruta);
            }
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                    + "show: Desplega un arbol con todo el contenido de la SnippetsDB\n"
                    + "    -this: muestra los archivos y carpetas en la ubicacion especifica en la que estas.\n"
                + "____________________________________________________________________________");
    }
}
