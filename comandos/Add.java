package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import general.Main;
import java.io.File;
import java.util.Scanner;

public class Add extends Comandos implements Redirecter {
    private File snippetAAgregar;
    
    public Add(Instruccion instruccion) {
        super(instruccion);
        
        setSnippetAAgregar();
    }

    private void setSnippetAAgregar() {
        try {
            snippetAAgregar = new File(instruccion.getArgumentoDelComando());
        } catch(Exception e) {
            snippetAAgregar = null;
        }
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            final StringBuilder category = new StringBuilder();
            
            instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                if(paramYArg.getParametro().equals("-category")) {
                    category.append(paramYArg.getArgumento());
                }
            });
            
            add(getSnippetName(), category.toString());
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
        
    }
    
    private void add(String name, String category) {
        
        category = Ficheros.eliminarComillas(category); 
        
        if(snippetAAgregar.exists()) {
            
            File agregarA = new File(Main.getUser().getEjecutandoseEnFile(), category);
            
            File snippetsDb = new File(Main.getLog().leerDeLogTxt(1));
            
            if(agregarA.equals(snippetsDb)) {
                agregarA = new File(agregarA, "default");
            }
            
            if(agregarA.exists()) {
                
                Ficheros.CopiarFichero(snippetAAgregar, new File(agregarA, name));
                Ficheros.deleteFile("Eliminar archivo original", snippetAAgregar);
                
            } else {
                System.out.println("No encontramos la ubicacion donde quiere agregar el archivo");
            }
            
        } else {
            System.out.println("El snippet indicado no existe");
        }
    }
    
    private String getSnippetName() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Nombre del snippet (si no escribes nada seguira teniendo el mismo nombre)> ");
        String name = in.nextLine();
        
        if (name.isEmpty()) {
            name = snippetAAgregar.getName();
        } else {
            name = Ficheros.eliminarComillas(name);
            name = Ficheros.agregarExtension(name, ".txt");
        }
        
        return name;
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                    + "add \"...\\ubicacion.txt\": Agregar un snipett\n"
                    + "    -category \"name\": agrega el snippet a esa categoria. -> Ej: add \"snippet.txt\" -category \"categoria\\subcategoria\"\n"
                    + "* Si no se especifica categoria, el snippet se agrega a la categoria \"default\".\n"
                    + "* CUANDO CUALQUIERA DE LOS NOMBRES LLEVAN ESPACIOS, DEBE PONER COMILLAS COMO SE MUESTRA EN EL EJEMPLO\n"
                    + "____________________________________________________________________________");
    }
}
