// PathVisio,
// a tool for data visualization and analysis using Biological Pathways
// Copyright 2006-2011 BiGCaT Bioinformatics
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package org.pathvisio.core.model;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import junit.framework.TestCase;
import org.pathvisio.core.debug.Logger;

public class TestGpml extends TestCase 
{
	private static final File PATHVISIO_BASEDIR = new File (".");
	/**
	 * Test reading 2010a file, then writing as 2008a
	 */
	public static void testWrite2010() throws IOException, ConverterException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/WP248_2008a.gpml");
		assertTrue (in.exists());
		
		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);
		
		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2010a.GPML_2010A.writeToXml(pwy, tmp, true);		
	}
	
	/**
	 * Test reading 2008a file, then writing as 2010a
	 */
	public static void testRead2010() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/WP248_2010a.gpml");
		assertTrue (in.exists());
		
		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);
	}
	
	/**
	 * Test reading 2008a file, then writing it as 2013a
	 */
	public static void testConvert08a13a() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/WP248_2008a.gpml");
		assertTrue (in.exists());
		
		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);
		
		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2013a.GPML_2013A.writeToXml(pwy, tmp, true);		
	}
	
	/**
	 * Test reading 2008a & 2010a files, then writing them as 2013a
	 */
	public static void testConvert10a13a() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/WP248_2010a.gpml");
		assertTrue (in.exists());
		
		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);
		
		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2013a.GPML_2013A.writeToXml(pwy, tmp, true);		
	}

	/**
	 * Test reading 2008a, 2010a & 2013a files, then writing them as 2017a
	 */
	public static void testConvert10a17a() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/WP248_2010a.gpml");
		assertTrue (in.exists());

		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);

		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2017a.GPML_2017A.writeToXml(pwy, tmp, true);
	}
	/**
	 * Test reading 2017a files with Group element, then writing them as 2017a
	 */
	public static void testGroup17a() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/2017a/group.gpml");
		assertTrue (in.exists());

		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);

		for(String s:pwy.getGroupIds())
			assertEquals("Group should have 2 elements",2,pwy.getGroupElements(s).size());

//		File tmp = new File (PATHVISIO_BASEDIR, "testData/2017a/group.gpml");
		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2017a.GPML_2017A.writeToXml(pwy, tmp, true);
	}

	
	/**
	 * Test reading 2017a files with ontology, pubref element, then writing them as 2017a
	 */
	public static void testPubRef17a() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/2017a/literaturexref-testcase.gpml");
		assertTrue (in.exists());

		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);

		assertEquals(3,pwy.getCitations().get(0).getAuthors().size());

//		File tmp = new File (PATHVISIO_BASEDIR, "testData/2017a/group.gpml");
		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2017a.GPML_2017A.writeToXml(pwy, tmp, true);
	}
	/**
	 * Test reading 2017a files with ontology, pubref element, then writing them as 2017a
	 */
	public static void testOntology17a() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/2017a/ontology-testcase.gpml");
		assertTrue (in.exists());

		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);

		for(PathwayElement e:pwy.getDataObjects())
			System.out.println(e.getClass());

//		File tmp = new File (PATHVISIO_BASEDIR, "testData/2017a/group.gpml");
		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2017a.GPML_2017A.writeToXml(pwy, tmp, true);
	}

	/**
	 * Test reading anchors, points graphId usage
	 */
	public static void testAnchors17a() throws ConverterException, IOException
	{
		File in = new File (PATHVISIO_BASEDIR, "testData/2017a/anchors-testcase.gpml");
		assertTrue (in.exists());

		Pathway pwy = new Pathway();
		pwy.readFromXml(in, true);

		for(PathwayElement e:pwy.getDataObjects())
			System.out.println(e.getClass());

//		File tmp = new File (PATHVISIO_BASEDIR, "testData/2017a/group.gpml");
		File tmp = File.createTempFile("test", "gpml");
		GpmlFormat2017a.GPML_2017A.writeToXml(pwy, tmp, true);
	}



	private static final File FILE1 = 
		new File (PATHVISIO_BASEDIR, "testData/2008a-deprecation-test.gpml");
	
	public void testDeprecatedFields() throws ConverterException
	{
		assertTrue (FILE1.exists());
		
		Pathway pwy = new Pathway();
		GpmlFormat.readFromXml(pwy, FILE1, true);
		
		PathwayElement dn = pwy.getElementById("e4fa1");
		assertEquals ("This is a backpage head", dn.getDynamicProperty("org.pathvisio.model.BackpageHead"));
	}
	
}
