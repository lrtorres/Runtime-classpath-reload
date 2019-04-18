Runtime Library Reload Java Class
================

This class "reloads" the library path of a Java program if a dynamic library file needs to be added during runtime.
Typically, a running java instance in a VM cannot reference library files that aren't loaded at startup, but this 
program forces the running instance to re-read the library path while running.
