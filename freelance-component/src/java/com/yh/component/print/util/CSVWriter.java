package com.yh.component.print.util;

import java.io.*;
import java.nio.charset.Charset;

/**
 * csv writer
 * 
 * @author eric
 */
public class CSVWriter
{
    public static class FinalizedException extends Exception
    {

        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 1L;

        public FinalizedException()
        {
            super("Resources have already been freed.");
        }
    }

    private PrintWriter outputStream;
    private String fileName;
    private boolean firstColumn;
    private char delimiter;
    private char textQualifier;
    private boolean useTextQualifier;
    private Charset charset;
    private String singleQualifier;
    private String doubleQualifier;
    private boolean initialized;
    private boolean closed;
    
    public CSVWriter(String s, char c, Charset charset1)
    {
        this();
        if(s == null)
            throw new IllegalArgumentException("File name can not be null.");
        if(charset1 == null)
        {
            throw new IllegalArgumentException("Charset can not be null.");
        } else
        {
            fileName = s;
            delimiter = c;
            charset = charset1;
            return;
        }
    }

    public CSVWriter(String s)
    {
        this(s, ',', Charset.forName("ISO-8859-1"));
    }

    public CSVWriter(Writer writer, char c)
    {
        this();
        if(writer == null)
        {
            throw new IllegalArgumentException("Output stream can not be null.");
        } else
        {
            outputStream = new PrintWriter(writer);
            delimiter = c;
            initialized = true;
            return;
        }
    }

    public CSVWriter(OutputStream outputstream, char c, Charset charset1)
    {
        this(((Writer) (new OutputStreamWriter(outputstream, charset1))), c);
    }

    private CSVWriter()
    {
        outputStream = null;
        fileName = null;
        firstColumn = true;
        delimiter = ',';
        textQualifier = '"';
        useTextQualifier = true;
        charset = null;
        singleQualifier = null;
        doubleQualifier = null;
        initialized = false;
        closed = false;
        initTextQualifier();
    }

    public char getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(char c)
    {
        delimiter = c;
    }

    public char getTextQualifier()
    {
        return textQualifier;
    }

    public void setTextQualifier(char c)
    {
        textQualifier = c;
    }

    public boolean getUseTextQualifier()
    {
        return useTextQualifier;
    }

    public void setUseTextQualifier(boolean flag)
    {
        useTextQualifier = flag;
    }

    public void write(String s, boolean flag)
        throws FinalizedException, IOException
    {
        checkClosed();
        checkInit();
        if(s == null)
            s = "";
        int i = s.length();
        if(firstColumn)
        {
            if(i == 0)
                outputStream.write(doubleQualifier);
            firstColumn = false;
        } else
        {
            outputStream.write(delimiter);
        }
        if(i > 0)
        {
            boolean flag1 = false;
            if(!flag)
                s = s.trim();
            else
            if(useTextQualifier)
            {
                char c = s.charAt(0);
                if(c == ' ' || c == '\t')
                    flag1 = true;
                if(!flag1 && i > 1)
                {
                    char c1 = s.charAt(i - 1);
                    if(c1 == ' ' || c1 == '\t')
                        flag1 = true;
                }
            }
            if(!flag1 && (s.indexOf(delimiter) > -1 || s.indexOf('\n') > -1 || s.indexOf('\r') > -1 || s.indexOf(textQualifier) > -1))
                flag1 = true;
            if(flag1)
            {
                outputStream.write(textQualifier);
                s = s.replaceAll(singleQualifier, doubleQualifier);
            }
            outputStream.write(s);
            if(flag1)
                outputStream.write(textQualifier);
        }
    }

    public void write(String s)
        throws FinalizedException, IOException
    {
        write(s, false);
    }

    private void initTextQualifier()
    {
        singleQualifier = "";
        singleQualifier += textQualifier;
        doubleQualifier = singleQualifier + singleQualifier;
    }

    public void endRecord()
        throws FinalizedException, IOException
    {
        checkClosed();
        checkInit();
        outputStream.println();
        firstColumn = true;
    }

    private void checkInit()
        throws IOException
    {
        if(!initialized)
        {
            if(fileName != null)
                outputStream = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), charset));
            initialized = true;
        }
    }

    public void flush()
    {
        outputStream.flush();
    }

    public void close()
    {
        if(!closed)
        {
            close(true);
            closed = true;
        }
    }

    protected void close(boolean flag)
    {
        if(flag)
            charset = null;
        try
        {
            if(initialized)
                outputStream.close();
            outputStream = null;
        }
        catch(Exception exception) { }
    }

    private void checkClosed()
        throws FinalizedException
    {
        if(closed)
            throw new FinalizedException();
        else
            return;
    }

    protected void finalize()
    {
        close(false);
    }

}