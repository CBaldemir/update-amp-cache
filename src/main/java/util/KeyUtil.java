package util;

import com.example.demo.UpdateAmpCacheController;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class KeyUtil {

	public static String getSignature(String url) throws Exception {

		url = url.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
		byte[] decoded = Base64.decodeBase64(url);

		Signature signature = Signature.getInstance("SHA256withRSA");
		PKCS8EncodedKeySpec encodedPrivateKeySpec = new PKCS8EncodedKeySpec(decoded);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(encodedPrivateKeySpec);

		signature.initSign(privateKey);
		signature.update(url.getBytes("UTF-8"));
		byte[] signatureValue = signature.sign();
		String signatureUrl = Base64.encodeBase64String(signatureValue);
		return signatureUrl
				.replace("+", "-")
				.replace("/", "_")
				.replace("=", "");

	}
}
