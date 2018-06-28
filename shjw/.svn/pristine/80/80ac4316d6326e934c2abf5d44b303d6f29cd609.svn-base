package com.sojson.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 
 * @author xj
 * @version 1.0,2017年8月7日
 * 
 */
@SuppressWarnings("unchecked")
public class SerializeUtil {
	static final Class<?> CLAZZ = SerializeUtil.class;

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (Exception e) {
			LoggerUtils.fmtError(CLAZZ, e, "serialize error %s",
					JSONObject.fromObject(value));
		} finally {
			close(os);
			close(bos);
		}
		return rv;
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(bais);
			close(ois);
		}
		return null;
	}

	public static Object deserialize(byte[] in) {
		return deserialize(in, Object.class);
	}

	public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
			}
		} catch (Exception e) {
			LoggerUtils.fmtError(CLAZZ, e, "serialize error %s", in);
		} finally {
			close(is);
			close(bis);
			
		}
		return (T) rv;
	}

	/**
	 * 关闭io流对象
	 * 
	 * @param closeable
	 */
	private static void close(Closeable closeable) {
		if (closeable != null)
			try {
				closeable.close();
			} catch (IOException e) {
				LoggerUtils.fmtError(CLAZZ, "close stream error");
			}
	}

	/**
	 * 序列化 list 集合
	 * 
	 * @param list
	 * @return
	 */
	public static byte[] serializeList(List<?> list) {

		if (list == null || list.size() == 0) {
			return null;
		}
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			for (Object obj : list) {
				oos.writeObject(obj);
			}
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(oos);
			close(baos);
		}
		return bytes;
	}

	/**
	 * 反序列化 list 集合
	 * 
	 * @param lb
	 * @return
	 */
	public static List<?> unserializeList(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		List<Object> list = new ArrayList<Object>();
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			while (bais.available() > 0) {
				Object obj = (Object) ois.readObject();
				if (obj == null) {
					break;
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(bais);
			close(ois);
		}
		return list;
	}

}
