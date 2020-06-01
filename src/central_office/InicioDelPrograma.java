package central_office;

import comandos.Comandos;
import java.io.File;
import java.util.Scanner;
import user.User;

public class InicioDelPrograma {
    private static volatile boolean ejecutar = true;
    private static User user;
    
    public static void bienvenida(User user) {
        setUser(user);
        
        System.out.println (
            "\n--------Creado por @JMati000.-------- "
            + "\n\n--------www.twitter.com/JMati000--------"
            + "\n\nBienvenido!, escribe 'help' sin las comillas para ver los comandos disponibles\n"
        );
        
        InicioDelPrograma.user.login();
    }
    
    public synchronized static void inicioDelPrograma() {
        Scanner in = new Scanner(System.in);
        
        System.out.print(user.getEjecutandoseEn());
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
        
        user.setEjecutandoseEn(lugarDeEjecucion);
        
        ejecutar = true;
    }
    
    public static void setUser(User user) {
        InicioDelPrograma.user = user;
        Comandos.setUser(user);
    }
}
