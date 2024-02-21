package frame.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * StringをSHA-256でハッシュ化します
 */
public class Hash {
	/**
	 * 引数の文字列をハッシュ化したデーターを返します
	 * @param pass ハッシュ化する文字列
	 * @return ハッシュ化されてデータ
	 * @throws NoSuchAlgorithmException SHA-256が見つからなかった場合の例外
	 */
	public static byte[] getHashedBytes(String pass) throws NoSuchAlgorithmException {
		byte[] result = null;

		MessageDigest md = MessageDigest.getInstance("SHA-256");

		result = md.digest(pass.getBytes());

		return result;
	}

	/**
	 * 引数の文字列をハッシュ化したものを文字列にエンコードして返します
	 * @param pass ハッシュ化する文字列
	 * @return ハッシュ化されたもじれつ
	 * @throws NoSuchAlgorithmException SHA-256が見つからなかった場合の例外
	 */
	public static String getHashedString(String pass) throws NoSuchAlgorithmException {
		byte[] hashedBytes = getHashedBytes(pass);

		StringBuilder sb = new StringBuilder();

		for (byte b : hashedBytes) {
			sb.append(String.format("%02x", b & 0xff));

		}

		return sb.toString();

	}
}
