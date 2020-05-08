package user;

import backend.Ficheros;
import general.Main;
import java.io.File;
import java.io.IOException;

public class Backup {
    public static File getBackupDir() {
        File miDir = null;
        
        try {
            miDir = new File(Ficheros.miDir.getCanonicalPath(), "Backup");
        } catch (IOException ex) {
            miDir = new File(Ficheros.miDir.getPath(), "Backup");
        }
        
        return miDir;
    }
    
    public static void backUp(File backupDir) {
        if(Main.getUser().hasStarted()) {
            File snippetsDb = new File(Main.getLog().leerDeLogTxt(1));
            
            if(!backupDir.exists()) {
                backupDir.mkdir();
            }
            
            backupDir = new File(backupDir.getAbsolutePath()+ "\\SnippetsDB"); 
            Ficheros.copiarTodo(snippetsDb, backupDir);
        } else { getBackupDir().mkdir(); }
    }
    
    public static void logBackUp() {
        Ficheros.CopiarFichero(new File(Main.getLog().getLogTxtLocation()), new File(getBackupDir(), "log.txt"));
    }
}
