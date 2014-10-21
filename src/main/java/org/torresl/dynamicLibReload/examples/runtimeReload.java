package org.torresl.dynamicLibReload.examples;

import org.torresl.dynamicLibReload.*;

/*
* Author: Luis
*This class instantiates both classes needed to change the java library path at runtime.
*/

public class runtimeReload {
  public static void main(String[] args){
    getLibPathFromProperties pathToReload = new getLibPathFromProperties();
    reloadLibPath newPath = new reloadLibPath();

    System.out.println("Before: " + System.getProperty("java.library.path"));
   
    newPath.reloadLibPath(pathToReload.getProp());

    System.out.println("After: " + System.getProperty("java.library.path"));
  }

}
