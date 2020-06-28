package opensource.liamm.tallycounter.ui.main.eventhandlers;

import android.view.View;

import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModel;

public class ClickListener {

    public void onValueClick(MainViewModel mainViewModel)
    {
        mainViewModel.incrementCounter();
    }
}
