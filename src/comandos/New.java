package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import general.Main;
import java.io.File;
import java.util.Scanner;

public class New extends Comandos implements Redirecter {

    public New(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasArgumentoDelComando()) {
            System.out.println(NO_NECESITO_ARGUMENTO_DEL_COMANDO);
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            if(instruccion.hasParametros()) {
                
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    
                    switch(paramYArg.getParametro()) {
                        case "-cat":
                            newCat(paramYArg.getArgumento());
                            break;
                        default:
                            System.out.println("Parametro Inexistente :/");
                            break;
                    }
                    
                });
                
            } else {
                System.out.println("Este comando requiere parametros para funcionar");
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }

    private void newCat(String cat) {
        if(cat == null || cat.isEmpty()) {
            Scanner in = new Scanner(System.in);
            
            System.out.print("Categoria a crear> ");
            cat = in.nextLine();
        }
        
        File category = new File(Main.getUser().getEjecutandoseEnFile(), Ficheros.eliminarComillas(cat));
        
        if(!category.exists()) {
            try {
                category.mkdir();
            } catch(Exception e) {
                System.out.println("No se ha podido crear el directorio. Compruebe su instruccion");
            }
        } else { 
            System.out.println("Ya existe una categoria con ese nombre."); 
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                    + "new: Crea un/a nuevo/a...\n"
                    + "    -cat \"nombre\" (case-insensitive): se crea una categoria en la SnippetsDB.\n"
                    + "* Si quieres crear una categoria dentro de otra, es decir, una subcategoria,\n"
                    + "  solo pon new -cat \"categoria\\nueva subcategoria\"\n"
                    + "* CUANDO CUALQUIERA DE LOS NOMBRES LLEVAN ESPACIOS, DEBE PONER COMILLAS COMO SE MUESTRA EN EL EJEMPLO\n"
                    + "____________________________________________________________________________");
    }
}
