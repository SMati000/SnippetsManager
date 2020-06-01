package funcionalidades.dirlist;

import java.io.File;
import user.User;

public class DirListAll implements DirList {
    private final User user;
    
    public DirListAll(User user) {
        this.user = user;
    }
    
    @Override
    public void show() {
       String[] rutas = user.getEjecutandoseEnFile().list();
        
        for (int i = 0; i < rutas.length; i++) {
            if (new File(user.getEjecutandoseEnFile(), rutas[i]).isDirectory()) {
                System.out.println(i + "> " + rutas[i]);
            } else {
                System.out.println(i + ". " + rutas[i]);
            }
            DIRS.add(rutas[i]);
        }
    }
}
