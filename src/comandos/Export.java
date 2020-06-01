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
        if(user.hasStarted()) {
            StringBuilder to = new StringBuilder();
            
            if(instruccion.hasParametros()) {
                
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    if(paramYArg.getParametro().equalsIgnoreCase("-to")) {
                        to.append(paramYArg.getArgumento());
                    }
                });
                
            }
            
            if(instruccion.hasArgumentoDelComando()) {
                export(instruccion.getArgumentoDelComando(), to.toString());
                
            } else {
                dirList = new DirListAll(user);
                export(dirList.ask(), to.toString());
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void export(String ruta, String to) {
        Scanner in = new Scanner(System.in);
            
        ruta = pedirDatos("Exportar> ", ruta).toString();
        
        File exportar = new File(user.getEjecutandoseEnFile(), Ficheros.eliminarComillas(ruta));
        
        if(exportar.exists()) {
            
            to = pedirDatos("Exportar A> ", to).toString();
            File destino = new File(Ficheros.eliminarComillas(to));
            
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