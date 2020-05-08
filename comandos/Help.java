package comandos;

import backend.Ficheros;
import central_office.Instruccion;
import central_office.ParametrosYArgumentos;
import central_office.Redirect;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Help extends Comandos implements Redirecter {

    public Help(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(instruccion.hasArgumentoDelComando()) {
            System.out.println(NO_NECESITO_ARGUMENTO_DEL_COMANDO);
        } else if(instruccion.hasParametros()) {
            
            if(instruccion.hasParametro("-more")) {
                
                helpWithMore();
                
            } else {
                
                instruccion.getParametrosYArgumentos().forEach(paramYArg -> {
                    commandHelp(paramYArg.getParametro());
                });
                
            }
            
        } else {
            allHelp();
        }
    }
    
    public void allHelp() {
        File help = new File(Ficheros.miDir, "recursos\\help\\help.txt");
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(help));
            String line = "";
            while(line != null) {
                line = br.readLine();
                if(line != null) {
                    System.out.println(line);
                }
            }
            br.close();
        } catch(IOException e) {
            System.out.println("Lo sentimos, ha habido un error.");
        }
    }
    
    public synchronized void helpWithMore() {
        
        File help = new File(Ficheros.miDir, "recursos\\help\\help.txt");
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(help));
            String line = "";
            
            for(int i = 0; line != null; i++) {
                line = br.readLine();
                if(line != null) {
                    
                    if(i < 10) {
                        
                        System.out.println(line);
                        
                    } else {

                        try {
                            new ProcessBuilder("cmd", "/c", "pause>null").inheritIO().start().waitFor();
                        } catch (IOException | InterruptedException ex) {
                            Scanner in = new Scanner(System.in);
                            in.nextLine();
                        }
                        
                        i = 0;
                        
                    }
                    
                }
            }
            br.close();
        } catch(IOException e) {
            System.out.println("Lo sentimos, ha habido un error.");
        }
    }
    
    public void commandHelp(String comando) {
        Redirect redirecter = new Redirect();
        
        List<ParametrosYArgumentos> helpParam = new ArrayList<>();
        helpParam.add(new ParametrosYArgumentos("-help", null));
                                                // saca el guion
        redirecter.redirecter(new Instruccion(comando.substring(1), "", helpParam));
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "help: muestra ayuda sobre todos los comandos del programa\n"
                + "     -more: muestra la ayuda general por partes"
                + "     -comando: muestra ayuda sobre ese comando en especifico\n"
                + "* Puede enlistar en forma de parametros individuales tantos comandos como quiera\n"
                + "____________________________________________________________________________");
    }
}
