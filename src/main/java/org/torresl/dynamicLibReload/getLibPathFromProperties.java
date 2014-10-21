package org.torresl.dynamicLibReload;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
* Builds system dependent library path (for use with reloadLibPath).
* @author	Luis Torres
*/
public class getLibPathFromProperties{

	private InputStream IS;
	private Properties p;
 /**
 * Class constructor.
 */
  public getLibPathFromProperties(){
	p = null;
	IS = null;
  }

 /**
 * Will modify the path separator depending of whether we're in a unix variant or Windows variant.
 * 
 * @param s	The original string.
 * @return	The new string with converted slashes.
 */
  public String osPathModifier(String s){
	String tempStr = s;
	String OS = System.getProperty("os.name").toLowerCase();

	if (OS.indexOf("win") >= 0){
	  tempStr = s.replace("/", "\\");
	}
	
	return tempStr;
  }

 /**
 * Loads the 'new.lib.path' property from the default ldlibrary.properties by default.
 *
 * @return	The contents of the 'lpsolver.lib.path' property.
 */
  public String getProp() {
	p = new Properties();
	String prop = new String();
	  
	try {
	  IS = this.getClass().getClassLoader().getResourceAsStream("ldlibrary.properties");
	  p.load(IS);

	  prop = osPathModifier(p.getProperty("new.lib.path"));
	  
	} catch(IOException e){;
	  e.printStackTrace();
 	}
	return prop;
  }

  public String getProp(String filename) {
	p = new Properties();
	String prop = new String();
	  
	try {
	  IS = this.getClass().getClassLoader().getResourceAsStream(filename);
	  p.load(IS);

	  prop = osPathModifier(p.getProperty("new.lib.path"));
	  
	} catch(IOException e){;
	  e.printStackTrace();
 	}
	return prop;
  }

  
}
