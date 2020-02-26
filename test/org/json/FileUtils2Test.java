/*
Copyright (C) 2020 by Herve Girod

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @since 1.5
 */
public class FileUtils2Test {

   public FileUtils2Test() {
   }

   @BeforeClass
   public static void setUpClass() {
   }

   @AfterClass
   public static void tearDownClass() {
   }

   @Before
   public void setUp() {
   }

   @After
   public void tearDown() {
   }

   /**
    * Test of toFile method, of class FileUtils.
    */
   @Test
   public void testFromFile() throws Exception {
      System.out.println("FileUtils2Test : testFromFile");

      URL url = this.getClass().getResource("testJSONArray.json");
      JSONArray array = FileUtils.toJSONArray(url);
      assertEquals("Array length", 3, array.length());
      Object o = array.get(0);
      assertEquals("First index", "cookie", o);
   }

   /**
    * Test of toFile method, of class JSONWriter.
    */
   @Test
   public void testToFile() throws IOException {
      System.out.println("FileUtils2Test : testToFile");
      JSONArray array = new JSONArray();
      array.put("cookie");
      array.put("fish");
      array.put("chips");

      File file = File.createTempFile("json", ".json");
      FileUtils.toFile(array, file);
      array = FileUtils.toJSONArray(file);

      assertEquals("Array length", 3, array.length());
      Object o = array.get(0);
      assertEquals("First index", "cookie", o);
   }
}
