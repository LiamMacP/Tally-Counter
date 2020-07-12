package opensource.liamm.tallycounter.ui.dialogs.edit;

import opensource.liamm.tallycounter.data.db.entity.IntegerCounter;

public interface EditCounterCallback {
    void OnSaveClick(IntegerCounter integerCounter);
}
