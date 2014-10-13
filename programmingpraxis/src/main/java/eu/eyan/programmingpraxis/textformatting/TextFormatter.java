package eu.eyan.programmingpraxis.textformatting;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.CharBuffer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * http://programmingpraxis.com/2014/10/07/text-formatting/
 * 
 * Text formatting is a huge topic. Today’s exercise looks at a simple text formatter. 
 * Input to the formatter is a file of ascii text; 
 * the input is free-form, except that paragraphs are marked by blank lines (two successive newline). 
 * The formatter copies the file to its output, moving text from one line to the previous line to fill each line as much as possible. 
 * It is possible to specify the width of a line, but if none is given the width defaults to sixty characters.
 * 
 * Your task is to write a simple text formatter. 
 *
 */
public class TextFormatter {

    private static final String LS = System.lineSeparator();
    private static final String PS = LS + LS;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void testFormatter() throws Exception {
        assertThat("_a__b___".split("_", -1)).containsOnly("", "a", "", "b", "", "", "");

        File file = newFile("a" + LS + "b" + PS + "c ddd" + PS + "eeee" + LS + PS + "f  g");

        int width = 3;
        for (int bufferSize = 1; bufferSize < 2; bufferSize++) {
            String formatted = getFormatted(file, bufferSize, width);

            //            System.out.println("--START--");
            //            System.out.println(formatted);
            //            System.out.println("--END--");

            assertThat(formatted).isEqualTo(
                "a b" + LS + "c" + LS + "ddd" + LS + "eee" + LS + "e" + LS + "f " + LS + "g");
        }
    }

    private String getFormatted(File file, int bufferSize, int lineWidth) throws FileNotFoundException, IOException {
        Reader reader = new FileReader(file);
        Writer stringWriter = new StringWriter();
        Writer writer = new FormattedWriter(stringWriter, lineWidth);
        CharBuffer buffer = CharBuffer.allocate(bufferSize);
        while (reader.read(buffer) > -1) {
            buffer.flip();
            writer.write(buffer.toString());
        }

        reader.close();
        writer.close();
        return stringWriter.toString();
    }

    private File newFile(String string) throws Exception {
        File newFile = tmp.newFile();
        Writer writer = new PrintWriter(newFile);
        writer.write(string);
        writer.close();
        return newFile;
    }

    public static class FormattedWriter extends Writer {

        private static final int DEFAULT_LINE_LENGTH = 60;

        private Writer writer;
        private final StringBuilder sb = new StringBuilder();
        private final int WIDTH;

        public FormattedWriter(Writer writer) {
            this(writer, DEFAULT_LINE_LENGTH);
        }

        public FormattedWriter(Writer writer, int lineWidth) {
            this.writer = writer;
            this.WIDTH = lineWidth;
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            sb.append(cbuf, off, len);
            String actual = sb.toString();
            String[] paragraphs = actual.split(PS, -1);
            for (int i = 0; i < paragraphs.length; i++) {
                String paragraph = paragraphs[i];
                if (i < paragraphs.length - 1) {
                    writer.write(format(paragraph) + LS);
                } else {
                    sb.setLength(0);
                    sb.append(paragraph);
                }
            }
        }

        private String format(String paragraph) {
            String formatted = "";
            String ohneLS = paragraph.replace(LS, " ").trim();
            //            System.out.println("P: " + ohneLS);

            String[] words = ohneLS.split(" ", -1);
            String actualLine = "";
            for (String word : words) {
                //                System.out.println("  W: " + word);
                if (actualLine.length() + 1 + word.length() > WIDTH) {
                    if (actualLine.length() > 0) {
                        formatted += actualLine + LS;
                    }
                    actualLine = word;
                } else {
                    actualLine += actualLine.length() == 0 ? word : " " + word;
                }
                while (actualLine.length() > WIDTH) {
                    formatted += actualLine.substring(0, WIDTH) + LS;
                    actualLine = actualLine.substring(WIDTH);
                }
                //                System.out.println("  AL: " + actualLine + " F:" + formatted.replaceAll(LS, "*"));
            }
            formatted += actualLine;

            return formatted;
        }

        @Override
        public void flush() throws IOException {
            writer.write(format(sb.toString()));
            writer.flush();
        }

        @Override
        public void close() throws IOException {
            flush();
            writer.close();
            writer = null;
        }
    }
}