package funcionalidades.dirlist;

import static funcionalidades.dirlist.DirList.DIRS;
import general.Main;
import java.io.File;
import user.User;

public class DirListFilesOnly implements DirList {
    private final User user;
    
    public DirListFilesOnly(User user) {
        this.user = user;
    }
    
    @Override
    public void show() {
       String[] rutas = user.getEjecutandoseEnFile().list();
        
        for (int i = 0; i < rutas.length; i++) {
            if (new File(user.getEjecutandoseEnFile(), rutas[i]).isFile()) {
                System.out.println(i + ". " + rutas[i]);
                DIRS.add(rutas[i]);
            }
        }
    }
}
