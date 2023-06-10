package Carta.Model;
public class Cliente extends UtenteNonIdentificato{

    private Integer id;
    private String email;
    private String user;
    private String password;


    public Cliente(Integer id, String nome, String cognome, String email, String user, String password) {
        super(nome, cognome);
        this.id = id;
        this.user  = user;
        this.email = email;
        this.password = password;
    }

    public Cliente(String nome, String cognome, String email, String user, String password) {
        super(nome, cognome);
        this.id = 0;
        this.user  = user;
        this.email = email;
        this.password = password;
    }

    public void riscattaPremio() {
        String premioDaRiscattare;
        int indice = 0;
        System.out.println("Benvenuto nell'area riscatta premio");
        do {
            switch(indice) {
                case 1: {
                    System.out.println("Il primo premio è: ");
                    break;
                }
            }
        } while (indice != 0);
            System.out.println("Arrivederci");
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getCognome() {
        return super.getCognome();
    }

    public String getEmail() {
        return email;
    }
    public String getUser() {return user;}
    public String getPassword() {
        return password;
    }
    public Integer getId() {
        return this.id;
    }
}
