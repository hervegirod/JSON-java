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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @since 1.1
 */
public class JSONObjectTest {

   public JSONObjectTest() {
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
    * Test of creation method, of class JSONObject.
    */
   @Test
   public void testCreate() {
      System.out.println("JSONObjectTest : testCreate");

      JSONObject jsonObj = new JSONObject();
      jsonObj.put("name", "Tom");
      jsonObj.put("birthday", "1940-02-10");
      jsonObj.put("age", 76);
      jsonObj.put("married", false);

      // Cannot set null directly
      jsonObj.put("car", JSONObject.NULL);

      jsonObj.put("favorite_foods", new String[] { "cookie", "fish", "chips" });

      JSONObject passportJsonObj = new JSONObject();
      passportJsonObj.put("id", 100001);
      passportJsonObj.put("nationality", "American");
      jsonObj.put("passport", passportJsonObj);

      boolean isMarried = jsonObj.getBoolean("married");
      assertFalse("married", isMarried);
      int age = jsonObj.getInt("age");
      assertEquals("age", 76, age);
      String name = jsonObj.getString("name");
      assertEquals("name", "Tom", name);

      JSONObject jsonObjField = jsonObj.getJSONObject("passport");
      assertNotNull("passport", jsonObjField);
      int id = jsonObjField.getInt("id");
      assertEquals("id", 100001, id);
      String nationality = jsonObjField.getString("nationality");
      assertEquals("nationality", "American", nationality);
   }

   /**
    * Test of Equals method, of class JSONObject.
    */
   @Test
   public void testEquals() throws IOException {
      System.out.println("JSONObjectTest : testEquals");

      URL url = this.getClass().getResource("testJSON.json");
      JSONObject jsonObj = FileUtils.toJSONObject(url);

      URL url2 = this.getClass().getResource("testJSON2.json");
      JSONObject jsonObj2 = FileUtils.toJSONObject(url2);

      assertEquals("must be equal", jsonObj, jsonObj2);
   }

   /**
    * Test of Equals method, of class JSONObject.
    */
   @Test
   public void testEquals2() throws IOException {
      System.out.println("JSONObjectTest : testEquals2");

      URL url = this.getClass().getResource("testJSON.json");
      JSONObject jsonObj = FileUtils.toJSONObject(url);

      URL url2 = this.getClass().getResource("testJSON3.json");
      JSONObject jsonObj2 = FileUtils.toJSONObject(url2);

      assertFalse("must not be equal", jsonObj.equals(jsonObj2));
   }

   /**
    * Test of Equals method, of class JSONObject.
    */
   @Test
   public void testEquals3() throws IOException {
      System.out.println("JSONObjectTest : testEquals3");

      URL url = this.getClass().getResource("testJSON.json");
      JSONObject jsonObj = FileUtils.toJSONObject(url);

      URL url2 = this.getClass().getResource("testJSON4.json");
      JSONObject jsonObj2 = FileUtils.toJSONObject(url2);

      assertFalse("must not be equal", jsonObj.equals(jsonObj2));
   }

   /**
    * Test of Equals method, of class JSONObject.
    */
   @Test
   public void testEquals4() throws IOException {
      System.out.println("JSONObjectTest : testEquals4");

      URL url = this.getClass().getResource("testJSON.json");
      JSONObject jsonObj = FileUtils.toJSONObject(url);

      URL url2 = this.getClass().getResource("testJSON5.json");
      JSONObject jsonObj2 = FileUtils.toJSONObject(url2);

      assertEquals("must be equal", jsonObj, jsonObj2);
   }

}
