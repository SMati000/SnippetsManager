package central_office;

import comandos.*;

public class Redirect {
    public void redirecter(Instruccion instrucciones) {
        
        comandos.Redirecter redirect = null;
        
        switch(instrucciones.getComando()) {
            case "start":
                redirect = new Start(instrucciones);
                break;
            case "config":
                redirect = new Config(instrucciones);
                break;
            case "add":
                redirect = new Add(instrucciones);
                break;
            case "new":
                redirect = new New(instrucciones);
                break;
            case "help":
                redirect = new Help(instrucciones);
                break;
            case "link":
                redirect = new Link(instrucciones);
                break;
            case "af":
                redirect = new AF(instrucciones);
                break;
            case "cd":
                redirect = new Cd(instrucciones);
                break;
            case "show":
                redirect = new Show(instrucciones);
                break;
            case "open":
                redirect = new Open(instrucciones);
                break;
            case "delete":
                redirect = new Delete(instrucciones);
                break;
            case "move":
                redirect = new Move(instrucciones);
                break;
            case "rename":
                redirect = new Rename(instrucciones);
                break;
            case "export":
                redirect = new Export(instrucciones);
                break;
            case "backup":
                redirect = new Backup(instrucciones);
                break;
            case "recover":
                redirect = new Recover(instrucciones);
                break;
            case "info":
                redirect = new Info(instrucciones);
                break;
            case "logout":
                redirect = new Logout(instrucciones);
                break;
            case "clear":
                redirect = new Clear(instrucciones);
                break;
            case "exit":
                redirect = new Exit(instrucciones);
                break;
            default:
                System.out.println("\n____________________________________________________________________________\n"
                        + "Algo no esta bien con su instruccion... \n"
                        + "Consulte la ayuda especifica del comando o la ayuda general con el comando help\n"
                        + "____________________________________________________________________________\n");
                break;
        }
    }
}