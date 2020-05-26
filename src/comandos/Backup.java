package comandos;

import backend.Ficheros;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import central_office.Instruccion;
import general.Main;
import java.io.File;
import java.util.Scanner;

public class Backup extends Comandos implements Redirecter {

    public Backup(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            
            if(instruccion.hasArgumentoDelComando() && instruccion.hasParametros()) {
                System.out.println("Error de sintaxis, este comando no permite utilizar\n"
                        + "argumento para el comando y parametros. Porque tienen efectos opuestos");
                return;
            }
            
            if(instruccion.hasArgumentoDelComando()) {
                doBackup(instruccion.getArgumentoDelComando());
            }
            
            if(instruccion.hasParametros()) {
                
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    
                    if(paramYArg.getParametro().equals("-restore")) {
                        restoreBackup(paramYArg.getArgumento());
                    } else {
                        System.out.println("Parametro incorrecto");
                    }
                    
                });
            
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void doBackup(String to) {
        to = pedirDatos("Crear el backup en> ", to).toString();
        
        if(to.contains(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString())) {
            System.out.println("No puede hacer un backup dentro de la propia Snippets Db");
            return;
        }
        
        File backupDir = new File(Ficheros.eliminarComillas(to));
            
        if(backupDir.exists() && backupDir.isDirectory()) {
            backupDir = new File(backupDir, "Backup");
            user.Backup.backUp(backupDir);
            System.out.println("Creado con Exito!");
        } else {
            System.out.println("Ese directorio no existe. Reviselo con cuidado y vuelva a intentarlo");
        }
    }
    
    private void restoreBackup(String restoreFrom) {
        if(restoreFrom == null || restoreFrom.isEmpty()) {
            Scanner in = new Scanner(System.in);
            
            System.out.print("Restaurar a> ");
            restoreFrom = in.nextLine();
        }
        
        restoreFrom = Ficheros.eliminarComillas(restoreFrom);
        File backupLocation = new File(restoreFrom, "SnippetsDB");
        
        if(backupLocation.exists()) {
            
            File restoreTo = new File(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString());
            
            if(!restoreTo.exists()) {
                restoreTo.mkdir();
            }
            
            Ficheros.copiarTodo(backupLocation, restoreTo);
            System.out.println("Listo!");
        } else {
            System.out.println("En la ubicacion ingresada no se encuentra el directorio 'Snippets DB' de donde\n"
                    + "el programa debe restaurar los archivos.");
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                    + "backup \"..\\ubicacion donde guardar\": Creara un backup en la ubicacion especificada\n"
                    + "    -restore \"ubicacion del backup hecho\": en caso de tener algun problema con la carpeta original,\n"
                    + "     puede restaurar su backup para usarlo como carpeta original. -> Ej: backup -restore \"ubicacion\\backup\"\n"
                + "____________________________________________________________________________");
    }
}
