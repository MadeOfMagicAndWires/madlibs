package online.madeofmagicandwires.joostbremmer_pset2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TextReader {

    public static final int[] TEXT_RES_IDS = new int[] {
            R.raw.madlib0_simple,
            R.raw.madlib1_tarzan,
            R.raw.madlib2_university,
            R.raw.madlib3_clothes,
            R.raw.madlib4_dance
    };
    private final Context CURRENT_CONTEXT;
    List<Text> textList;


    /**
     * Constructor; creates a TextReader instance
     * @param context Application/Activity Context
     */
    public TextReader(@NonNull Context context) {
        this.CURRENT_CONTEXT = context;
        this.textList = new ArrayList<>();
    }

    /**
     * Constructor; creates a TextReader instance linked to a list.
     * @param context Application/Activity context
     * @param list a list of Text objects
     */
    public TextReader(@NonNull Context context, @Nullable List<Text> list) {
        this.CURRENT_CONTEXT = context;
        this.textList = list;
    }


    /**
     * Populates a list with Text objects of the raw text files
     */
    public void populateList() {
        this.textList = populateList(CURRENT_CONTEXT, TEXT_RES_IDS);
    }

    public static List<Text> populateList(Context context, int[] resourceIds) {
        List<Text> texts = new ArrayList<>();
        for(int resId : resourceIds) {
            String textFName = getTextFileName(context, resId);
            Text text = new Text(resId, textFName);
            texts.add(text);
        }
        return texts;
    }

    /**
     * Returns the file name of a resource file
     * @param resId the resource id of the file
     * @return String of the filename, sans file extension
     */
    public String getTextFileName(int resId) {
        return getTextFileName(CURRENT_CONTEXT, resId);
    }

    /**
     * Returns the file name of a resource file
     * @param context Application or activity context
     * @param resId the resource id of the file
     * @return String of the filename, sans file extension
     */
    public static String getTextFileName(Context context, int resId) {
        return context.getResources().getResourceEntryName(resId);
    }





}
