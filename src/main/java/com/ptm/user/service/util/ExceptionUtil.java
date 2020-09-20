package com.ptm.user.service.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
public class ExceptionUtil {

	
	@SuppressWarnings({ "unchecked", "hiding" })
	public static <K, Object> Map<K, Object> mapOf(Object... keyValPair) {

		if (keyValPair.length % 2 != 0)
			throw new IllegalArgumentException("Keys and values must be in pairs");

		Map<K, Object> map = new HashMap<K, Object>(keyValPair.length / 2);
		Boolean b = Boolean.FALSE;
		map.put((K) "success", (Object) b);

		for (int i = 0; i < keyValPair.length; i += 2) {
			map.put((K) keyValPair[i], keyValPair[i + 1]);
		}

		return map;
	}

}
