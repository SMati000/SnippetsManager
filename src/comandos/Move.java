package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import funcionalidades.dirlist.DirListAll;
import general.Main;
import java.io.File;
import java.util.Scanner;

public class Move extends Comandos implements Redirecter {
    
    public Move(Instruccion instruccion) {
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
                
                move(this.instruccion.getArgumentoDelComando());
            
            } else {
                dirList = new DirListAll();
                move(dirList.ask());
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void move(String ubicacion) {
        Scanner in = new Scanner(System.in);
        
        ubicacion = pedirDatos("Snippet o Categoria a mover> ", ubicacion).toString();
        
        File mover = new File(Main.getUser().getEjecutandoseEnFile(), Ficheros.eliminarComillas(ubicacion));
        
        if(mover.exists()) {
            System.out.print("(No ponga nada para mover a la ruta principal de la Snippets Db)\n"
                    + "Mover a> ");
            
            File moverA = new File(Main.getLog().leerDeLogTxt(1), Ficheros.eliminarComillas(in.nextLine()));
            
            if(moverA.exists() && moverA.isDirectory()) {
                moverA = new File(moverA, mover.getName());
                
                if(mover.isFile()) {
                    Ficheros.CopiarFichero(mover, moverA);
                    mover.delete();
                } else if (mover.isDirectory()) {
                    Ficheros.copiarTodo(mover, moverA);
                    Ficheros.deleteAll(mover);
                }
                
            } else {
                System.out.println("No se puede mover a [" + moverA.getAbsolutePath() + "] :/");
            }
        } else {
            System.out.println("El archivo [" + mover.getAbsolutePath() + "] no existe :/");
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "move \"ubicacion\": este comando le permite mover un snippet o categoria.\n"
                + "* SI DESEA MOVER A LA RUTA PRINCIPAL DE LA SNIPPETS DB, EN [mover a> ] NO DEBE PONER NADA\n"
                + "____________________________________________________________________________");
    }
    
}
