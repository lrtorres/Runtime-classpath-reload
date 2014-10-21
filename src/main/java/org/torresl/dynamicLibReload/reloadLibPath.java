package org.torresl.dynamicLibReload;

import java.io.*;
import java.lang.reflect.Field;

/**
* This class uses reflection to force the jvm to reload its library path 
* from a newly defined path string.
*
* @author	Luis Torres
*/
public class reloadLibPath {

	/**
	* Class Constructor. User /tmp by default.
	* 
	*/
	public reloadLibPath(){
	  try{
		addDir("/tmp");
 	  } catch(Exception e){};
	}

	/**
	* Class Constructor with String argument.  Appends the
	* given string to the java library path.
	*
	* @param s	Path that one wishes to append.
	*/
	public void reloadLibPath(String s){
	  try{
	      	addDir(s);
	  } catch(Exception e){};
	}

	/**
        * This enables the java.library.path to be modified at runtime
        * From a Sun engineer at http://forums.sun.com/thread.jspa?threadID=707176
        * it uses the java reflection API.
	*
	* @param s		A path to be appended to the existing java library path.
	* @throws IOException	Generated if a handle can't be acquired 
	*			or in case of insufficient permissions.	
	*/
	public static void addDir(String s) throws IOException {
    	try {

          Field field = ClassLoader.class.getDeclaredField("usr_paths");
          field.setAccessible(true);

          String[] paths = (String[])field.get(null);
          for (int i = 0; i < paths.length; i++) {
            if (s.equals(paths[i])) {
                return;
            }
          }

          String[] tmp = new String[paths.length+1];
          System.arraycopy(paths,0,tmp,0,paths.length);
          tmp[paths.length] = s;
          field.set(null,tmp);
          System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + s);

        } catch (IllegalAccessException e) {
            throw new IOException("Failed to get permissions to set library path");
        } catch (NoSuchFieldException e) {
            throw new IOException("Failed to get field handle to set library path");
        } 
      }
}
