package tokenGenerator;

public class Run {

    public static void main(String[] args) {
        TokenGenerator tokenGenerator = new TokenGenerator();
        tokenGenerator.welcomeUser();
        int lengthVariant = tokenGenerator.chooseOption();
        int[] token = tokenGenerator.generateToken(lengthVariant);
        tokenGenerator.deliverToken(token);
    }
}
