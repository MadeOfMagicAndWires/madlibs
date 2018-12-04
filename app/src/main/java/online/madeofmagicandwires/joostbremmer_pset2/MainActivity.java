package online.madeofmagicandwires.joostbremmer_pset2;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String TEXT_EXTRA_NAME = "textExtra";

    /** Resource id of the Spinner dropdown **/
    private final int dropDownId = R.id.dropdown;

    /** list of text objects used by Spinner dropdown */
    private List<Text> texts;
    /** TextAdapter used by Spinner dropdown **/
    private TextDropDownAdapter adapter;


    /**
     * On Click listener for the Submit button;
     * gets the selected text from the dropdown and passes it on to the next Activity
     * @see #initSubmitButton()
     * @see TextDropDownAdapter
     * @see #populateDropdown()
     *
     */
    public class TextSubmitButton implements View.OnClickListener{

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Spinner dropDown = findViewById(dropDownId);
            Text txt = (Text) dropDown.getSelectedItem();
            if(txt != null) {
                Intent intent = new Intent(v.getContext(), StoryActivity.class);
                intent.putExtra(TEXT_EXTRA_NAME, txt);
                startActivity(intent);
            } else {
                Snackbar sb = Snackbar.make(v, "Please select a text", Snackbar.LENGTH_SHORT);
                sb.show();
            }

        }
    }

    /**
     * Populates the text dropdown  Spinner with data and sets listeners
     * @see TextReader
     * @see TextDropDownAdapter
     * @see #texts
     */
    public void populateDropdown() {
        texts = TextReader.populateList(this, TextReader.TEXT_RES_IDS);
        adapter = new TextDropDownAdapter(
                this,
                R.layout.dropdown_option,
                texts,
                R.layout.support_simple_spinner_dropdown_item);
        Spinner dropdown = findViewById(dropDownId);
        dropdown.setAdapter(adapter);
    }

    /**
     * Sets relevant listeners to the submit button; dropdown must be initialsed and populated
     * @see TextSubmitButton
     * @see #populateDropdown()
     *
     */
    public void initSubmitButton() {
        Button submit = findViewById(R.id.madLibButton);
        submit.setOnClickListener(new TextSubmitButton());
    }

    /**
     * OnCreate method
     * @param savedInstanceState bundle of saved data from last saved instance
     * @see AppCompatActivity#onCreate(Bundle)
     * @see AppCompatActivity#onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateDropdown();
        initSubmitButton();
    }


}
