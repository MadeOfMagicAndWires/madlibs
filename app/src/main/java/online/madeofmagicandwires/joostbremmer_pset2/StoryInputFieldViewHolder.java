package online.madeofmagicandwires.joostbremmer_pset2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * ViewHolder class used as container for replacement input fields.
 */
public class StoryInputFieldViewHolder extends RecyclerView.ViewHolder {

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
}