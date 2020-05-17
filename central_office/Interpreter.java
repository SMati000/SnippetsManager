package central_office;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public final class Interpreter {
    private final String instruccion; // ej: add "path" -cat "cat"
    
    private String comando = "";
    private String argumentoDelComando;
    private Instruccion instrucciones; 
    
    public Interpreter(String instruccion) {
        this.instruccion = instruccion;
        
        if(!instruccion.split(" ")[0].startsWith("-")) { // solo los parametros empiezan con '-', los comando no
            comando = instruccion.split(" ")[0];
        }
        
        argumentoDelComando = null; // se inicializa en interpret()
    }

    public void interpret() {
        final int ARGUMENTO_DEL_COMANDO = 0; // vuelta 0 del ciclo, porque el comando y arg del comando siempre van primero
        List<ParametrosYArgumentos> paramsYArgs = new ArrayList<>();
        
                                              // comando "arg" || -param "arg" -> las comillas son opcionales en ambos casos
        Pattern checkSymbols = Pattern.compile("-?[A-Za-z]+([\\s][\"]?[\\w(\\d\\s.:+'/'\\\\)?]+[\"]?)?");
        Matcher parser = checkSymbols.matcher(this.instruccion);
        
        for(int i = 0; parser.find(); i++) {
            if(parser.group().length() != 0) {
                String found = parser.group().trim();
                
                String temp[] = dividirCadena(found);
                    
                temp[1] = quitarComillas(temp[1]);
                                                 // si temp[0] empezara con guion, seria el arg de un param, no del comando
                if(i == ARGUMENTO_DEL_COMANDO && !temp[0].startsWith("-")) {
                    argumentoDelComando = temp[1];
                } else {

                    if(!temp[0].equalsIgnoreCase(comando)) {
                        paramsYArgs.add(new ParametrosYArgumentos(temp[0], temp[1]));  
                    }

                }
            }
        }
        
        this.instrucciones = new Instruccion(comando, argumentoDelComando, paramsYArgs);
    }
    
    private String[] dividirCadena(String found) {
        String temp[] = new String[2];
        
        if(found.contains("\"")) { 
            temp[0] = found.substring(0, (found.indexOf("\"")-1));
            temp[1] = found.substring((found.indexOf("\"")+1));
        } else {
            temp = found.split("\\s\"?");
        }
        
        if(temp.length < 2) {
            String tempUno[] = new String[2];
            
            tempUno[0] = temp[0];
            tempUno[1] = null;
            
            temp = tempUno; // necesito que temp tenga indice 1 aunq sea null para 
        }                   // ahorrarme el manejo de excepciones en interpret()
        
        return temp;
    }
    
    private String quitarComillas(String cadena) {
        try {
            cadena = cadena.startsWith("\"") ? cadena.substring(1) : cadena;
            cadena = cadena.endsWith("\"") ? cadena.substring(0, cadena.length()-1) : cadena;
        } catch(NullPointerException e) {}
        
        return cadena;
    }
    
    public Instruccion getInstrucciones() {
        if(instrucciones != null) {
            return instrucciones;
        }
        return null;
    }
}
