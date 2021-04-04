package uz.devfest.projectfive.fragments;

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
import uz.devfest.projectfive.DetailsHotelActivity;
import uz.devfest.projectfive.MainAdapter;
import uz.devfest.projectfive.R;

public class HotelFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.recycler_view_fragment, container, false);

        int[] name = new int[Bukhara.hotels.length];
        for (int i = 0; i < name.length; i++) {
            name[i] = Bukhara.hotels[i].getName();
        }
        int[] address = new int[Bukhara.hotels.length];
        for (int i = 0; i < name.length; i++) {
            address[i] = Bukhara.hotels[i].getAddress();
        }
        int[] image = new int[Bukhara.hotels.length];
        for (int i = 0; i < image.length; i++) {
            image[i] = Bukhara.hotels[i].getImage();
        }

        MainAdapter adapter = new MainAdapter(name, address, image);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setListener(new MainAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), DetailsHotelActivity.class);
                intent.putExtra(DetailsHotelActivity.EXTRA_DETAIL_HOTEL_ID, position);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }


}
