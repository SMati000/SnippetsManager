package central_office;
public final class ParametrosYArgumentos {
    private final String parametro;
    private final String argumento;
    
    public ParametrosYArgumentos(String parametro, String argumento) {
        this.parametro = parametro;
        this.argumento = argumento;
    }
    
    @Override
    public String toString() {
        try {
            return (this.parametro + ", " + this.argumento);
        } catch(NullPointerException e) {
            return null;
        }
    }
    
    public String getParametro() {
        return this.parametro;
    }
    
    public String getArgumento() {
        return this.argumento;
    }
    
}
