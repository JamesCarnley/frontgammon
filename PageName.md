# Introduction #

Repository: [https://frontgammon.googlecode.com/svn/branches/assignments/3/simplechat](https://frontgammon.googlecode.com/svn/branches/assignments/3/simplechat)

"E48 (nothing to turn in), E49, E50,E51, E52(nothing to turn in)" from Paras


# Details #

## Functional Requirements ##
### Client ###
  * E49
  1. Print a notice on the client when the server closes the connection.
  1. Make the port number modifiable on the command line
  * E50
  1. Implement command "#quit" causes the client to terminate connection gracefully and exits the program.
  1. "#logoff" Causes the client to disconnect from the server but not quit.
  1. "#sethost 

&lt;host&gt;

" change the host to which the client will connect.  Must only be available while the client is not connected.  And prints an error when connected.
  1. "#setport 

&lt;port&gt;

" change the TCP port with he same constraints as #sethost.
  1. "#login" connect to the server, with the same constraints as #sethost.
  1. "#gethost" displays the current host.
  1. "#getport" displays the current port.

### Server ###
  * E49
  1. Modify the server so its prints a message when a client disconnects.
  * E50 Many of these functions overlap so it might be better to break them into pieces and call the pieces.
  1. Create an input interface for the Server's User so that input from the server user will be echoed to the local chat and to the client.  The server's text must be preceded by "SERVER MSG>"
  1. "#quit" causes the server to quit gracefully
  1. "#stop" stop listening to incoming connections
  1. "#close" stop listening and close all connections
  1. "#setport 

&lt;port&gt;

" changes listening port, only allowed if connection is closed
  1. "#start" causes the server to start listening
  1. "#getport" display the current port of the server.

## Implementation ##

### Using Regular Expressions ###
  * regex package in the Java API [http://java.sun.com/javase/6/docs/api/java/util/regex/package-summary.html](http://java.sun.com/javase/6/docs/api/java/util/regex/package-summary.html)
  * [Capturing Group](http://java.sun.com/javase/6/docs/api/java/util/regex/Pattern.html#cg)
  * [Groups Method](http://java.sun.com/javase/6/docs/api/java/util/regex/Matcher.html#group(int))









