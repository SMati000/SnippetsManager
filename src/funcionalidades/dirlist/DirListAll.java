package funcionalidades.dirlist;

import general.Main;
import java.io.File;

public class DirListAll implements DirList {
    @Override
    public void show() {
       String[] rutas = Main.getUser().getEjecutandoseEnFile().list();
        
        for (int i = 0; i < rutas.length; i++) {
            if (new File(Main.getUser().getEjecutandoseEnFile(), rutas[i]).isDirectory()) {
                System.out.println(i + "> " + rutas[i]);
            } else {
                System.out.println(i + ". " + rutas[i]);
            }
            DIRS.add(rutas[i]);
        }
    }
}
