package com.yh.admin.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.DateUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @description
 * @author jicc
 * @created 2011-11-17
 * @version 1.0
 */
public class EncryptUtil
{

	private final static byte[] defaultKey = { 22, 82, 25, 21, 37, 1, 61, 94 };

	private static String Algorithm = "DES";

	private static byte[] key = null;

	static boolean debug = false;

	private Properties props = new Properties();

	private static Logger logger = Logger.getLogger(EncryptUtil.class);

	private static String IS_ENCRYPT_FLAG = "isEncoding";
	
	private static String[] ips;

	public EncryptUtil() {}

	public String getProperty(String key)
	{
		return props.getProperty(key);
	}

	/**
	 * @see 生成密钥
	 */
	public static byte[] getSecretKey() throws NoSuchAlgorithmException
	{
		if (key == null)
		{
			KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
			SecretKey deskey = keygen.generateKey();
			if (debug)
				System.out.println("生成密钥:" + byte2hex(deskey.getEncoded()));
			key = deskey.getEncoded();
		}
		return key;
	}

	/**
	 * @see 将指定的数据根据提供的密钥进行加密
	 * @param input
	 *            需要加密的数据
	 * @return String 加密后的数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String encryptDataDwr(String inputStr) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		byte[] input = inputStr.getBytes();
		BASE64Encoder enc = new BASE64Encoder();
		String expireStr = inputStr;
		String isEncrypt = ConfigUtil.getProperty("url.param.encrypt.check.enabled");
		if (isEncrypt != null && Constant.YES.equals(isEncrypt))
		{
			expireStr = enc.encode(encryptData(input, defaultKey)) + "&" + IS_ENCRYPT_FLAG;
		}
		return expireStr;
	}

	/**
	 * @see 将指定的数据根据提供的密钥进行加密
	 * @param input
	 *            需要加密的数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密后的数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] encryptData(byte[] input, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{

		if (key == null)
		{
			key = defaultKey;
		}
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);

		if (debug)
		{
			System.out.println("加密前的二进串:" + byte2hex(input));
			System.out.println("加密前的字符串:" + new String(input));
		}
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(input);
		if (debug)
		{
			System.out.println("加密后的二进串:" + byte2hex(cipherByte));
		}
		return cipherByte;
	}

	/**
	 * @see 将给定的已加密的数据通过指定的密钥进行解密
	 * @param input
	 *            待解密的数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密后的数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decryptData(byte[] input, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug)
		{
			System.out.println("解密前的信息:" + byte2hex(input));
		}
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(input);
		if (debug)
		{
			System.out.println("解密后的二进串:" + byte2hex(clearByte));
			System.out.println("解密后的字符串:" + (new String(clearByte)));
		}
		return clearByte;
	}

	/**
	 * @see 将给定的已加密的数据通过指定的密钥进行解密
	 * @param input
	 *            待解密的数据
	 * @return String 解密后的数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encryptData(String inputStr)
	{
		// 解码
		try
		{
			String isEncrypt = ConfigUtil.getProperty("url.param.encrypt.check.enabled");
			if (StringUtils.isNotEmpty(isEncrypt) && !Constant.YES.equals(isEncrypt))
			{
				return inputStr;
			}
			BASE64Decoder dec = new BASE64Decoder();
			byte[] btExpire = dec.decodeBuffer(inputStr);
			// 解密
			btExpire = EncryptUtil.decryptData(btExpire, defaultKey);
			return new String(btExpire);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return inputStr;
		}
	}

	/**
	 * @see 字节码转换成16进制字符串
	 * @param b
	 *            输入要转换的字节码
	 * @return String 返回转换后的16进制字符串
	 */
	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
			{
				hs = hs + ":";
			}
		}
		return hs.toUpperCase();
	}

	public String getMacAddress()
	{
		return getMac();
	}

	public boolean isLegal()
	{

		boolean result = true;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		InputStream is = null;
		is = classLoader.getResourceAsStream("license.dat");

		String path = null;
		if (is != null)
		{
			path = classLoader.getResource("license.dat").getPath();
			try
			{
				props.load(is);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				try
				{
					is.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			result = false;
		}

		// 取得试用时间
		String expireDay = getProperty("expireDay");

		// 如果取不到试用时间就认为已经失效
		if (GenericValidator.isBlankOrNull(expireDay))
		{
			// 读取服务器Mac地址，保存
			String macAdress = getMacAddress();
			if (!GenericValidator.isBlankOrNull(macAdress))
			{
				props.setProperty("expireDay", macAdress);
				OutputStream fos = null;
				try
				{
					fos = new FileOutputStream(path);

					props.store(fos, "");
					fos.flush();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					if (fos != null)
					{
						try
						{
							fos.close();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
			result = false;
		}
		else
		{
			// 解密失效时间
			BASE64Decoder dec = new BASE64Decoder();
			byte[] btExpireDay;
			try
			{
				// 解码
				btExpireDay = dec.decodeBuffer(expireDay);
				// 解密失效时间
				try
				{
					btExpireDay = EncryptUtil.decryptData(btExpireDay, defaultKey);
				}
				catch (Exception e)
				{
					return false;
				}

				expireDay = new String(btExpireDay);
				String[] exDay = expireDay.split(";");
				if (exDay.length <= 1)
				{
					result = false;
				}
				else
				// 配置文件中有失效日期
				{
					Date dtExpireDay = DateUtil.parseDate(exDay[0]);
					Date now = DateUtil.now();
					String isPrintLog = getProperty("isPrintLog");
					if (Constant.YES.equals(isPrintLog))
					{
						logger.info("dtExpireDay:" + DateUtil.formatDate(dtExpireDay));
					}
					if (now.after(dtExpireDay))
					{
						result = false;
						return result;
					}

					String bandingMacAddress = exDay[1];
					if (Constant.YES.equals(isPrintLog))
					{
						logger.info("binding_macAddress:" + bandingMacAddress);
					}
					if (!GenericValidator.isBlankOrNull(bandingMacAddress))
					{
						String macAddressStr = getMacAddress();
						String[] macAddressArr = macAddressStr.split("#");
						boolean isMap = false ; //是否匹配
						for(String macAddress : macAddressArr)
						{
							if (macAddress != null && macAddress.length() == 17 && bandingMacAddress.contains(macAddress))
							{
								isMap = true;
								break;
							}
						}
						result = isMap;
					}
				}
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return result;
	}

	/***
	 * 将10进制转为16进制（物理地址是16进制的）
	 * @param buf
	 * @return
	 */
	protected static String toHex(byte buf)
	{
		int n = buf >= 0 ? buf : 256 + buf;
		String str = Integer.toHexString(n);
		if (str.length() < 2)
			str = "0" + str;
		return str.toUpperCase();
	}

	private static String formatMac(byte[] mac)
	{
		if (mac == null || mac.length < 1)
			return null;
		StringBuilder builder = new StringBuilder();
		for (byte b : mac)
		{
			String temp = toHex(b);
			builder.append(temp);
			builder.append("-");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	private void parseIp()
	{
		String bindingIP = getProperty("bindingIP");
		ips = bindingIP == null ?null:bindingIP.split("#");
	}
	
	private boolean containIp(String getIp)
	{
		for(String ip : ips)
		{
			if(ip != null && ip.equals(getIp))
			{
				return true;
			}
		}
		return false;
	}

	/***
	 * 获取物理地址
	 * @return
	 */
	public String getMac()
	{
		try
		{
			parseIp();
			String returnMac = "";
			Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
			while (el.hasMoreElements())
			{
				NetworkInterface item = el.nextElement();
				try
				{
					if (item != null)
					{
						for (InterfaceAddress address : item.getInterfaceAddresses())
						{
							byte[] mac = item.getHardwareAddress();
							if (mac == null || mac.length < 1)
								continue;
							if (address.getAddress() instanceof Inet4Address)
							{
								Inet4Address inet4Address = (Inet4Address) address.getAddress();
								String isPrintLog = getProperty("isPrintLog");
								if (Constant.YES.equals(isPrintLog))
								{
									logger.info("bindingIP:" + getProperty("bindingIP") + "   HardwareAddress:" + formatMac(item.getHardwareAddress()));
									logger.info("inet4Address:"+inet4Address==null?null:inet4Address.getHostAddress());
								}
								if (inet4Address != null && containIp(inet4Address.getHostAddress()))
								{
									returnMac += formatMac(item.getHardwareAddress())+"#";
								}
							}
						}
					}
				}
				catch (NoSuchMethodError nme)
				{
					 logger.error(nme.getMessage());
				}
			}
			return returnMac;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return null;
	}

	public static Date addYears(Date date, int years)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);

		return cal.getTime();
	}

	public static byte[] getDefaultkey()
	{
		return defaultKey;
	}
	
	
	/**
	 * 加密
	 * @param src
	 * @return
	 */
	public static String encodeData(String src)
	{
		try
		{
			byte[] input = src.getBytes();
			//此方法转换base64编码
//			BASE64Encoder enc = new BASE64Encoder();
//			String expireStr = enc.encode(EncryptUtil.encryptData(input, EncryptUtil.getDefaultkey()));
			String expireStr = new String(Base64.encodeBase64(EncryptUtil.encryptData(input, EncryptUtil.getDefaultkey())));
			return expireStr;
		}
		catch (Exception e)
		{
			Logger.getLogger(EncryptUtil.class).error("encrypt error!");
			return src;
		}
	}
	
	/**
	 * 解密
	 * @param src
	 * @return
	 */
	public static String decodeData(String src)
	{
		try
		{
			BASE64Decoder dec = new BASE64Decoder();
			byte[] btExpire = dec.decodeBuffer(src);
			// 解密
			btExpire = EncryptUtil.decryptData(btExpire, EncryptUtil.getDefaultkey());
			return new String(btExpire);
		}
		catch (Exception e)
		{
			Logger.getLogger(EncryptUtil.class).error("encrypt error!");
			return src;
		}
	}

	/**
	 * 验证DNS的license的有效性
	 * */
	public boolean isLegalByDNS(HttpServletRequest request)
	{
		boolean result = true;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		InputStream is = null;
		is = classLoader.getResourceAsStream("license.dat");

		String path = null;
		if (is != null)
		{
			path = classLoader.getResource("license.dat").getPath();
			try
			{
				props.load(is);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				try
				{
					is.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			result = false;
		}

		// 取得试用时间
		String expireDay = getProperty("expireDay");

		// 如果取不到试用时间就认为已经失效
		if (GenericValidator.isBlankOrNull(expireDay))
		{
			// 读取服务器Mac地址，保存
			String dnsAdress = getDNSAddress(request);
			if (!GenericValidator.isBlankOrNull(dnsAdress))
			{
				props.setProperty("expireDay", dnsAdress);
				OutputStream fos = null;
				try
				{
					fos = new FileOutputStream(path);

					props.store(fos, "");
					fos.flush();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					if (fos != null)
					{
						try
						{
							fos.close();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
			result = false;
		}
		else
		{
			// 解密失效时间
			BASE64Decoder dec = new BASE64Decoder();
			byte[] btExpireDay;
			try
			{
				// 解码
				btExpireDay = dec.decodeBuffer(expireDay);
				// 解密失效时间
				try
				{
					btExpireDay = EncryptUtil.decryptData(btExpireDay, defaultKey);
				}
				catch (Exception e)
				{
					return false;
				}

				expireDay = new String(btExpireDay);
				String[] exDay = expireDay.split(";");
				if (exDay.length <= 1)
				{
					result = false;
				}
				else
				// 配置文件中有失效日期
				{
					Date dtExpireDay = DateUtil.parseDate(exDay[0]);
					Date now = DateUtil.now();
					String isPrintLog = getProperty("isPrintLog");
					if (Constant.YES.equals(isPrintLog))
					{
						logger.info("dtExpireDay:" + DateUtil.formatDate(dtExpireDay));
					}
					if (now.after(dtExpireDay))
					{
						result = false;
					}

					String bandingDNSName = exDay[1];
					if (Constant.YES.equals(isPrintLog))
					{
						logger.info("binding_macAddress:" + bandingDNSName);
					}
					if (!GenericValidator.isBlankOrNull(bandingDNSName))
					{
						String dnsAddressStr = getDNSAddress(request);
						String[] macAddressArr = dnsAddressStr.split("#");
						boolean isMap = false ; //是否匹配
						for(String macAddress : macAddressArr)
						{
							if (macAddress != null && bandingDNSName.contains(macAddress))
							{
								isMap = true;
								break;
							}
						}
						result = isMap;
					}
				}
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return result;
	}

	private String getDNSAddress(HttpServletRequest request)
	{
		return request.getServerName();
	}
	
}
