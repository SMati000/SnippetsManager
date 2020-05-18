package central_office;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class InterpreterTest {
    String instruccion;
    Instruccion expected;
    
    public InterpreterTest(String instruccion, Instruccion expected) {
        this.instruccion = instruccion;
        this.expected = expected;
    }
    
    @Parameters
    public static Iterable<Object[]> parametros() {
        List<ParametrosYArgumentos> temp1 = new ArrayList<>();
        temp1.add(new ParametrosYArgumentos("-category", "default"));
        
        List<ParametrosYArgumentos> temp2 = new ArrayList<>();
        temp2.add(new ParametrosYArgumentos("-newpassword", null));
        temp2.add(new ParametrosYArgumentos("-newpath", "c:\\users\\roberto\\desktop"));
        temp2.add(new ParametrosYArgumentos("-help", null));
        
        return Arrays.asList(new Object[][] {
            {"add \"C:\\users\\roberto\\desktop\\tuvieja\\asd.txt\" -category \"default\"", new Instruccion("add", "C:\\users\\roberto\\desktop\\tuvieja\\asd.txt", temp1)},
            {"cd default\\asd", new Instruccion("cd", "default\\asd", new ArrayList<>())},
            {"start -newpassword -newpath \"c:\\users\\roberto\\desktop\" -help", new Instruccion("start", null, temp2)}
        });
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of getInstrucciones method, of class Interpreter.
     */
    @Test
    public void testGetInstrucciones() {
        Interpreter interpreter = new Interpreter(instruccion);
        interpreter.interpret();
        Instruccion result = interpreter.getInstrucciones();
        
        if(expected.toString().equals(result.toString())) {
            System.out.println("Test existoso");
        } else {
            System.out.println("Test Fallido");
        }
        
        System.out.println("");
        
        System.out.println(expected.toString());
        System.out.println(result.toString());
    }
    
}
