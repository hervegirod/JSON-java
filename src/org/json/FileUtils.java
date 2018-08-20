/*
Copyright (C) 2018 by Herve Girod

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package org.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @since 1.0
 */
public class FileUtils {
   private FileUtils() {
   }

   /**
    * Save a JSONObject as a File. The result will be a JSON file. For example:
    * <pre>
    * {"version":"0.9.5","fields":["title","body"],"ref":"id","documentStore":{"docs"...
    * </pre>
    *
    * @param object the JSONObject
    * @param file the file
    * @throws IOException if an IOException occured
    * @throws JSONException if the JSONObject was unable to write
    */
   public static void toFile(JSONObject object, File file) throws IOException, JSONException {
      toFile(object, file, 0, 0);
   }

   /**
    * Save a JSONObject as a File, using a specified indentFactor and indent. The result will be a JSON file. For example:
    * <pre>
    * {"version":"0.9.5","fields":["title","body"],"ref":"id","documentStore":{"docs"...
    * </pre>
    *
    * @param object the JSONObject
    * @param file the file
    * @param indentFactor The number of spaces to add to each level of indentation
    * @param indent The indentation of the top level
    * @throws IOException if an IOException occured
    * @throws JSONException if the JSONObject was unable to write
    */
   public static void toFile(JSONObject object, File file, int indentFactor, int indent) throws IOException, JSONException {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      object.write(writer, indentFactor, indent);
      writer.flush();
   }

   /**
    * Save a JSONObject as a File. The result will be a javascript file, where the result of the JSON content will be affected to the specified variable. For example:
    * <pre>
    * var myVar = {"version":"0.9.5","fields":["title","body"],"ref":"id","documentStore":{"docs"...
    * </pre>
    *
    * @param object the JSONObject
    * @param file the file
    * @param var the variable to affect
    * @throws IOException if an IOException occured
    * @throws JSONException if the JSONObject was unable to write
    */
   public static void toFile(JSONObject object, File file, String var) throws IOException, JSONException {
      toFile(object, file, 0, 0, var);
   }

   /**
    * Save a JSONObject as a File, using a specified indentFactor and indent. The result will be a javascript file, where the result of the JSON content will be
    * affected to the specified variable. For example:
    * <pre>
    * var myVar = {"version":"0.9.5","fields":["title","body"],"ref":"id","documentStore":{"docs"...
    * </pre>
    *
    * @param object the JSONObject
    * @param file the file
    * @param indentFactor The number of spaces to add to each level of indentation
    * @param indent The indentation of the top level
    * @param var the variable to affect
    * @throws IOException if an IOException occured
    * @throws JSONException if the JSONObject was unable to write
    */
   public static void toFile(JSONObject object, File file, int indentFactor, int indent, String var) throws IOException, JSONException {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      writer.write("var " + var + " = ");
      object.write(writer, indentFactor, indent);
      writer.flush();
   }

   /**
    * Save a JSONObject as an URL.
    *
    * @param object the JSONObject
    * @param url the URL
    * @throws IOException if an IOException occured
    * @throws JSONException if the JSONObject was unable to write
    */
   public static void toURL(JSONObject object, URL url) throws IOException, JSONException {
      toURL(object, url, 0, 0);
   }

   /**
    * Save a JSONObject as an URL, using a specified indentFactor and indent.
    *
    * @param object the JSONObject
    * @param url the URL
    * @param indentFactor The number of spaces to add to each level of indentation
    * @param indent The indentation of the top level
    * @throws IOException if an IOException occured
    * @throws JSONException if the JSONObject was unable to write
    */
   public static void toURL(JSONObject object, URL url, int indentFactor, int indent) throws IOException, JSONException {
      URLConnection connection = url.openConnection();
      connection.setDoOutput(true);

      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
      BufferedWriter writer = new BufferedWriter(out);
      object.write(writer, indentFactor, indent);
      writer.flush();
   }

   /**
    * Load an URL as a JSONObject.
    *
    * @param url the URL
    * @return the JSONObject
    * @throws IOException if an IOException occured
    * @throws JSONException if the URL is not a valid JSON file
    */
   public static JSONObject toJSONObject(URL url) throws IOException, JSONException {
      InputStream stream = url.openStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      JSONObject obj = toJSONObject(reader);
      return obj;
   }

   /**
    * Load a File as a JSONObject.
    *
    * @param file the File
    * @return the JSONObject
    * @throws IOException if an IOException occured
    * @throws JSONException if the File is not a valid JSON file
    */
   public static JSONObject toJSONObject(File file) throws IOException, JSONException {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      JSONObject obj = toJSONObject(reader);
      return obj;
   }

   /**
    * Use a reader to load a JSONObject.
    *
    * @param reader the reader
    * @return the JSONObject
    * @throws IOException if an IOException occured
    * @throws JSONException if a JSONException occured
    */
   public static JSONObject toJSONObject(BufferedReader reader) throws IOException, JSONException {
      StringBuilder buf = new StringBuilder();
      boolean started = false;
      while (true) {
         String line = reader.readLine();
         if (line != null) {
            buf.append(line);
            if (started) {
               buf.append('\n');
            } else {
               started = true;
            }
         } else {
            break;
         }
      }
      reader.close();
      JSONObject obj = new JSONObject(buf.toString());
      return obj;
   }

   /**
    * Convert an XML URL to a JSONObject.
    *
    * @param url the XML URL
    * @return the JSONObject
    * @throws IOException if an IOException occured
    * @throws JSONException if the XML URL is not a valid JSON file
    */
   public static JSONObject toJSONObjectFromXML(URL url) throws IOException, JSONException {
      return toJSONObjectFromXML(url, false);
   }

   /**
    * Convert an XML URL to a JSONObject.
    *
    * @param url the XML URL
    * @param keepStrings If true, then values will not be coerced into boolean or numeric values and will instead be left as strings
    * @return the JSONObject
    * @throws IOException if an IOException occured
    * @throws JSONException if the XML URL is not a valid JSON file
    */
   public static JSONObject toJSONObjectFromXML(URL url, boolean keepStrings) throws IOException, JSONException {
      InputStream stream = url.openStream();
      StringBuilder buf = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
         boolean started = false;
         while (true) {
            String line = reader.readLine();
            if (line != null) {
               buf.append(line);
               if (started) {
                  buf.append('\n');
               } else {
                  started = true;
               }
            } else {
               break;
            }
         }
      }
      JSONObject obj = XML.toJSONObject(buf.toString(), keepStrings);
      return obj;
   }

   /**
    * Convert an XML File to a JSONObject.
    *
    * @param file the XML File
    * @return the JSONObject
    * @throws IOException if an IOException occured
    * @throws JSONException if the XML File is not a valid JSON file
    */
   public static JSONObject toJSONObjectFromXML(File file) throws IOException, JSONException {
      return toJSONObjectFromXML(file, false);
   }

   /**
    * Convert an XML File to a JSONObject.
    *
    * @param file the XML File
    * @param keepStrings If true, then values will not be coerced into boolean or numeric values and will instead be left as strings
    * @return the JSONObject
    * @throws IOException if an IOException occured
    * @throws JSONException if the XML File is not a valid JSON file
    */
   public static JSONObject toJSONObjectFromXML(File file, boolean keepStrings) throws IOException, JSONException {
      StringBuilder buf = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
         boolean started = false;
         while (true) {
            String line = reader.readLine();
            if (line != null) {
               buf.append(line);
               if (started) {
                  buf.append('\n');
               } else {
                  started = true;
               }
            } else {
               break;
            }
         }
      }
      JSONObject obj = XML.toJSONObject(buf.toString(), keepStrings);
      return obj;
   }
}
