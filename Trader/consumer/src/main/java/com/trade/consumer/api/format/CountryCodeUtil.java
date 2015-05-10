package com.trade.consumer.api.format;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class CountryCodeUtil {

	public static boolean isValidISOCountry(String s) {		
		return ISO_COUNTRIES.contains(s);
	}

	private static final Set<String> ISO_COUNTRIES = new HashSet<String>(
			Arrays.asList(Locale.getISOCountries()));

	private CountryCodeUtil() {
		
	}

}
