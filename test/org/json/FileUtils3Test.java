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
import static org.junit.Assert.assertNull;
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
 * @since 1.6
 */
public class FileUtils3Test {

   public FileUtils3Test() {
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
    * Test of toJSONObject method, of class FileUtils.
    */
   @Test
   public void testFromFile() throws Exception {
      System.out.println("FileUtils3Test : testFromFile");

      URL url = this.getClass().getResource("testJSON7.json");
      JSONObject jsonObj = FileUtils.toJSONObject(url);

      boolean isMarried = jsonObj.getBoolean("married");
      assertFalse("married", isMarried);
      int age = jsonObj.getInt("age");
      assertEquals("age", 76, age);
      String name = jsonObj.getString("name");
      assertNull("name", name);

      JSONObject jsonObjField = jsonObj.getJSONObject("passport");
      assertNotNull("passport", jsonObjField);
      int id = jsonObjField.getInt("id");
      assertEquals("id", 100001, id);
      String nationality = jsonObjField.getString("nationality");
      assertEquals("nationality", "American", nationality);
   }

   /**
    * Test of toFile method, of class JSONWriter.
    */
   @Test
   public void testToFile() throws IOException {
      System.out.println("FileUtilsTest : testToFile");
      JSONObject jsonObj = new JSONObject();
      jsonObj.putNULL("name");
      jsonObj.put("birthday", "1940-02-10");
      jsonObj.put("age", 76);
      jsonObj.put("married", false);

      // Cannot set null directly
      jsonObj.putNULL("car");

      jsonObj.put("favorite_foods", new String[] { "cookie", "fish", "chips" });

      JSONObject passportJsonObj = new JSONObject();
      passportJsonObj.put("id", 100001);
      passportJsonObj.put("nationality", "American");
      jsonObj.put("passport", passportJsonObj);

      File file = File.createTempFile("json", ".json");
      FileUtils.toFile(jsonObj, file);
      jsonObj = FileUtils.toJSONObject(file);

      boolean isMarried = jsonObj.getBoolean("married");
      assertFalse("married", isMarried);
      int age = jsonObj.getInt("age");
      assertEquals("age", 76, age);
      String name = jsonObj.getString("name");
      assertNull("name", name);
      String car = jsonObj.getString("car");
      assertNull("car", car);

      JSONObject jsonObjField = jsonObj.getJSONObject("passport");
      assertNotNull("passport", jsonObjField);
      int id = jsonObjField.getInt("id");
      assertEquals("id", 100001, id);
      String nationality = jsonObjField.getString("nationality");
      assertEquals("nationality", "American", nationality);
   }
}
