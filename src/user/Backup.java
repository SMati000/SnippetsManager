package user;

import backend.Ficheros;
import java.io.File;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;

public class Backup {
    public static File getBackupDir() {
        File miDir = new File(".", "Backup");
        
        return miDir;
    }
    
    public static void backUp(User user, File backupDir) {
        if(user.hasStarted()) {
            File snippetsDb = new File(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString());
            
            if(!backupDir.exists()) {
                backupDir.mkdir();
            }
            
            backupDir = new File(backupDir.getAbsolutePath()+ "\\SnippetsDB"); 
            Ficheros.copiarTodo(snippetsDb, backupDir);
            
//            configBackUp();
        } else { getBackupDir().mkdir(); }
    }
    
    private static void configBackUp() {
        Ficheros.copiarTodo(Configurations.CONFIG, new File(getBackupDir(), "configs"));
    }
}
