package online.madeofmagicandwires.joostbremmer_pset2;

import android.support.annotation.NonNull;
import java.io.InputStream;
import java.io.Serializable;


/**
 * Text
 *
 * Representation the data model pertaining to any text.
 *
 * @author Joost Bremmer
 * @version 1.0
 */
public class Text implements Serializable {
    private final int textId;
    private final String textTitle;
    private final String textFileName;
    private InputStream textInput;

    /**
     * Constructor; creates a instance of Text
     * @param resId the resource id of a text
     * @param textFileName the fileName of a text
     */
    public Text(@NonNull int resId, @NonNull String textFileName) {
        this.textId  = resId;
        this.textFileName = textFileName;
        this.textTitle = fileNameToTitle(textFileName);
        this.textInput = null;
    }

    /**
     * Returns the id of a text
     * @return the resource id of a text
     */
    public int getTextId() {
        return textId;
    }

    /**
     * Returns a text's title
     * @return the title of a text
     */
    public String getTextTitle() {
        return textTitle;
    }

    /**
     * Returns a filename of a title file
     * @return the name of the file containing a text
     */
    public String getTextFileName() {
        return this.textFileName;
    }

    /**
     * Links a inputstream of a Text
     * @param textInput InputStream of the file containing this Texts
     */
    public void setTextInput(InputStream textInput) {
        this.textInput = textInput;
    }

    /**
     * Returns the inputstream of a text
     * @return InputStream of the text's file.
     */
    public InputStream getTextInput() {
        return textInput;
    }

    /**
     * Returns the name of <b>this</b> text
     * @return the file name of a text instance
     */
    public String fileNameToTitle() {
        return fileNameToTitle(this.textFileName);
    }

    /**
     * Returns a parsed title of a text based on its file name.
     * @param fName the name of the file containing a text
     * @return the title of a text, as interpreted by its file name
     * @see "https://stackoverflow.com/a/1086134" for title case-ing.
     */
    public static  String fileNameToTitle(String fName) {
        if(fName == null || fName.isEmpty()) {
            return "";
        }
        // Strip fileName of prefix and file ext.
        String fNameStripped =  fName.replaceAll(
                "madlib\\d_",
                "")
                .replace(
                        "\\.\\w{3}$",
                        "");
        if(fNameStripped.isEmpty()) {
            return "";
        }

        StringBuilder titleBuilder = new StringBuilder();
        Boolean capitaliseNext = true;
        for(char c : fNameStripped.toCharArray()) {
            if(Character.isSpaceChar(c)) {
                capitaliseNext = true;
            } else if(capitaliseNext) {
                c = Character.toTitleCase(c);
                capitaliseNext = false;
            }
             titleBuilder.append(c);
        }

        return titleBuilder.toString();
    }

    /**
     * Returns a string representing <b>this</b> Text
     * @return a Human Readable string representing a Text instance
     */
    @NonNull
    @Override
    public String toString() {
        return this.textTitle + " : " + this.textId;
    }
}
