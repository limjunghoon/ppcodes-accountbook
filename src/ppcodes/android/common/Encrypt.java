package ppcodes.android.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class Encrypt
{
  public static String toMd5(byte[] bytes)
  {
	try
	{
	  MessageDigest algorithm = MessageDigest.getInstance("MD5");
	  algorithm.reset();
	  algorithm.update(bytes);
	  return toHexString(algorithm.digest(), "");
	}
	catch (NoSuchAlgorithmException e)
	{
	  Log.v("he--------------------------------ji", "toMd5(): " + e);
	  throw new RuntimeException(e);
	  // 05-20 09:42:13.697: ERROR/hjhjh(256):
	  // 5d5c87e61211ab7a4847f7408f48ac
	}
  }

  private static String toHexString(byte[] bytes, String separator)
  {
	StringBuilder hexString = new StringBuilder();
	for (byte b : bytes)
	{
	  String hex = Integer.toHexString(0xFF & b);
	  if (hex.length() == 1)
	  {
		hexString.append('0');
	  }
	  hexString.append(hex).append(separator);
	}
	return hexString.toString();
  }

  public static String MD5(String str)
  {
	MessageDigest md5 = null;
	try
	{
	  md5 = MessageDigest.getInstance("MD5");
	}
	catch (Exception e)
	{
	  e.printStackTrace();
	  return "";
	}

	char[] charArray = str.toCharArray();
	byte[] byteArray = new byte[charArray.length];

	for (int i = 0; i < charArray.length; i++)
	{
	  byteArray[i] = (byte) charArray[i];
	}
	byte[] md5Bytes = md5.digest(byteArray);

	StringBuffer hexValue = new StringBuffer();
	for (int i = 0; i < md5Bytes.length; i++)
	{
	  int val = ((int) md5Bytes[i]) & 0xff;
	  if (val < 16)
	  {
		hexValue.append("0");
	  }
	  hexValue.append(Integer.toHexString(val));
	}
	return hexValue.toString();
  }

  // 可逆的加密算法
  public static String encryptmd5(String str)
  {
	char[] a = str.toCharArray();
	for (int i = 0; i < a.length; i++)
	{
	  a[i] = (char) (a[i] ^ 'l');
	}
	String s = new String(a);
	return s;
  }

}
