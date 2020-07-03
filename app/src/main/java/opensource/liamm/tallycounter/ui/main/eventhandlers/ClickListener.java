package opensource.liamm.tallycounter.ui.main.eventhandlers;

import opensource.liamm.tallycounter.ui.main.viewmodel.MainViewModel;

public class ClickListener {

    public void onIncrementClick(MainViewModel mainViewModel)
    {
        mainViewModel.incrementCounter();
    }

    public void onDecrementClick(MainViewModel mainViewModel)
    {
        mainViewModel.decrementCounter();
    }
}
