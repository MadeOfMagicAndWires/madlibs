package online.madeofmagicandwires.joostbremmer_pset2;

import android.support.annotation.NonNull;
import java.io.InputStream;


/**
 * Text
 *
 * Representation the data model pertaining to any text.
 *
 * @author Joost Bremmer
 * @version 1.0
 */
public class Text {
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
        return fNameStripped;
    }

    /**
     * Opens <b>this</b> text
     * @return a inputstream of a text instance
     */
    public InputStream openText() {
        return openText(this.textId);
    }

    /**
     * Opens a Resource file
     * @param textId the resource id of a file containing a text
     * @return a inputstream object of the file linked to the resource id
     */
    public static InputStream openText(int textId) {
        return null;
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
