package comandos;

import backend.Ficheros;
import central_office.Configurations.Configurations;
import static central_office.Configurations.SystemConfigDTO.DEFAULTDIRKEY;
import central_office.InicioDelPrograma;
import central_office.Instruccion;
import general.Main;
import java.io.File;

public class Cd extends Comandos implements Redirecter {

    public Cd(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    public void redirecter() {
        if(instruccion.hasParametro("-help")) {
            help();
            return;
        }
        
        if(Main.getUser().hasStarted()) {
            if(instruccion.hasParametros()) {
                System.out.println(NO_NECESITO_PARAMETROS);
            } if(!instruccion.hasArgumentoDelComando()) {
                String currentLoc = Main.getUser().getEjecutandoseEn().substring(1, (Main.getUser().getEjecutandoseEn().length()-2));
                
                System.out.println("Te encuentras en: " + currentLoc);
            } else {
                cd(instruccion.getArgumentoDelComando());
            }
        } else {
            System.out.println(DEBE_INICIAR_MSG);
        }
    }
    
    private void cd(String arg) {
        switch(arg) {
            case "..":
                goBack();
                break;
            case "\\":
                goHome();
                break;
            default:
                goTo(new File(Main.getUser().getEjecutandoseEnFile(), Ficheros.eliminarComillas(arg)));
                break;
        }
    }
    
    private void goTo(File goTo) {
        if(goTo.exists() && goTo.isDirectory()) {
            InicioDelPrograma.cambiarLugarDeEjecucionA(goTo);
        } else {
            System.out.println("El Directorio [" + goTo.getAbsolutePath() + "] no existe.");
        }
    }
    
    private void goBack() {
        if(Main.getUser().getEjecutandoseEnFile().getName().equals("SnippetsDB")) {
            return;
        }
        
        InicioDelPrograma.cambiarLugarDeEjecucionA(Main.getUser().getEjecutandoseEnFile().getParentFile());
    }
    
    private void goHome() {
        InicioDelPrograma.cambiarLugarDeEjecucionA(new File(Configurations.SYSTEMCONFIG.readVariable(DEFAULTDIRKEY).toString()));
    }
    
    @Override
    public void help() {
        System.out.println("\n____________________________________________________________________________\n"
                    + "cd: te permite navegar en la SnippetsDB\n"
                    + "    cd: muestra tu ubicacion actual\n"
                    + "    cd \"categoria\\subcat\": accede a esa carpeta\n"
                    + "    cd .. retrocede una carpeta\n"
                    + "    cd \\ retrocede al inicio\n"
                    + "* ADVERTENCIA: TODOS LOS COMANDOS SE EJECUTAN EN LA UBICACION EN LA QUE ESTE. EXCEPTO AQUELLOS COMANDOS QUE\n"
                    + "SON GENERALES COMO show, recover, backup, etc.\n"
                    + "____________________________________________________________________________");
    }
    
}
