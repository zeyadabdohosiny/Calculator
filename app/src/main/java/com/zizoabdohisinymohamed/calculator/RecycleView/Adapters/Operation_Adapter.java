package com.zizoabdohisinymohamed.calculator.RecycleView.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zizoabdohisinymohamed.calculator.R;
import com.zizoabdohisinymohamed.calculator.RecycleView.Modle.OperationModle;

import java.util.ArrayList;

public class Operation_Adapter extends RecyclerView.Adapter<Operation_Adapter.ExampleViewHolder> {
    ArrayList<OperationModle> list = new ArrayList();

    public Operation_Adapter(ArrayList<OperationModle> list) {
        this.list = list;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        TextView operation, number, slash;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            operation = itemView.findViewById(R.id.operation_cardview);
            number = itemView.findViewById(R.id.number_cardview);
            slash = itemView.findViewById(R.id.slash_cardview);


        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.operation_card_view, parent, false);
        ExampleViewHolder exm = new ExampleViewHolder(v);
        return exm;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        OperationModle modle = list.get(position);
        holder.operation.setText(modle.getOperation());
        holder.number.setText(modle.getNumber());
        holder.slash.setText(",");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
