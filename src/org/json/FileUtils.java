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
 * @author scdsahv
 */
public class FileUtils {
   private FileUtils() {
   }

   public static void toFile(JSONObject object, File file) throws IOException, JSONException {
      toFile(object, file, 0, 0);
   }

   public static void toFile(JSONObject object, File file, int indentFactor, int indent) throws IOException, JSONException {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      object.write(writer, indentFactor, indent);
      writer.flush();
   }

   public static void toURL(JSONObject object, URL url) throws IOException, JSONException {
      toURL(object, url, 0, 0);
   }

   public static void toURL(JSONObject object, URL url, int indentFactor, int indent) throws IOException, JSONException {
      URLConnection connection = url.openConnection();
      connection.setDoOutput(true);

      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
      BufferedWriter writer = new BufferedWriter(out);
      object.write(writer, indentFactor, indent);
      writer.flush();
   }

   public static JSONObject toJSONObject(URL url) throws IOException, JSONException {
      InputStream stream = url.openStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      JSONObject obj = toJSONObject(reader);
      return obj;
   }

   public static JSONObject toJSONObject(File file) throws IOException, JSONException {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      JSONObject obj = toJSONObject(reader);
      return obj;
   }

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

   public static JSONObject toJSONObjectFromXML(URL url) throws IOException, JSONException {
      return toJSONObjectFromXML(url, false);
   }

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

   public static JSONObject toJSONObjectFromXML(File file) throws IOException, JSONException {
      return toJSONObjectFromXML(file, false);
   }

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
