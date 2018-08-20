Release history:

~~~
For the https://github.com/hervegirod/JSON-java fork:
1.0        this is a Netbeans project
           JSONObject and JSONArray both have an equals and hashCode method
           a FileUtils class with static method has been added to simply the generation to/from a File / a JSON object
           The supported Java version is Java 8
1.1        Add Unit tests from the https://github.com/stleary/JSON-Java-unit-test project
1.2        JSONObject and JSONArray now have static methods to create new JSONObject or JSONArray, allowing to simplify the creation
           of a JSON content
           Use multicatch in exceptions rather than using a generic exception
1.3        Add new methods in the FileUtils class to create a Javascript expression rather than a JSON content. This can be useful for
           some browsers which do not allow to open JSON content in a local environment

For https://github.com/stleary/JSON-java project
20180813    POM change to include Automatic-Module-Name (#431)

20180130    Recent commits

20171018    Checkpoint for recent commits.

20170516    Roll up recent commits.

20160810    Revert code that was breaking opt*() methods.

20160807    This release contains a bug in the JSONObject.opt*() and JSONArray.opt*() methods,
it is not recommended for use.
Java 1.6 compatability fixed, JSONArray.toList() and JSONObject.toMap(),
RFC4180 compatibility, JSONPointer, some exception fixes, optional XML type conversion.
Contains the latest code as of 7 Aug, 2016

20160212    Java 1.6 compatibility, OSGi bundle. Contains the latest code as of 12 Feb, 2016.

20151123    JSONObject and JSONArray initialization with generics. Contains the
latest code as of 23 Nov, 2015.

20150729    Checkpoint for Maven central repository release. Contains the latest code
as of 29 July, 2015.
~~~