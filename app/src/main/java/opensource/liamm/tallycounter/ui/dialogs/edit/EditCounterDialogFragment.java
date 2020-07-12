package opensource.liamm.tallycounter.ui.dialogs.edit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import opensource.liamm.tallycounter.R;
import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;
import opensource.liamm.tallycounter.databinding.EditCounterDialogFragmentBinding;

public class EditCounterDialogFragment extends DialogFragment {

    public static final String TAG = EditCounterDialogFragment.class.getSimpleName();

    private static final String BUNDLE_ARGUMENT_NAME = "name";
    private static final String BUNDLE_ARGUMENT_VALUE = "value";

    private EditCounterCallback mEditCounterCallback;
    private IntegerCounter mIntegerCounter;

    public static EditCounterDialogFragment newInstance(final @NonNull IntegerCounter integerCounter, final @NonNull EditCounterCallback editCounterCallback) {
        final EditCounterDialogFragment dialog = new EditCounterDialogFragment(integerCounter, editCounterCallback);

        final Bundle arguments = new Bundle();

        arguments.putString(BUNDLE_ARGUMENT_NAME, integerCounter.getName());
        arguments.putInt(BUNDLE_ARGUMENT_VALUE, integerCounter.getValue());

        dialog.setArguments(arguments);

        return dialog;
    }

    private EditCounterDialogFragment(final @NonNull IntegerCounter integerCounter, final @NonNull EditCounterCallback editCounterCallback) {
        this.mIntegerCounter = integerCounter;
        this.mEditCounterCallback = editCounterCallback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();

        EditCounterDialogFragmentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.edit_counter_dialog_fragment, null, false);

        binding.setIntegerCounter(mIntegerCounter);

        return new AlertDialog.Builder(activity)
                .setView(binding.getRoot())
                .setTitle(getString(R.string.dialog_edit_counter_title))
                .setPositiveButton(getString(R.string.dialog_edit_button_save), (dialog, which) -> this.mEditCounterCallback.OnSaveClick(mIntegerCounter))
                .setNegativeButton(getString(R.string.dialog_edit_button_cancel), (dialog, which) -> {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }).create();
    }

}

