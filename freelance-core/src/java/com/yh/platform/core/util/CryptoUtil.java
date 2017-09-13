/*
 * @(#) CryptoUtil.java        1.00         2006-7-26
 * 
 * Copyright (c) 2006 JADE EXPRESS Corporation. All Rights Reserved.
 *
 *
 */
package com.yh.platform.core.util;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.engines.RC6Engine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.encoders.HexEncoder;
import org.bouncycastle.util.encoders.UrlBase64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * crypto utility functions
 * 
 * @author eric
 */
public class CryptoUtil {
	private static byte[] DEFAULT_KEY = "2006!@#$%^&*()_+"
			.getBytes();

	public static byte[] blockCipherProcess(BlockCipher engine,
			boolean encrypt, byte[] data, byte[] key) {
		KeyParameter kp = new KeyParameter(key);
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(engine);

		cipher.init(encrypt, kp);

		byte[] out = null;
		try {
			out = new byte[cipher.getOutputSize(data.length)];
			int len1 = cipher.processBytes(data, 0, data.length, out, 0);
			cipher.doFinal(out, len1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return out;
	}

	public static byte[] rc6(boolean encrypt, byte[] data, byte[] key) {
		return blockCipherProcess(new RC6Engine(), encrypt, data, key);
	}

	public static byte[] blowfish(boolean encrypt, byte[] data, byte[] key) {
		return blockCipherProcess(new BlowfishEngine(), encrypt, data, key);
	}

	public static String base64_encode(byte[] data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Base64Encoder ube = new Base64Encoder();
		try {
			ube.encode(data, 0, data.length, baos);
		} catch (IOException e) {
		}

		return baos.toString();
	}

	public static byte[] base64_decode(String s) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Base64Encoder ube = new Base64Encoder();
		try {
			ube.decode(s, baos);
		} catch (IOException e) {
		}

		return baos.toByteArray();
	}

	public static String base64url_encode(byte[] data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		UrlBase64Encoder ube = new UrlBase64Encoder();
		try {
			ube.encode(data, 0, data.length, baos);
		} catch (IOException e) {
		}

		return baos.toString();
	}

	public static byte[] base64url_decode(String s) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		UrlBase64Encoder ube = new UrlBase64Encoder();
		try {
			ube.decode(s, baos);
		} catch (IOException e) {
		}

		return baos.toByteArray();
	}

	public static String hex_encode(byte[] data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HexEncoder he = new HexEncoder();
		try {
			he.encode(data, 0, data.length, baos);
		} catch (IOException e) {
		}

		return baos.toString();
	}

	public static byte[] hex_decode(String s) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HexEncoder he = new HexEncoder();
		try {
			he.decode(s, baos);
		} catch (IOException e) {
		}

		return baos.toByteArray();
	}

	/**
	 * fast encrypt data for passing by url
	 * 
	 * @param data
	 * @return
	 */
	public static String fast_url_encrypt(byte[] data) {
		return base64url_encode(rc6(true, data, DEFAULT_KEY));
	}

	/**
	 * decrypt data resulted by fast_url_encrypt.
	 * 
	 * <b>NOTE:return data may have zero padding</b>
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] fast_url_decrypt(String s) {
		return rc6(false, base64url_decode(s), DEFAULT_KEY);
	}

	public static String md5(String str) {
		MD5Digest digest = new MD5Digest();
		byte[] bytes = str.getBytes();
		byte[] buf = new byte[digest.getDigestSize()];

		digest.update(bytes, 0, bytes.length);
		digest.doFinal(buf, 0);
		return new String(Hex.encode(buf));
	}
	
	public static void main(String[] args) {
		System.out.println(md5("666666"));
	}

}
