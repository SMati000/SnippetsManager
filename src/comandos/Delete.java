package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import funcionalidades.dirlist.*;
import general.Main;
import java.io.File;
import java.util.Scanner;

public class Delete extends Comandos implements Redirecter {

    public Delete(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(this.instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            if(this.instruccion.hasParametros()) {
                System.out.println(NO_NECESITO_PARAMETROS);
            } else if(instruccion.hasArgumentoDelComando()) {
                delete(this.instruccion.getArgumentoDelComando());
            } else {
                dirList = new DirListAll();
                
                delete(dirList.ask());
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void delete(String toDelete) {
        toDelete = pedirDatos("Eliminar> ", toDelete).toString();
        
        File delete = new File(Main.getUser().getEjecutandoseEnFile(), Ficheros.eliminarComillas(toDelete));
        
        if(delete.exists() && !delete.equals(Main.getUser().getEjecutandoseEnFile())) {
            
            if(delete.isFile()) {
                
                Ficheros.deleteFile("Seguro que desea eliminar el snippet?", delete);
                
            } else if(delete.isDirectory()) {
                
                Scanner in = new Scanner(System.in);
                System.out.print("Seguro desea eliminar esa categoria? (y/n)> ");
                String confirm = in.nextLine();
                
                switch(confirm) {
                    case "y":
                        Ficheros.deleteAll(delete);
                        break;
                    case "n":
                        System.out.println("Okay :)");
                        break;
                    default:
                        System.out.println("Letra invalida :/");
                        break;
                }
            }
        } else {System.out.println("Ese archivo o categoria no existe :/");}
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "delete \"ubicacion\": le permite eliminar un snippet, categoria o subcategoria\n"
                + "____________________________________________________________________________");
    }
}
