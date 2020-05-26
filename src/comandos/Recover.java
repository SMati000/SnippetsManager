package comandos;

import backend.Ficheros;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import central_office.Instruccion;
import general.Main;
import java.io.File;
import java.io.IOException;

public class Recover extends Comandos implements Redirecter {

    public Recover(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            if(instruccion.hasArgumentoDelComando() || instruccion.hasParametros()) {
                System.out.println(NO_NECESITO_NINGUN_PARAMETRO_EXTRA);
            } else {
                recover();
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void recover() {
        File restoreTo = new File(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString());
        File restoreFrom;
        
        try {
            restoreFrom = new File(Ficheros.miDir.getCanonicalPath(), "Backup\\SnippetsDB");
        } catch (IOException ex) { 
            restoreFrom = new File(Ficheros.miDir.getPath(), "Backup\\SnippetsDB");
        }
        
        if(!restoreTo.exists()) {
            restoreTo.mkdir();
        }
        
        Ficheros.copiarTodo(restoreFrom, restoreTo);
        System.out.println("Listo!");
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "recover: en caso de tener algun problema con la carpeta original y no tener backups recientes,\n"
                + "         puede usar este comando y el programa automaticamente restaurara lo que pueda\n"
                + "____________________________________________________________________________\n");
    }
}
