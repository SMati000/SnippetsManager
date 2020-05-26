package comandos;

import backend.Ficheros;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import java.io.File;
import central_office.Instruccion;
import general.Main;

public class Info extends Comandos implements Redirecter {

    public Info(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            
            if(instruccion.hasParametros() || instruccion.hasArgumentoDelComando()) {
                System.out.println(NO_NECESITO_NINGUN_PARAMETRO_EXTRA);
            } else {
                info();
            }
            
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void info() {
        System.out.println("------------------------------------------------------");
        
        System.out.println (
            "--------Creado por @JMati000.-------- "
            + "\n--------Soporte a traves de twitter:-------\n"
            + "---------www.twitter.com/JMati000---------\n"
        );

        File snippetsDB = new File(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString());
        
        if(snippetsDB.exists()) {
            System.out.println("Snippets DB: " + snippetsDB.getAbsolutePath());
            
            Ficheros.contarArchivos(snippetsDB);
            System.out.println("Snippets totales: " + Ficheros.getArchivos());
        } else {
            System.out.println("Snippets DB: no encontrado");
        }
        
        System.out.println("Editor por defecto: " + Main.getUser().getDefaultProgram());

        System.out.println("------------------------------------------------------");
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                + "info: muestra informacion general\n"
                + "____________________________________________________________________________");
    }
    
}
