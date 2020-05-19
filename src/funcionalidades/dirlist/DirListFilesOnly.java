package funcionalidades.dirlist;

import static funcionalidades.dirlist.DirList.DIRS;
import general.Main;
import java.io.File;

public class DirListFilesOnly implements DirList {
    @Override
    public void show() {
       String[] rutas = Main.getUser().getEjecutandoseEnFile().list();
        
        for (int i = 0; i < rutas.length; i++) {
            if (new File(Main.getUser().getEjecutandoseEnFile(), rutas[i]).isFile()) {
                System.out.println(i + ". " + rutas[i]);
                DIRS.add(rutas[i]);
            }
        }
    }
}
