/**
 * Story.java
 * Madlibs
 *
 * Created by Hella Haanstra on April 15, 2016
 *
 * Based on: CS 193A, Marty Stepp
 *
 * This class represents a Mad Libs story that comes from a text file.
 * You can construct it and pass an input stream or Scanner to read the story text.
 * After constructing it, you can ask it for each placeholder by calling
 *  getNextPlaceholder, then filling in that placeholder by calling fillInPlaceholder.
 * To see how many placeholders are left, use the methods
 *  getPlaceholderRemainingCount and isFilledIn.
 * You can get the story's text by calling its toString method.
 * A Story is Serializable, so it can be packed into an Intent as "extra" data.
 */

// !YOU MAY WANT TO CHANGE THE PACKAGE BELOW SO THAT IT MATCHES YOUR PROJECT'S PACKAGE!
package online.madeofmagicandwires.joostbremmer_pset2;

import java.io.*;
import java.util.*;

public class Story implements Serializable {

    /** properly formed HTML header **/
    public static final String HTML_START = "<!DOCTYPE html><html lang='en'><head><title>Madlibs</title></head><body><p>";
    public static final String HTML_END = "</p></body></html>";


    /** text of the story **/
    private String text;
    /** list of placeholders to fill in **/
    private List<PlaceholderType> placeholders;
    /** list of standardised placeholder types **/
    //private List<PlaceholderType> placeholderTypes;
    /** number of placeholders that have been filled in **/
    private int filledIn;
    /** set to true to surround placeholders with <b>bold</b> tags **/
    private boolean htmlMode;

    {
        // instance initializer; runs before any constructor
        text = "";
        placeholders = new ArrayList<PlaceholderType>();
        // placeholderTypes = new ArrayList<PlaceholderType>();
        filledIn = 0;
        htmlMode = true;
        clear();
    }

    /**
     * constructs a new Story reading its text from the given input stream
     * @return Story instance containing text from InputStream
     */
    public Story(InputStream stream) {
        read(stream);
    }

    /** resets the story back to an empty initial state */
    public void clear() {
        text = "";
        placeholders.clear();
        filledIn = 0;
    }

    /**
     * replaces the next unfilled placeholder with the given word
     * @param word
     */
    public void fillInPlaceholder(String word) {
        if (!isFilledIn()) {
            text = text.replace("{" + filledIn + "}", word);
            filledIn++;
        }
    }

    /**
     * returns the next placeholder
     * @return the next placeholder word,
     *         or "" if all placeholders have been filled in
     */
    public PlaceholderType getNextPlaceholder() {
        if (isFilledIn()) {
            return PlaceholderType.NO_NEXT_PLACEHOLDER;
        } else {
            return placeholders.get(filledIn);
        }
    }

    /**
     * Returns a specific placeholder's type
     * @param position placeholders position in the placeholder list
     * @return the type of the placeholder saved in position
     * @see PlaceholderType
     */
    public PlaceholderType getPlaceholder(int position) {
        if(position < placeholders.size()) {
            return placeholders.get(Math.abs(position));
        } else {
            return PlaceholderType.NO_NEXT_PLACEHOLDER;
        }
    }

    /**
     * returns total number of placeholders in the story
     * @return int representing the sum of placeholders
     */
    public int getPlaceholderCount() {
        return placeholders.size();
    }

    /**
     * returns how many placeholders still need to be filled in
     * @return int representing the remaining amount of placeholders.
     */
    public int getPlaceholderRemainingCount() {
        return placeholders.size() - filledIn;
    }

    /**
     * returns whether all placeholders have been filled in
     * @return true if all placeholders have been filed in, false if not
     */
    public boolean isFilledIn() {
        return filledIn >= placeholders.size();
    }

    /**
     * reads initial story text from the given input stream
     * @param stream inputstream of the text
     */
    public void read(InputStream stream) {
        read(new Scanner(stream));
    }

    /**
     * reads initial story text from the given Scanner
     * @param input inputstream containing the text
     */
    private void read(Scanner input) {

        // Never use concat in loops; use StringBuilder instead.
        StringBuilder txtBuilder = new StringBuilder(text);

        // if html is on, add properly formed html code up to <p>
        if(htmlMode) {
            txtBuilder.append(HTML_START);
        }

        while (input.hasNext()) {
            String word = input.next();

            if (word.startsWith("<") && word.endsWith(">")) {
                // a placeholder; replace with e.g. "{0}" so I can find/replace it easily later
                // (make them bold so that they stand out!)
                // Update: We've changed "<0>" to "{0}"
                //         to avoid confusion with HTML tags.
                if (htmlMode) {
                     txtBuilder.append(" <b>{");
                     txtBuilder.append(placeholders.size());
                     txtBuilder.append("}</b>");
                } else {
                    txtBuilder.append(" {");
                    txtBuilder.append(placeholders.size());
                    txtBuilder.append("}");
                }
                // "<plural-noun>" becomes "plural noun"
                String placeholder = word.substring(1, word.length() - 1)
                        .replace("-", " ");
                placeholders.add(PlaceholderType.parsePlaceHolderType(placeholder));
            } else {
                // a regular word; just concatenate
                if (txtBuilder.length() > 0) {
                    txtBuilder.append(" ");
                }
                txtBuilder.append(word);
            }

        }


        // lastly, when html is on, close all remaining html tags
        if(htmlMode) {
            txtBuilder.append(HTML_END);
        }

        this.text = txtBuilder.toString();
    }

    /** returns story text */
    public String toString() {
        return text;
    }
}
