package com.kibandasky.org;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kibandasky.org.Model.KData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VegFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VegFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private RecyclerView recyclerView;

    //firebase
    private DatabaseReference mVegetableOneDB;



    public VegFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VegFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VegFragment newInstance(String param1, String param2) {
        VegFragment fragment = new VegFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View myView = inflater.inflate(R.layout.fragment_veg, container, false);

        mVegetableOneDB = FirebaseDatabase.getInstance().getReference().child("VegetableDatabase");
        mVegetableOneDB.keepSynced(true);

        recyclerView = myView.findViewById(R.id.recycler_veg_one);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<KData> options = new FirebaseRecyclerOptions.Builder<KData>()
                .setQuery(mVegetableOneDB, KData.class)
                .build();


        FirebaseRecyclerAdapter<KData, vegOneViewHolder> adapter = new FirebaseRecyclerAdapter<KData, vegOneViewHolder>(
                 options

        ) {
            @Override
            protected void onBindViewHolder(@NonNull vegOneViewHolder holder, int position, @NonNull KData model) {
                      holder.setTitle(model.getTitle());
                      holder.setDescription(model.getDescription());
                      holder.setImage(model.getImage());
            }

            @NonNull
            @Override
            public vegOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                  R.layout.cus_item_data, parent, false);
                vegOneViewHolder viewHolder = new vegOneViewHolder(view);
                 return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public static class vegOneViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public vegOneViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

        }
        public void setTitle(String title){
            TextView mtitle = mView.findViewById(R.id.post_title);
            mtitle.setText(title);
        }

        public void setDescription(String description){
            TextView mDescription = mView.findViewById(R.id.post_details);
            mDescription.setText(description);

        }

        public void setImage(final String image){
            final ImageView mImage = mView.findViewById(R.id.post_image);
            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                           Picasso.get().load(image).into(mImage);
                }
            });
        }

    }
}