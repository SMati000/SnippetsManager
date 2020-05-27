package comandos;

import backend.Ficheros;
import backend.config.Config;
import central_office.Configurations.Configurations;
import central_office.Instruccion;
import funcionalidades.ArchivosAsociados;
import funcionalidades.dirlist.DirList;
import funcionalidades.dirlist.DirListFilesOnly;
import general.Main;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AF extends Comandos implements Redirecter { // la clase se llama AF de AssociatedFiles

    public AF(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(Main.getUser().hasStarted()) {
            String parametro;
            String argumento = null;
                    
            if(instruccion.hasParametros()) {
                argumento = instruccion.getParametrosYArgumentos().get(0).getArgumento();
                
                if(instruccion.getParametrosYArgumentos().size() == 1) {
                    parametro = instruccion.getParametrosYArgumentos().get(0).getParametro();
                } else {
                    System.out.println("Solo puede usar un parametro");
                    return;
                }
                
            } else {
                parametro = getParametro();
            }
            
            if(parametro != null) {
                switch(parametro) {
                    case "-list":
                        list(instruccion.getArgumentoDelComando());
                        break;
                    default:
                        System.out.println("Parametro inexistente :/");
                        break;
                }
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private String getParametro() {
        Scanner in = new Scanner(System.in);
            
        System.out.print("1. para listar los archivos asociados\n"
                + "> ");

        switch(in.nextLine()) {
            case "1":
                return "-list";
            default:
                System.out.println("Opcion invalida.");
                return null;
        }
    }
    
    private void list(String snippet) {
        if(snippet == null || snippet.isEmpty()) {
            DirList list = new DirListFilesOnly();
            snippet = list.ask();
        }
        
        snippet = pedirDatos("Snippet> ", snippet).toString();
        snippet = Ficheros.eliminarComillas(snippet);
        
        File snippetFile = new File(Main.getUser().getEjecutandoseEnFile(), snippet);
        
        if(snippetFile.isFile()) {
            
            Config config = Configurations.getAssociatedFile(ArchivosAsociados.getConfigFileName(snippetFile));
            String[] archivos = (String[]) config.readVariableArray(snippetFile.getName());
            
            listar(archivos);
            
            int indice = 0;
            
            while(indice != -1) { 
                indice = deseaAbrir(archivos);
                
                if(indice != -1) {
                    open(new File(archivos[indice]));
                }
            }
            
            System.out.println("");
            
        } else {
            System.out.println("El snippet [" + snippetFile.getAbsolutePath() + "] no existe");
        }
    }
    
    private void listar(String[] archivos) {
        System.out.println("______________________________________________________");
        for(int i = 0; i < archivos.length; i++) {
            final String name = new File(archivos[i]).getName();

            System.out.println(i + ". " + name + " : " + archivos[i]);
        }
        System.out.println("______________________________________________________");
    }
    
    private int deseaAbrir(String[] archivos) {
        Scanner in = new Scanner(System.in);
        int indice = -1;
            
        System.out.print("Si desea abrir alguno de los archivos, indique el indice correspondiente.\n"
                + "Si no, escriba '-1' para salir de este menu\n"
                + "> ");

        try {
            indice = in.nextInt();
        } catch(Exception e) {}

        if(indice > -1 && indice < archivos.length) {
            return indice;
        }
        
        return -1;
    }
    
    private void open(File archivo) {
        try {
            Desktop.getDesktop().open(archivo);
        } catch(IOException e) {
            System.out.println("Hemos tenido un error intentando abrir el archivo :/\n"
                    + "Intente de nuevo.\n");
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "af \"snippet.extension\": permite realizar acciones sobre los archivos asociados al snippet indicado\n"
                + "     -list: lista los archivos asociados y permite abrirlos\n"
                + "____________________________________________________________________________");
    }
}
