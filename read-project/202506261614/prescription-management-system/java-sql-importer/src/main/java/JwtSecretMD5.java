import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JwtSecretMD5 {
    public static void main(String[] args) {
        String secret = "your_jwt_secret"; // Replace with your secret
        try {
            // Get the MD5 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Apply the digest method to the secret and get the hash
            byte[] hash = md.digest(secret.getBytes());

            // Convert the byte array to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            // Output the MD5 hash
            System.out.println("MD5 Hash of the secret key: " + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
