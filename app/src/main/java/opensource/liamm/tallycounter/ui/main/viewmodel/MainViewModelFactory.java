package opensource.liamm.tallycounter.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    public static MainViewModelFactory getInstance(Application application) {
        if (instance == null) {
            synchronized (MainViewModelFactory.class) {
                if (instance == null) {
                    instance = new MainViewModelFactory(application);
                }
            }
        }

        return instance;
    }

    private final Application mApplication;
    private static MainViewModel mainViewModel;
    private static MainViewModelFactory instance;

    private MainViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (mainViewModel == null) {
            if (!modelClass.isAssignableFrom(MainViewModel.class)) {
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
            mainViewModel = new MainViewModel(mApplication);
        }

        return (T) mainViewModel;
    }
}
