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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @since 1.2
 */
public class JSONObject2Test {

   public JSONObject2Test() {
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
   public void testGetString() {
      System.out.println("JSONObject2Test : testGetString");

      JSONObject jsonObj = new JSONObject();
      jsonObj.put("name", "Tom");
      jsonObj.put("birthday", "1940-02-10");
      jsonObj.put("age", 76);
      jsonObj.put("married", false);

      assertEquals("int field", 76, jsonObj.get("age"));
      assertEquals("int field", "76", jsonObj.getString("age"));
      assertEquals("int field", "Tom", jsonObj.getString("name"));
   }

   /**
    * Test of creation method, of class JSONObject.
    */
   @Test
   public void testCreate() {
      System.out.println("JSONObject2Test : testCreate");

      JSONObject jsonObj = JSONObject.create()
         .put("name", "Tom")
         .put("birthday", "1940-02-10")
         .put("age", 76)
         .put("married", false);

      assertEquals("int field", 76, jsonObj.get("age"));
      assertEquals("int field", "76", jsonObj.getString("age"));
      assertEquals("int field", "Tom", jsonObj.getString("name"));
   }

}
