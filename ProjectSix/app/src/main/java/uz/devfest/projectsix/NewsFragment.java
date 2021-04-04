package uz.devfest.projectsix;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {
    private static final String TAG = "NewsFragment";
    private ConstraintLayout constraintLayout, constraintNoData;
    private RecyclerView mNewsRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<News> mItems = new ArrayList<>();
    private NewsAdapter adapter;
    private LoaderManager loaderManager;
    private boolean checkIn;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        checkConnection();
    }

    public void checkConnection() {
        ConnectivityManager manager = (ConnectivityManager)
                getContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            checkIn = false;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                loaderRestart();
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                loaderRestart();
            }
        } else {
            checkIn = true;
            showAlertDialog();
            loaderRestart();
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),
                R.style.MyDialogTheme);
        builder.setTitle(R.string.check_internet);

        String positiveText = getString(R.string.try_again);
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkConnection();
            }
        });

        String negativeText = getString(R.string.quit);
        builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment, container,
                false);
        constraintLayout = v.findViewById(R.id.noInternet);
        constraintLayout.setVisibility(View.GONE);
        constraintNoData = v.findViewById(R.id.no_data);
        constraintNoData.setVisibility(View.GONE);
        mNewsRecyclerView = v.findViewById(R.id.news_recycler_view);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
        swipeRefreshLayout = v.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkConnection();
                if (checkIn) {
                    constraintLayout.setVisibility(View.VISIBLE);
                } else {
                    constraintLayout.setVisibility(View.GONE);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return v;
    }

    private void setupAdapter() {
        if (isAdded()) {
            adapter = new NewsAdapter(mItems);
            mNewsRecyclerView.setAdapter(adapter);
        }
    }

    private void loaderRestart() {
        loaderManager = getLoaderManager();
        loaderManager.restartLoader(0, null, this);
    }


    private class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
        private static final String DATE_SEPARATOR = "T";
        private List<News> mNewsItems;

        public NewsAdapter(List<News> newsItems) {
            mNewsItems = newsItems;
        }

        private class NewsHolder extends RecyclerView.ViewHolder {
            private ConstraintLayout constraintLayout;
            private TextView mTitleTextView;
            private TextView mTypeTextView;
            private TextView mDateTextView;
            private TextView mTimeTextView;
            private TextView author;
            private String date;
            private String time;

            public NewsHolder(ViewGroup parent) {
                super(LayoutInflater.from(getContext())
                        .inflate(R.layout.list_item, parent, false));
                constraintLayout = itemView.findViewById(R.id.constraintLayout);
                mTitleTextView = itemView.findViewById(R.id.webTitle);
                mTypeTextView = itemView.findViewById(R.id.sectionName);
                mDateTextView = itemView.findViewById(R.id.date);
                mTimeTextView = itemView.findViewById(R.id.time);
                author = itemView.findViewById(R.id.author);
            }

            public void bindNews(final News item) {
                String originalDate = item.getWebPublicationDate();
                if (originalDate.contains(DATE_SEPARATOR)) {
                    String[] parts = originalDate.split(DATE_SEPARATOR);
                    date = parts[0];
                    time = parts[1].substring(0, 5);
                } else {
                    date = originalDate;
                }
                mDateTextView.setText(date);
                mTimeTextView.setText(time);
                mTitleTextView.setText(item.getWebTitle());
                mTypeTextView.setText(item.getSectionName());
                if (item.getAuthor() != null) {
                    author.setText(item.getAuthor());
                } else {
                    author.setVisibility(View.GONE);
                }
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = NewsPageActivity
                                .newIntent(getActivity(), item.getNewsPageUri());
                        startActivity(i);
                    }
                });

            }
        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            return new NewsHolder(viewGroup);
        }

        @Override
        public void onBindViewHolder(NewsHolder newsHolder, int position) {
            News newsItem = mNewsItems.get(position);
            newsHolder.bindNews(newsItem);
        }

        @Override
        public int getItemCount() {
            return mNewsItems.size();
        }
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        if (data != null && data.size() > 0) {
            adapter = new NewsAdapter(data);
            mNewsRecyclerView.setAdapter(adapter);
            mNewsRecyclerView.setVisibility(View.VISIBLE);
            constraintNoData.setVisibility(View.GONE);
        } else {
            constraintNoData.setVisibility(View.VISIBLE);
            mNewsRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
    }

}
