package com.yh.platform.core.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;

/**
 * 
 * @author wanxm
 *
 */
public final class PdfConcat 
{
	/**
	 * Concatenate existing PDF file to one file
	 * 
	 * @param inFiles
	 * @param outFile
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void concat(String[] inFiles, String outFile) throws Exception
	{
		if (inFiles.length < 1) return;
		
		int pageOffset = 0;
		int index = 0;
		@SuppressWarnings("rawtypes")
		ArrayList master = new ArrayList();
		
		Document document = null;
		PdfCopy writer = null;
		PdfReader reader = null;
		
		while (index < inFiles.length)
		{
			// create a reader for certain document
			reader = new PdfReader(inFiles[index]);
			reader.consolidateNamedDestinations();
			
			// retrieve the total number of pages
			int pages = reader.getNumberOfPages();
			@SuppressWarnings("rawtypes")
			List bookmarks = SimpleBookmark.getBookmark(reader);
			if (bookmarks != null)
			{
				if (pageOffset != 0)
					SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
				master.addAll(bookmarks);
			}
			pageOffset += pages;
			
			if (index == 0)
			{
				// create the new document object
				document = new Document(reader.getPageSizeWithRotation(1));
				writer = new PdfCopy(document, new FileOutputStream(outFile));
				document.open();
			}
			
			// add the content
			for (int i = 1; i <= pages; i++)
			{
				PdfImportedPage page = writer.getImportedPage(reader, i);
				writer.addPage(page);
			}
			
			PRAcroForm form = reader.getAcroForm();
			if (form != null) writer.copyAcroForm(reader);
			
			index ++;
			
		}
		
		if (master.size() > 0) writer.setOutlines(master);
		
		// close the document
		document.close();
		writer.close();
	}

}
