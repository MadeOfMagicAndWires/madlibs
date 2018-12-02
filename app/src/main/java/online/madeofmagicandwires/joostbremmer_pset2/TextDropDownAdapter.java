package online.madeofmagicandwires.joostbremmer_pset2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class TextDropDownAdapter extends ArrayAdapter<Text> {


    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public TextDropDownAdapter(@NonNull Context context,
                                int resource,
                                @NonNull List objects) {
        super(context, resource, objects);
    }

    /**
     * Binds the data to a View to display
     *
     * @param position The position of the item
     *                 within the adapter's data set of the item
     *                 whose view we want
     * @param convertView The old view to reuse, if possible
     * @param parent The parent that this view will eventually be attached to;
     *               must never be null
     * @return the resulting View
     */
    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        // get text object
        Text text = getItem(position);
        //create new convertView if necessary
        if(convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(
                   R.layout.dropdown_option,
                   parent,
                   false);
        }

        // populate convertView with the right data
        TextView dropDownText = convertView.findViewById(R.id.dropdownOptionText);
        dropDownText.setText(text.getTextTitle());
        dropDownText.setTag(position);

        return convertView;

    }
}
