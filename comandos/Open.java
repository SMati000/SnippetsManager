package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import general.Main;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Open extends Comandos implements Redirecter {

    public Open(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(this.instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            
            if(this.instruccion.hasParametros()) {
                System.out.println(NO_NECESITO_PARAMETROS);
            } else {
                open(this.instruccion.getArgumentoDelComando());
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    public void open(String file) {
        
        if(file == null) {
            System.out.print("Abrir> ");

            Scanner in = new Scanner(System.in);
            file = in.nextLine();
        }
        
        file = Ficheros.eliminarComillas(file);
                
        file = Ficheros.agregarExtension(file, ".txt");

        File snippet = new File(Main.getUser().getEjecutandoseEnFile(), file);

        if(snippet.exists() && snippet.isFile()) {
            try {
                 Desktop.getDesktop().open(snippet);
            } catch(IOException e) { 
                System.out.println("Ha habido un error :( | " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe");
        }
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "open \"categoria/snippet.txt\": este comando abrira\n"
                + "el snippet indicado para que pueda verlo y editarlo\n"
                + "____________________________________________________________________________");
    }
    
}
