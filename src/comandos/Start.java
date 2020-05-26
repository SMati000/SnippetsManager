package comandos;

import backend.Ficheros;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTEDITORKEY;
import central_office.Instruccion;
import central_office.SnippetsDb;
import general.Main;
import java.io.File;
import java.util.Scanner;

public class Start extends Comandos implements Redirecter {
    private final boolean hasStarted;
    
    public Start(Instruccion instruccion) {
        super(instruccion);
        this.hasStarted = Main.getUser().hasStarted();
    }

    @Override
    public void redirecter() {
        if(this.instruccion.hasArgumentoDelComando()) {
            
            System.out.println(NO_NECESITO_ARGUMENTO_DEL_COMANDO);
            help();
            
        } else {
            
            if(this.instruccion.hasParametro("-help")) {
                help();
                return;
            }
            
            if(hasStarted) {
                
                if(this.instruccion.hasParametros()) {
                    
                    this.instruccion.getParametrosYArgumentos().forEach(
                        objeto -> {
                            
                            switch(objeto.getParametro()) {
                                case "-newpath":
                                    SnippetsDb.defaultSnippetsDb().mover(objeto.getArgumento());
                                    break;
                                case "-newpassword":
                                    Main.getUser().signIn();
                                    break;
                                default:
                                    System.out.println("El parametro " + objeto.getParametro() + " no existe");
                                    break;                                    
                            }
                            
                        }
                    );
                    
                } else {
                    System.out.println("\n____________________________________________________________________________\n"
                            + "* No puedes iniciar dos veces en el programa.\n"
                            + "* Si deseas hacer un backup, use el comando 'backup'.\n"
                            + "* Le recomendamos que vea la ayuda de estos comandos antes de utilizarlos para prevenir posibles errores.\n"
                            + "____________________________________________________________________________");
                }
                
            } else {
                Configurations.SYSTEMCONFIG.addVariable(DEFAULTEDITORKEY, Vim.VIM.getAbsolutePath());
                
                Main.getUser().signIn();
                
                Scanner in = new Scanner(System.in);
                
                System.out.print("Especifique la ruta donde almacenar los Snippets\n  > ");
                SnippetsDb snippetsDb = new SnippetsDb(new File(Ficheros.eliminarComillas(in.nextLine())));
                
                snippetsDb.crear();
            }
            
        }
    }
    
    
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "start: este comando le permite iniciar en el programa y especificar los datos necesarios. \n"
                + "    -newpassword: le permite cambiar o establecer una contrasena\n"
                + "    -newpath: le permite cambiar la ruta de almacenamiento (ESTE COMANDO MOVERA TODO A LA NUEVA RUTA)\n"
                + "* SOLO SE USA LA PRIMERA VEZ QUE INGRESE AL PROGRAMA.\n"
                + "* NECESARIO PARA PODER USAR MUCHAS FUNCIONES!!\n"
                + "____________________________________________________________________________");
    }
}
