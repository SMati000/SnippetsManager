package user;

import backend.Ficheros;
import general.Main;
import java.io.File;
import java.io.IOException;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;

public class Backup {
    public static File getBackupDir() {
        File miDir = new File(".", "Backup");
        
        return miDir;
    }
    
    public static void backUp(File backupDir) {
        if(Main.getUser().hasStarted()) {
            File snippetsDb = new File(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString());
            
            if(!backupDir.exists()) {
                backupDir.mkdir();
            }
            
            backupDir = new File(backupDir.getAbsolutePath()+ "\\SnippetsDB"); 
            Ficheros.copiarTodo(snippetsDb, backupDir);
        } else { getBackupDir().mkdir(); }
    }
    
    public static void logBackUp() {
        Ficheros.CopiarFichero(new File(Configurations.SYSTEMCONFIGFILE.getAbsolutePath()), new File(getBackupDir(), "log.txt"));
    }
}
