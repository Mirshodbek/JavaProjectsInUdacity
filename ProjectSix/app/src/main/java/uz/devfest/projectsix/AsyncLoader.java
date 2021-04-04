package uz.devfest.projectsix;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class AsyncLoader extends AsyncTaskLoader<List<News>> {

    public AsyncLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        return new HttpMaker().fetchItems();
    }

    @Override
    public void deliverResult(@Nullable List<News> data) {
        super.deliverResult(data);
    }
}
