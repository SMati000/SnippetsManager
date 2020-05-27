package funcionalidades;

import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import java.io.File;

public final class ArchivosAsociados {
    
    public static String getConfigFileName(File snippetLoc) {
        String nombre = snippetLoc.getParent()
                .substring(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString().length()+1)
                .replace("\\", "-");
        
        if(!nombre.endsWith(".properties")) {
            nombre += ".properties";
        }
        
        return nombre;
    }
}
