import java.util.Base64;
public class Program {
    public static void main(String []args) {
        String encrypted_message = "<encrypted message>".replace(" ", "");
        byte[] key = "<google username>".getBytes();

        byte[] decodedBytes = Base64.getDecoder().decode(encrypted_message);
        byte[] final_message = new byte[decodedBytes.length];

        for (int i = 0, j = 0; i < final_message.length; i++, j = (j + 1) % key.length) {
            final_message[i] = (byte)(decodedBytes[i] ^ key[j]);
        }

        System.out.println(new String(final_message));
    }
}