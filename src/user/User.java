package user;
import backend.Contraseña;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import static central_office.Configurations.SystemConfigDTO.DEFAULTEDITORKEY;
import central_office.InicioDelPrograma;
import central_office.SnippetsDb;
import java.io.Console;
import java.io.File;
import java.util.Scanner;
public final class User {
    private final Contraseña contraseña;
    private String ejecutandoseEn;
    private File ejecutandoseEnFile;
    private String defaultProgram;
    
    public User() {
        this.contraseña = new Contraseña();
        ejecutandoseEn = "\nSnippetsDB> ";
        
        if(hasStarted()) {
            ejecutandoseEnFile  = new File(SnippetsDb.defaultSnippetsDb().toString());
            defaultProgram = Configurations.SYSTEMCONFIG.readVariable(DEFAULTEDITORKEY).toString();
        }
    }
    
    public void signIn() {
        this.contraseña.setPassword(Configurations.SYSTEMCONFIG);
    }
    
    public void login() {        
        if(!this.hasStarted()) { 
            InicioDelPrograma.inicioDelPrograma();
        } else if(!contraseña.verifyPassword(Configurations.SYSTEMCONFIG, "-1")) {
            String password = "";
            System.out.println("Por seguridad, puede ser que la contraseña no se vea en pantalla");
            Console console = System.console();

            if (console == null) {
                Scanner in = new Scanner(System.in);
                System.out.print("Contraseña> ");
                password = in.nextLine();   
            } else {
                char[] passwordArray = console.readPassword("Contraseña> ");
                password = new String(passwordArray);
            }

            if(contraseña.verifyPassword(Configurations.SYSTEMCONFIG, password)) {
                SnippetsDb snippetsDb = SnippetsDb.defaultSnippetsDb();
                snippetsDb.comprobarLugarDeAlmacenamiento();
                Backup.backUp(this, Backup.getBackupDir());
                InicioDelPrograma.inicioDelPrograma();
            } else {
                System.out.println("Contraseña incorrecta, intente de nuevo");
                login();
            }
        } else {
            SnippetsDb snippetsDb = SnippetsDb.defaultSnippetsDb();
            snippetsDb.comprobarLugarDeAlmacenamiento();
            Backup.backUp(this, Backup.getBackupDir());
            InicioDelPrograma.inicioDelPrograma();
        }
    }
    
    public boolean hasStarted() { 
        return Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY) != null;
    }
    
    public String getEjecutandoseEn() {
        return this.ejecutandoseEn;
    }
    
    public void setEjecutandoseEn(File ejecutandoseEn) {
        this.ejecutandoseEnFile = ejecutandoseEn;
        
        String temp = ejecutandoseEn.getAbsolutePath();
        this.ejecutandoseEn = temp.substring(temp.indexOf("SnippetsDB"), temp.length()) + "> ";
    }
    
    public void setDefaultProgram(String defaultProgram) {
        this.defaultProgram = defaultProgram;
    }
    
    public File getEjecutandoseEnFile() {
        return this.ejecutandoseEnFile;
    }
    
    public String getDefaultProgram() {
        return this.defaultProgram;
    }
}
