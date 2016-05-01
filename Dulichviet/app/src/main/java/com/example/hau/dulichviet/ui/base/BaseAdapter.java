package com.example.hau.dulichviet.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public class BaseAdapter<V,VH extends BaseHolder> extends RecyclerView.Adapter<VH>{
    protected LayoutInflater inflater;
    protected List<V> dataSource = Collections.emptyList();

    public BaseAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindData(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(List<V> dataSource) {
        try{
            this.dataSource =new ArrayList<>(dataSource);
            notifyDataSetChanged();
        }catch (IllegalStateException  e){

        }
    }

    public List<V> getDataSource() {
        return this.dataSource;
    }

    public void appendItem(V item){
        if (this.dataSource.isEmpty()){
            this.dataSource = new ArrayList<>();
        }
        this.dataSource.add(item);
        notifyItemInserted(getItemCount());
    }

    public void removeAtPosition(int position){
        if (this.dataSource.size() > position){
            this.dataSource.remove(position);
            notifyItemRangeRemoved(position,1);
        }
    }

    public void appendItems(List<V> items){
        if (this.dataSource.isEmpty()){
            setDataSource(items);
        }else {
            this.dataSource.addAll(items);
            notifyItemRangeInserted(getItemCount() - 1 ,items.size());
        }
    }

    public void addItemAtFirst(V item){
        if (this.dataSource.isEmpty()){
            this.dataSource = new ArrayList<>();
        }
        this.dataSource.add(0,item);
        notifyItemInserted(0);
    }

    public void addAtFirstAndRemoveEnd(V item){
        if (this.dataSource.isEmpty()){
            this.dataSource = new ArrayList<>();
        }
        this.dataSource.add(0,item);
        notifyItemInserted(0);
        this.dataSource.remove(getItemCount() - 1);
        notifyItemRemoved(getItemCount() - 1);
    }



}
