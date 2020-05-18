package funcionalidades.dirlist;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public interface DirList {
    List<String> DIRS = new ArrayList();
    
    void show();
    
    default String ask() {
        DIRS.clear();
        
        Scanner in = new Scanner(System.in);
        int indice;
        
        show();
        
        if(DIRS.isEmpty()) {
            return "";
        }
        
        System.out.print("Indice> ");
        
        try {
            indice = in.nextInt();
            return DIRS.get(indice);
        } catch(InputMismatchException | IndexOutOfBoundsException e) {
            return "";
        }
    }
}
