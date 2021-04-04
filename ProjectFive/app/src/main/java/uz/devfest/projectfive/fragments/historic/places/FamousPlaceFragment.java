package uz.devfest.projectfive.fragments.historic.places;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uz.devfest.projectfive.Bukhara;
import uz.devfest.projectfive.DetailsActivity;
import uz.devfest.projectfive.MainAdapter;
import uz.devfest.projectfive.R;

public class FamousPlaceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.recycler_view_fragment, container, false);


        int[] name = new int[Bukhara.bukhara.length];
        for (int i = 0; i < name.length; i++) {
            name[i] = Bukhara.bukhara[i].getName();
        }
        int[] address = new int[Bukhara.bukhara.length];
        for (int i = 0; i < name.length; i++) {
            address[i] = Bukhara.bukhara[i].getAddress();
        }
        int[] image = new int[Bukhara.bukhara.length];
        for (int i = 0; i < image.length; i++) {
            image[i] = Bukhara.bukhara[i].getImage();
        }

        MainAdapter adapter = new MainAdapter(name, address, image);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setListener(new MainAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.EXTRA_DETAIL_ID, position);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
