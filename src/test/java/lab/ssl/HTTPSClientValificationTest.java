package lab.ssl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Test;

public class HTTPSClientValificationTest {
	private static final String httpsUrl = "https://google.com";

	@Test
	public void canCallHTTPS() throws NoSuchAlgorithmException,
			KeyManagementException, IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		TrustManager[] trustPolicy = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType)
					throws CertificateException {
				boolean valid = false;
				for (X509Certificate each : certs) {
					if (trusted(each)) {
						valid = true;
						break;
					}
				}

				if (valid == false) {
					throw new CertificateException();
				}
			}
		} };

		sc.init(null, trustPolicy, new java.security.SecureRandom());
		SSLSocketFactory sslSocketFactory = sc.getSocketFactory();

		HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
		URL u = new URL(httpsUrl);
		URLConnection uc = u.openConnection();
		HttpsURLConnection connection = (HttpsURLConnection) uc;

		final int OK = 200;
		assertThat(connection.getResponseCode(), is(OK));
	}

	private boolean trusted(X509Certificate cert) {
		try {
			KeyStore keystore = kerStore();
			PKIXParameters params = new PKIXParameters(keystore);

			@SuppressWarnings("rawtypes")
			Iterator it = params.getTrustAnchors().iterator();
			while (it.hasNext()) {
				TrustAnchor ta = (TrustAnchor) it.next();
				X509Certificate each = ta.getTrustedCert();
				try {
					each.verify(cert.getPublicKey());
					return true;
				} catch (InvalidKeyException e) {
				} catch (NoSuchProviderException e) {
				} catch (SignatureException e) {
				}
			}
		} catch (CertificateException e) {
		} catch (KeyStoreException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidAlgorithmParameterException e) {
		} catch (IOException e) {
		}
		return false;
	}

	private KeyStore kerStore() throws FileNotFoundException,
			KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException {
		String filename = System.getProperty("java.home")
				+ "/lib/security/cacerts".replace('/', File.separatorChar);
		FileInputStream is = new FileInputStream(filename);
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		String password = "changeit";
		keystore.load(is, password.toCharArray());
		return keystore;
	}
}
