package comandos;

import backend.Ficheros;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import central_office.Instruccion;
import central_office.ParametrosYArgumentos;
import central_office.Redirect;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import sun.security.krb5.Confounder;

public class Add extends Comandos implements Redirecter {
    private File snippetAAgregar;
    private boolean associateFiles = false;
    
    public Add(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        setSnippetAAgregar();
        
        if(user.hasStarted()) {
            final StringBuilder category = new StringBuilder();
            
            instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                if(paramYArg.getParametro().equals("-category")) {
                    category.append(paramYArg.getArgumento());
                } else if(paramYArg.getParametro().equals("-af")) {
                    associateFiles = true;
                }
            });
            
            add(getSnippetName(), category.toString());
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
        
    }

    private void setSnippetAAgregar() {
        try {
            snippetAAgregar = new File(instruccion.getArgumentoDelComando());
        } catch(Exception e) {
            snippetAAgregar = new File("");
        }
    }
    
    private void add(String name, String category) {
        
        category = Ficheros.eliminarComillas(category); 
        
        if(snippetAAgregar.exists()) {
            
            File agregarA = new File(user.getEjecutandoseEnFile(), category);
            
            File snippetsDb = new File(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString());
            
            if(agregarA.equals(snippetsDb)) {
                agregarA = new File(agregarA, "default");
            }
            
            if(agregarA.exists()) {
                
                Ficheros.CopiarFichero(snippetAAgregar, new File(agregarA, name));
                Ficheros.deleteFile("Eliminar archivo original", snippetAAgregar);
                
                associateFiles(agregarA.getAbsolutePath().replace(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString() + "\\", "") + "\\" + name);
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
    
    public void associateFiles(String snippet) {
        if(associateFiles) {
            Redirect redirecter = new Redirect();
        
            ArrayList<ParametrosYArgumentos> temp = new ArrayList();
            temp.add(new ParametrosYArgumentos("-add", null));
            
            redirecter.redirecter(new Instruccion("link", snippet, temp));
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                    + "add \"...\\ubicacion.extencion\": Agregar un snipett\n"
                    + "    -category \"name\": agrega el snippet a esa categoria. -> Ej: add \"snippet.txt\" -category \"categoria\\subcategoria\"\n"
                    + "     -af: permite asocar archivos al snippet agregado"
                    + "* Si no se especifica categoria, el snippet se agrega a la categoria \"default\".\n"
                    + "* CUANDO CUALQUIERA DE LOS NOMBRES LLEVAN ESPACIOS, DEBE PONER COMILLAS COMO SE MUESTRA EN EL EJEMPLO\n"
                    + "____________________________________________________________________________");
    }
}