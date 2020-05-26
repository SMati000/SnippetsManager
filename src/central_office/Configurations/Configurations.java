package central_office.Configurations;

import backend.config.Config;
import java.io.File;
import java.io.IOException;

public final class Configurations {
    protected static final File CONFIG = new File(".", "configs");
    public static final File SYSTEMCONFIGFILE = new File(CONFIG, "system.properties");
    protected static final File ASSOCIATEDFILES = new File(CONFIG, "associated Files");
    
    public static Config SYSTEMCONFIG;
    
    public static void setUp() {
        try {
            CONFIG.mkdir();
            ASSOCIATEDFILES.mkdir();
            SYSTEMCONFIGFILE.createNewFile();
            
            SYSTEMCONFIG = new Config(SYSTEMCONFIGFILE);
        } catch (IOException ex) {}
    }
    
    public static Config getAssociatedFile(String configArchivo) { 
        return new Config(new File(ASSOCIATEDFILES, configArchivo)); 
    }
}
