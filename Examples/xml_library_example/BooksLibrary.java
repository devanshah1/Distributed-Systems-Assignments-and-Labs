import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory; 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

public class BooksLibrary extends DefaultHandler {

	static OutputStreamWriter out;
	
	public static void main (String argv [])
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {

			out = new OutputStreamWriter (System.out, "UTF8");
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse( new File("library.xml"), new BooksLibrary() );

		} catch (Throwable err) {
			err.printStackTrace ();
		}
	}
    //===========================================================
    // Methods in SAX DocumentHandler 
    //===========================================================

    public void startDocument ()
    throws SAXException
    {
        showData ("<?xml version='1.0' encoding='UTF-8'?>");
        newLine();
    }

    public void endDocument ()
    throws SAXException
    {
        try {
            newLine();
            out.flush ();
        } catch (IOException e) {
            throw new SAXException ("I/O error", e);
        }
    }


    public void startElement (String uri, String localName, String qName, Attributes attrs)
    throws SAXException
    {
        showData ("<"+qName);
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength (); i++) {
                showData (" ");
                showData (attrs.getLocalName(i)+"=\""+attrs.getValue (i)+"\"");
            }
        }
        showData (">");
    }

    public void endElement (String name)
    throws SAXException
    {
        showData ("</"+name+">");
    }

    public void characters (char buf [], int offset, int len)
    throws SAXException
    {
        String s = new String(buf, offset, len);
        showData (s);
    }

    //===========================================================
    // Helpers Methods
    //===========================================================

    // Wrap I/O exceptions in SAX exceptions, to
    // suit handler signature requirements
    private void showData (String s)
    throws SAXException
    {
        try {
            out.write (s);
            out.flush ();
        } catch (IOException e) {
            throw new SAXException ("I/O error", e);
        }
    }

    // Start a new line
    private void newLine ()
    throws SAXException
    {
        String lineEnd =  System.getProperty("line.separator");
        try {
            out.write (lineEnd);
        } catch (IOException e) {
            throw new SAXException ("I/O error", e);
        }
    }

}