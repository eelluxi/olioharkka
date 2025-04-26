package com.example.olioharkka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonViewAdapter  extends RecyclerView.Adapter<LutemonViewHolder>{
    private Context context;
    private ArrayList<Lutemon> lutemons;
    private boolean NoActive = false;


    public LutemonViewAdapter(ArrayList<Lutemon> lutemons, Context context) {
        this.lutemons = lutemons;
        this.context = context;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        holder.nameText.setText(lutemons.get(position).getName());
        holder.hpText.setText("HP: " +Integer.toString(lutemons.get(position).getHpmax()) + "/" + Integer.toString(lutemons.get(position).getHp()));
        holder.lvlText.setText("LVL: "+Integer.toString(lutemons.get(position).getLvl()));
        holder.statText.setText("AP/DP: "+Integer.toString(lutemons.get(position).getAp()) + "/" + Integer.toString(lutemons.get(position).getDp()));
        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());

        /* Tämä ei toimi. Vaihetaan TextView Buttoniin.         Jälkeenpäin katsottuna olisin ehkä saanut toimimaan.
        holder.chooseText.setOnClickListener(view -> { // "Valitse" button sets chosen lutemon as the active lutemon in home and battle activities
        int pos = holder.getAdapterPosition();
        ((HomeActivity)context).setActiveLutemon(LutemonStorage.getInstance().getLutemon(pos));
        });

        holder.releaseText.setOnClickListener(view -> { // "Vapauta" button removes chosen lutemon from the list, unless it is the only one
            if (lutemons.size() > 1){
            int pos = holder.getAdapterPosition();
            LutemonStorage.getInstance().removeLutemon(pos);
            notifyItemRemoved(pos);
            }
        });
         */
        holder.chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                if (LutemonStorage.getInstance().getLutemon(pos).getID() != ((HomeActivity)context).getActiveLutemonID()) {
                    ((HomeActivity) context).setActiveLutemon(LutemonStorage.getInstance().getLutemon(pos));
                    ((HomeActivity) context).setChosenInfo();
                }
            }
        });

        holder.releaseButton.setOnClickListener(view -> { // "Vapauta" button removes chosen lutemon from the list, unless it is the only one
            if (lutemons.size() > 1) {
                int pos = holder.getAdapterPosition();
                System.out.println("Verrataan" + LutemonStorage.getInstance().getLutemon(pos).getID() + " ja " + ((HomeActivity)context).getActiveLutemonID());
                if (LutemonStorage.getInstance().getLutemon(pos).getID() == ((HomeActivity)context).getActiveLutemonID()){ // Checks if active lutemon is removed
                    System.out.println("Ovat samoja");
                    NoActive = true;
                }

                LutemonStorage.getInstance().removeLutemon(pos);
                notifyItemRemoved(pos);

                if (NoActive == true){ // If active lutemon is removed, set first lutemon as active
                    System.out.println("Active lutemon removed");
                    ((HomeActivity)context).setActiveLutemon(LutemonStorage.getInstance().getLutemon(0));
                    ((HomeActivity)context).setChosenInfo();
                    NoActive = false;
                }

            }
        });

        }


        /*
        public void removeButton(){
        int pos = holder.getAdapterPosition();
        LutemonStorage.getInstance().removeLutemon(pos);
        notifyItemRemoved(pos);
        }
         */


    @Override
    public int getItemCount() {
        return lutemons.size();
    }



}
