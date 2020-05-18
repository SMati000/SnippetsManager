package central_office;
import java.util.List;
public class Instruccion {
    private final String comando;
    private final String argumentoDelComando;
    private final List<ParametrosYArgumentos> paramsYArgs;
    
    public Instruccion(String comando, String argumentoDelComando, List<ParametrosYArgumentos> paramsYArgs) {
        this.comando = comando;
        this.argumentoDelComando = argumentoDelComando;
        this.paramsYArgs = paramsYArgs;
    }
    
    public String getComando() {
        return this.comando;
    }
    
    public String getArgumentoDelComando() {
        return this.argumentoDelComando;
    }
    
    public boolean hasArgumentoDelComando() {
        return this.argumentoDelComando != null;
    }
    
    public boolean hasParametros() {
        return !this.paramsYArgs.isEmpty();
    }
    
    private boolean toReturn;
    public boolean hasParametro(String parametro) {
        toReturn = false;
        
        this.paramsYArgs.forEach(objeto -> {
                if(objeto.getParametro().equalsIgnoreCase(parametro)) {
                    toReturn = true;
                }
            }
        );
        
        return toReturn;
    }
    
    @Override
    public String toString() {
        try {
            return (this.comando + ", " + this.argumentoDelComando + ", "+ paramsYArgs.toString());
        } catch(NullPointerException e) {
            return null;
        }
    }
    
    public List<ParametrosYArgumentos> getParametrosYArgumentos() {
        return this.paramsYArgs;
    }
}
