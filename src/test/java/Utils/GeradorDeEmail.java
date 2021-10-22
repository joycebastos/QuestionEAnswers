package Utils;

public class GeradorDeEmail{
    GeradorDeNome nomeCompleto = new GeradorDeNome();
    private static String email;

    public  String geraEmail() {
        String[] nome = nomeCompleto.geraNomeCompleto().split(" ");
        String primeiroNome = nome[0];
        email = primeiroNome +"@yopmail.com";

        return email;

    }
}
