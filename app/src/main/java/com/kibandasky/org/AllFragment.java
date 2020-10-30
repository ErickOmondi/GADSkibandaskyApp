package com.kibandasky.org;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kibandasky.org.Model.KData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {

    private RecyclerView allRecycler;
    private RecyclerView allFruitsRecyler;

    //Firebase

    private DatabaseReference mVegetableDatabase;
    private  DatabaseReference mFruitDatabase;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance(String param1, String param2) {
        AllFragment fragment = new AllFragment();
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


        View myView =  inflater.inflate(R.layout.fragment_all, container, false);

        mVegetableDatabase = FirebaseDatabase.getInstance().getReference().child("VegetableDatabase");
        mVegetableDatabase.keepSynced(true);
        mFruitDatabase = FirebaseDatabase.getInstance().getReference().child("FruitDatabase");
        mFruitDatabase.keepSynced(true);



          //Vegetables RecyclerView connection

        allRecycler = myView.findViewById(R.id.recycler_vegetable);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        allRecycler.setHasFixedSize(true);
        allRecycler.setLayoutManager(layoutManager);


        //Fruit RecyclerView connection

        allFruitsRecyler = myView.findViewById(R.id.recycler_fruit);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        layoutManager1.setReverseLayout(true);
        layoutManager1.setStackFromEnd(true);

        allFruitsRecyler.setHasFixedSize(true);
        allFruitsRecyler.setLayoutManager(layoutManager1);

        return  myView;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions <KData> VegetableOptions =
                new FirebaseRecyclerOptions.Builder<KData>()
                .setQuery(mVegetableDatabase, new SnapshotParser<KData>() {
                    @NonNull
                    @Override
                    public KData parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new KData(snapshot.child("Id").getValue().toString(),
                                snapshot.child("title").getValue().toString(),
                                snapshot.child("description").getValue().toString(),
                                snapshot.child("image").getValue().toString());
            }
        })  .build();


        FirebaseRecyclerAdapter<KData , AllFragment.VegetableViewHolder>adapterOne = new FirebaseRecyclerAdapter<KData, VegetableViewHolder>(VegetableOptions) {


            @NonNull
            @Override
            public VegetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_data, parent, false);
                VegetableViewHolder vegetableViewHolder = new VegetableViewHolder(view);
                return vegetableViewHolder;
            }

            @Override
            protected void onBindViewHolder(@NonNull VegetableViewHolder holder, int position, @NonNull final KData model) {

                holder.setTitle(model.getTitle());
                holder.setDescription(model.getDescription());
                holder.setImage(model.getImage());

                holder.myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), VegetableDetailsActivity.class);
                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("description", model.getDescription());
                        intent.putExtra("image", model.getImage());
                        startActivity(intent);
                    }
                });
            }
        };

    allRecycler.setAdapter(adapterOne);

        FirebaseRecyclerOptions <KData> fruitOptions =
                new FirebaseRecyclerOptions.Builder<KData>()
                        .setQuery(mFruitDatabase, new SnapshotParser<KData>() {
                            @NonNull
                            @Override
                            public KData parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new KData(snapshot.child("Id").getValue().toString(),
                                        snapshot.child("title").getValue().toString(),
                                        snapshot.child("description").getValue().toString(),
                                        snapshot.child("image").getValue().toString());
                            }
                        })  .build();

        FirebaseRecyclerAdapter<KData, AllFragment.fruitViewHolder> adapterTwo = new FirebaseRecyclerAdapter<KData, fruitViewHolder>(fruitOptions) {

            @NonNull
            @Override
            public fruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_data, parent, false);


                fruitViewHolder fholder = new fruitViewHolder (view);
                return fholder;
            }

            @Override
            protected void onBindViewHolder(@NonNull fruitViewHolder holder, int position, @NonNull final KData model) {

                holder.setTitle(model.getTitle());
                holder.setDescription(model.getDescription());
                holder.setImage(model.getImage());

                holder.myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), FruitDetailsActivity.class);
                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("description", model.getDescription());
                        intent.putExtra("image", model.getImage());
                        startActivity(intent);
                    }
                });
            }
        };
        allFruitsRecyler.setAdapter(adapterTwo);
    }

    public static class VegetableViewHolder extends  RecyclerView.ViewHolder{

        View myView;

        public VegetableViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;
        }
        public  void setTitle(String title){
            TextView mtitle = myView.findViewById(R.id.text_title);
            mtitle.setText(title);
        }
        public void setDescription(String description){
            TextView mDescription = myView.findViewById(R.id.txt_description);
            mDescription.setText(description);
        }
        public void setImage(final String image){
            final ImageView mImage = myView.findViewById(R.id.imageView);
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

    public static class fruitViewHolder extends RecyclerView.ViewHolder{
        View myView;

        public fruitViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;
        }

        public  void setTitle(String title){
            TextView mTitle = myView.findViewById(R.id.text_title);
            mTitle.setText(title);
        }

        public void setDescription(String description){
            TextView mDescription = myView.findViewById(R.id.txt_description);
            mDescription.setText(description);
        }

        public void setImage(final String image){

            final ImageView mImageViw = myView.findViewById(R.id.imageView);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mImageViw, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(mImageViw);
                }
            });
        }

    }

}

