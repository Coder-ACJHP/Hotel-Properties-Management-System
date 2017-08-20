package com.coder.hms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceControl extends ResourceBundle.Control{

	 @Override
	    public ResourceBundle newBundle(String baseName, Locale locale,
	            String format, ClassLoader loader, boolean reload)
	            throws IllegalAccessException, InstantiationException,
	            IOException {
	        String bundlename = toBundleName(baseName, locale);
	        String resName = toResourceName(bundlename, "properties");
	        InputStream stream = loader.getResourceAsStream(resName);
	        return new PropertyResourceBundle(new InputStreamReader(stream,
	                "UTF-8"));
	    }
}
