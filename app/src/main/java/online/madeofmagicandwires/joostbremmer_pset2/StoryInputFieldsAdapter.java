package online.madeofmagicandwires.joostbremmer_pset2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * RecyclerView Adapter used to populate a RecyclerView with
 * Input options for placeholder replacements.
 */
public class StoryInputFieldsAdapter extends RecyclerView.Adapter<StoryInputFieldsAdapter.StoryInputFieldViewHolder> {

    /**
     * ViewHolder class used as container for replacement input fields.
     */
    public class StoryInputFieldViewHolder extends RecyclerView.ViewHolder implements
            EditText.OnEditorActionListener,
            TextWatcher,
            View.OnClickListener {

        public View root;
        public TextView inputLabel;
        public EditText inputField;
        public Button inputSubmit;

        /**
         * ViewHolder constructor.
         * @param itemView
         */
        public StoryInputFieldViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialise private members
            this.root = itemView;
            this.inputLabel = itemView.findViewById(R.id.inputLabel);
            this.inputField = itemView.findViewById(R.id.inputField);
            this.inputSubmit = itemView.findViewById(R.id.inputSubmit);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            String replacementInput = inputField.getText().toString();
            if(!replacementInput.isEmpty()) {
                submitReplacement(replacementInput);
            } else {
                showPopUp("Please fill in the placeholder properly");
            }
        }

        /**
         * Called when an action is being performed.
         *
         * @param v        The view that was clicked.
         * @param actionId Identifier of the action.  This will be either the
         *                 identifier you supplied, or {@link android.view.inputmethod.EditorInfo#IME_NULL}
         *                 EditorInfo.IME_NULL} if being called due to the enter key
         *                 being pressed.
         * @param event    If triggered by an enter key, this is the event;
         *                 otherwise, this is null.
         * @return Return true if you have consumed the action, else false.
         */
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            // In case the user presses the Done button submitReplacement replacement data to list
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                String replacement = v.getText().toString();
                if(replacement.isEmpty()) {
                    showPopUp("Please fill in the placeholder properly");
                    return true;
                } else {
                    submitReplacement(replacement);
                    return false;
                }

            }
            return false;
        }

        /**
         * Submits the data to the list of words to replace placeholders.
         * @param replacement
         */
        private void submitReplacement(String replacement) {
            int pos = getAdapterPosition();
            int replacementsCount = replacements.size();

            if(pos < 0 || pos >  replacementsCount) {
                showPopUp("Please fill in the previous fields first");

                return;
            } else {

            }
            replacements.add(pos, replacement);

            // update views
            onBindViewHolder(this, pos);

        }

        /**
         * Updates the submit Button's appearance based on whether user data has been submitted
         * @param userInputSubmitted true if data has been submitted,
         *                           false if user data has not been submitted yet,
         *                           or has been changed since.
         */
        private void showDataSubmittedButton(Boolean userInputSubmitted) {
            if(userInputSubmitted) {
                inputSubmit.setText(R.string.CHECKMARK);
                inputSubmit.setBackgroundColor(mContext.getColor(R.color.darkGreen));
                inputSubmit.setTextColor(mContext.getColor(R.color.white));
            } else {
                inputSubmit.setText(R.string.submitButton);
                inputSubmit.setBackgroundColor(mContext.getColor(R.color.btnDefault));
                inputSubmit.setTextColor(mContext.getColor(android.R.color.black));
            }

        }

        /**
         * Only required for implementation of TextWatcher; does nothing
         * @see TextWatcher
         * @param s     text before change
         * @param start start of sequence
         * @param count length of sequence
         * @param after length of sequence after it is modified by this method
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * Only required for implementation of TextWatcher; does nothing
         * @see TextWatcher
         * @param s     text on change
         * @param start length on start of change
         * @param before length before change
         * @param count length after change
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /**
         * This method is called to notify you that, somewhere within
         * <code>s</code>, the text has been changed.
         * It is legitimate to make further changes to <code>s</code> from
         * this callback, but be careful not to get yourself into an infinite
         * loop, because any changes you make will cause this method to be
         * called again recursively.
         * (You are not told where the change took place because other
         * afterTextChanged() methods may already have made other changes
         * and invalidated the offsets.  But if you need to know here,
         * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
         * to mark your place and then look up from here where the span
         * ended up.
         *
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {
            String updatedReplacement = s.toString();
            int pos = getAdapterPosition();

            // updates the button appearance based on whether the user inputted replacement has been
            // submitted yet
            if (pos < replacements.size() && updatedReplacement.equalsIgnoreCase(getReplacementItem(pos))) {
                // input is already submitted
                showDataSubmittedButton(true);
            } else  {
                // (updated) input has not been submitted yet
                showDataSubmittedButton(false);
            }
        }
    }

    /* Members of the adapter class */

    /** default layout file to inflate for this adapter **/
    public static final int DEFAULT_LAYOUT = R.layout.story_replace_input;
    /** Application context **/
    private final Context mContext;
    /** Data set to be bound to views **/
    private Story mStory;
    /** Amount of items to be created **/
    private int itemCount;
    /** LayoutInflator used to inflate items **/
    private LayoutInflater mInflator;
    /** layout file to inflate, if given **/
    private int layoutId;
    /** list of words to replace their respective placeholders in the Story's text **/
    private List<String> replacements;

    /**
     * Constructor of the Adapter
     * @param context Active context; mind that this should be an <b>Activity</b>'s
     *                context, not the Application one
     * @param story   Story object containing relevant data
     * @param layoutId Resource Id of the layoutfile to inflate into RecycleView.
     */
    public StoryInputFieldsAdapter(Context context, Story story, int layoutId) {
        this.mStory = story;
        this.itemCount = story.getPlaceholderCount();
        this.mContext = context;
        this.mInflator = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.replacements = new ArrayList<>();
    }

    /**
     * Constructor of the Adapter; used the default Layout Resource id.
     * @param context <B>Activity</B> Context
     * @param story Story object containing relevant data
     * @see #DEFAULT_LAYOUT
     */
    public StoryInputFieldsAdapter(Context context, Story story) {
        this(context, story, DEFAULT_LAYOUT);
    }

    /**
     * Creates a StoryInputFielViewHolder
     * @param parent parent RecyclerView
     * @param i position of
     * @return
     */
    @NonNull
    @Override
    public StoryInputFieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = mInflator.inflate(layoutId, parent, false);
        return new StoryInputFieldViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryInputFieldViewHolder viewHolder, int i) {

        // this is always the same
        PlaceholderType placeholder = mStory.getPlaceholder(i);
        viewHolder.inputLabel.setText(placeholder.toString() + ":");
        viewHolder.inputField.setHint("Please type " + placeholder.toString().toLowerCase());

        //this data changes on whether the user has already inputted data
        if(i < replacements.size()) {
            // user inputted replacement data is present set, set correct data
            viewHolder.inputField.setText(replacements.get(i));
            viewHolder.showDataSubmittedButton(true);


        } else {
            // user inputted replacement data is NOT present, set to defaults.
            viewHolder.inputField.setText("");
            viewHolder.showDataSubmittedButton(false);

        }

        // set eventlisteners for text change, editor action keys and submit button
        viewHolder.inputField.addTextChangedListener(viewHolder);
        viewHolder.inputSubmit.setOnClickListener(viewHolder);
        viewHolder.inputField.setOnEditorActionListener(viewHolder);


    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Applies all replacements; filling each placeholder with the corresponding replacement in
     * the replacement list.
     * @return true if the placeholders have all been filled in, false if something went wrong.
     */
    public boolean commitReplacements() {
        if(replacements.size() >= itemCount) {
            for (int i = 0; i < itemCount; i++) {
                String replacement = replacements.get(i);
                mStory.fillInPlaceholder(replacement);

            }
         } else {
            Log.d("replacement commit error", replacements.size() + " " + itemCount);
            showPopUp("Please fill in all fields");

        }
        return mStory.isFilledIn();

    }

    /**
     * Shows a toast popup message  with given text for a long duration
     * @param text the text to show in the toast
     */
    public void showPopUp(String text) {
        Toast popUp = Toast.makeText(
                mContext,
                text,
                Toast.LENGTH_LONG);
        popUp.show();
    }


    /**
     * Returns the replacement entry for each
     * @param position index of the replacement element
     * @return the element in the replacement list at the given index
     */
    public String getReplacementItem(int position) {
            return replacements.get(position);
    }


    /**
     * Returns the placeholder type of an item
     * @param position position of item
     * @return the placeholder type of the item at position
     */
    public PlaceholderType getPlaceholderItem(int position) {
        return mStory.getPlaceholder(position);
    }
}
