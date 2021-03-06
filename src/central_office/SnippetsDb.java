package central_office;

import backend.Ficheros;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SnippetsDb {
    
    private static final String NOMBRE = "SnippetsDB";
    private File ubicacion;
    private File DIRECTORIOPRINCIPAL;
    
    public SnippetsDb(final File UBICACION) {
        this.ubicacion = UBICACION;
        DIRECTORIOPRINCIPAL = new File(this.ubicacion, NOMBRE);
    }
    
    public void crear() {
        if(this.ubicacion.exists() && this.ubicacion.isDirectory()) {
            System.out.println("Se almacenaran los archivos en: " + this.toString());
        } else {
            
            try {
                this.ubicacion = new File(Ficheros.miDir.getCanonicalPath());
            } catch (IOException ex) {}
            
            DIRECTORIOPRINCIPAL = new File(this.ubicacion, NOMBRE);
            
            System.out.println("La ruta ingresada no es un directorio existente\n"
                    + "Se almacenaran los archivos en: " + this.toString() + "\n"
                    + "Si desea cambiar este directorio, use el comando -new /path 'ruta'- (sin guiones)\n");
        }
        
        final File CATEGORIAPRINCIPAL = new File(DIRECTORIOPRINCIPAL, "Default");

        Ficheros.crearDirectorios(DIRECTORIOPRINCIPAL, true, Configurations.SYSTEMCONFIG, DEFAULTDIRKEY);
        Ficheros.crearDirectorios(CATEGORIAPRINCIPAL, false, Configurations.SYSTEMCONFIG, "");
    }
    
    public void mover(String moverA) {
        try {
            
            if(moverA == null || moverA.isEmpty()) {
                System.out.print("Nueva ruta> ");

                Scanner in = new Scanner(System.in);
                moverA = in.nextLine();
            }
            
            File vieja = new File(ubicacion, NOMBRE); // ruta vieja
            
            File nuevaUbi = new File(Ficheros.eliminarComillas(moverA));
            
            if(nuevaUbi.exists() && nuevaUbi.isDirectory()) {
                ubicacion = nuevaUbi;
                DIRECTORIOPRINCIPAL = new File(ubicacion, NOMBRE);
            
                Ficheros.copiarTodo(vieja, ubicacion);
                Ficheros.deleteAll(vieja);
                Configurations.SYSTEMCONFIG.addOrUpdateVariable(DEFAULTDIRKEY, new File(ubicacion, NOMBRE).getCanonicalPath());
                
                System.out.println("Movido con exito!");
            } else {
                System.out.println("La ruta ingresada no es un directorio existente.");
            }
        } catch (Exception ex) {}
    }
    
    public void comprobarLugarDeAlmacenamiento() {
        if(!DIRECTORIOPRINCIPAL.exists()) {
            System.out.println("--------------------------------------------------------------------------------\n"
                    + "La ruta \"" + DIRECTORIOPRINCIPAL.getAbsolutePath() + "\" no se encuentra disponible,\n"
                    + "es posible que la haya eliminado involuntariamente, revise su papelera de reciclaje,\n"
                    + "o utilice los comandos que provee el programa para estas situaciones,\n"
                    + "e intente restaurarla y reiniciar el programa.\n"
                    + "De lo contrario, establesca la nueva ruta con el comando 'start -crearsnippetsdb' SIN COMILLAS.\n"
                    + "-----------------------------------------------------------------------------------------\n");
            
        }
        
        if(!new File(DIRECTORIOPRINCIPAL, "default").exists()) {
            new File(DIRECTORIOPRINCIPAL, "default").mkdir();
        }
    }
    
    @Override
    public String toString() {
        if(this.DIRECTORIOPRINCIPAL != null) {
            return this.DIRECTORIOPRINCIPAL.getAbsolutePath();
        }
        return "";
    }
    
    public static SnippetsDb defaultSnippetsDb() {
        Object dir = Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY);
        
        if(dir != null) {
            return (new SnippetsDb(new File(dir.toString()).getParentFile()));
        }
        return null;
    }
}