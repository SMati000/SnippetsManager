package central_office;
import general.Main;
import java.io.File;
import java.util.Scanner;
public class InicioDelPrograma {
    private static volatile boolean ejecutar = true;
    
    public static void bienvenida() {
        System.out.println (
            "\n--------Creado por @JMati000.-------- "
            + "\n\n--------www.twitter.com/JMati000--------"
            + "\n\nBienvenido!, escribe 'help' sin las comillas para ver los comandos disponibles\n"
        );
    }
    
    public synchronized static void inicioDelPrograma() {
        Scanner in = new Scanner(System.in);
        
        System.out.print(Main.getUser().getEjecutandoseEn());
        String instruccion = in.nextLine().toLowerCase();
        
        Interpreter interpreter = new Interpreter(instruccion);
        interpreter.interpret();
        
        Redirect redirecter = new Redirect();
        redirecter.redirecter(interpreter.getInstrucciones());
        
        if(ejecutar) {
            inicioDelPrograma();
        }
    }
    
    public synchronized static void cambiarLugarDeEjecucionA(File lugarDeEjecucion) {
        ejecutar = false;
        
        Main.getUser().setEjecutandoseEn(lugarDeEjecucion);
        
        ejecutar = true;
    }
}
