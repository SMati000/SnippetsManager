package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import funcionalidades.dirlist.DirListAll;
import general.Main;
import java.io.File;
import java.util.Scanner;

public class Export extends Comandos implements Redirecter {
    
    public Export(Instruccion instruccion) {
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
            } else if(instruccion.hasArgumentoDelComando()) {
                export(instruccion.getArgumentoDelComando());
            } else {
                dirList = new DirListAll();
                export(dirList.ask());
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void export(String ruta) {
        Scanner in = new Scanner(System.in);
            
        ruta = pedirDatos("Exportar> ", ruta).toString();
        
        File exportar = new File(Main.getUser().getEjecutandoseEnFile(), Ficheros.eliminarComillas(ruta));
        
        if(exportar.exists()) {
            
            System.out.print("Exportar a> ");
            File destino = new File(Ficheros.eliminarComillas(in.nextLine()));
            
            if(destino.exists() && destino.isDirectory()) {
                
                if(exportar.isFile()) {
                
                    Ficheros.CopiarFichero(exportar, new File(destino, exportar.getName()));
                
                } else if(exportar.isDirectory()){
                
                    Ficheros.copiarTodo(exportar, new File(destino, exportar.getName()));
                
                }
                
            } else { System.out.println("El destino [" + destino.getAbsolutePath() + "] ni existe :/"); }
            
        } else {
            System.out.println("El archivo o categoria [" + exportar.getAbsolutePath() + "] no existe\n"
                    + "Es importante que en caso de ser un archivo, ponga la extension correspondiente");
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                    + "export \\\"ubicaion\\\": este comando le permite hacer una copia del snippet\n"
                    + "     o categoria indicada a una carpeta externa a la SnippetsDb\n"
                + "____________________________________________________________________________");
    }
    
}