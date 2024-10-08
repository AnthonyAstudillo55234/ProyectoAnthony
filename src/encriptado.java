import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encriptado {
    public static void main(String[] args) {

        String originalString = "bkjhbkjhgkhjg";
        String hashedString = generateHash(originalString);

        System.out.println("Original: " + originalString);
        System.out.println("Hash: " + hashedString);

    }
    public static String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(input.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
