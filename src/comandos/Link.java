package comandos;

import backend.config.Config;
import backend.Ficheros;
import central_office.Configurations.Configurations;
import central_office.Instruccion;
import funcionalidades.ArchivosAsociados;
import general.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Link extends Comandos implements Redirecter {
    private List<File> associatedFiles;
    
    public Link(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        associatedFiles = new ArrayList<>();
        
        if(instruccion.hasParametros()) {
            if(instruccion.getParametrosYArgumentos().size() == 1) {
                String parametro = instruccion.getParametrosYArgumentos().get(0).getParametro();
            
                link(instruccion.getArgumentoDelComando(), parametro);
            } else {
                System.out.println("Solo necesito un parametro");
            }
        } else {
            String parametro = getParametro();
            
            if(parametro != null) {
                link(instruccion.getArgumentoDelComando(), parametro);
            }
        }
    }
    
    private String getParametro() {
        Scanner in = new Scanner(System.in);
            
        System.out.print("1. para asociar nuevos archivos\n"
                + "2. para eliminar asociaciones\n"
                + "> ");

        switch(in.nextLine()) {
            case "1":
                return "-add";
            case "2":
                return "-delete";
            default:
                System.out.println("Opcion invalida.");
                return null;
        }
    }
    
    private void link(String snippet, String parametro) {
        snippet = pedirDatos("Snippet> ", snippet).toString();
        snippet = Ficheros.eliminarComillas(snippet);
        
        File snippetToLink = new File(Main.getUser().getEjecutandoseEnFile(), snippet);
        if(snippetToLink.isFile()) {
            
            pedirArchivosAsociados();
            
            if(parametro.equalsIgnoreCase("-add")) {
                add(snippetToLink);
            } else if(parametro.equalsIgnoreCase("-delete")) {
                delete(snippetToLink);
            }
            
        }
    }
    
    private void pedirArchivosAsociados() {
        Scanner in = new Scanner(System.in);
        boolean continuar = true;
        
        System.out.println("Escriba 'e' para terminar");
        while(continuar) {
            System.out.print("Archivo> ");
            String archivo = in.nextLine();
            
            if(archivo.equalsIgnoreCase("e")) {
                continuar = false;
            } else {
                añadirALaLista(new File(archivo));
            }
        }
    }
    
    private void añadirALaLista(File archivo) {
        if(archivo.exists()) {
            associatedFiles.add(archivo);
        } else {
            System.out.println("Archivo inexistente :/");
        }
    }
    
    private void add(File snippet) {
        Object[] temp = associatedFiles.toArray();
        String[] añadir = new String[temp.length];
        
        for(int i = 0; i < temp.length; i++) {
            añadir[i] = temp[i].toString();
        }
        
        Config config = Configurations.getAssociatedFile(ArchivosAsociados.getConfigFileName(snippet));
        
        if(añadir.length != 0) {
            config.addVariable(snippet.getName(), añadir);
        }
    }
    
    private void delete(File snippet) {
        Config config = Configurations.getAssociatedFile(ArchivosAsociados.getConfigFileName(snippet));
        
        if(config.readVariable(snippet.getName()) != null) {
            Object[] registered = config.readVariableArray(snippet.getName());
            
            config.removeVariable(snippet.getName());
            
            for(Object r : registered) {
                if(!associatedFiles.contains(new File(r.toString())) && !r.toString().isEmpty()) {
                    config.addVariable(snippet.getName(), r.toString());
                }
            }
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "link \"snippet.extension\": permite vincular o desvincular archivos a un snippet\n"
                + "     -add: especificas que quieres asociar nuevos archivos a un snippet\n"
                + "     -delete: especificas que quieres eliminar archivos asociados a un snippet\n"
                + "____________________________________________________________________________");
    }
}
