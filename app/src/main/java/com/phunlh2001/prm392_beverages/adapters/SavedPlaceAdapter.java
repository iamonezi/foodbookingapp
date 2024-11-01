package com.phunlh2001.prm392_beverages.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phunlh2001.prm392_beverages.R;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.helper.ISavedPlace;
import com.phunlh2001.prm392_beverages.viewmodel.AddressViewModel;

import java.util.List;

public class SavedPlaceAdapter extends RecyclerView.Adapter<SavedPlaceAdapter.SavedPlaceViewHolder> {
    private List<AddressViewModel> addressList;
    private final ISavedPlace iClickItem;

    public SavedPlaceAdapter(ISavedPlace iClickItem) {
        this.iClickItem = iClickItem;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<AddressViewModel> list) {
        addressList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SavedPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        return new SavedPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPlaceViewHolder holder, int position) {
        AddressViewModel address = addressList.get(position);
        if (address == null) return;

        holder.tvName.setText(address.getName());
        holder.tvPhone.setText(address.getPhone());
        holder.tvAddress.setText(address.getAddress());

        holder.btnEdit.setOnClickListener(v -> iClickItem.onClickEditAddress(address));
        holder.btnDelete.setOnClickListener(v -> iClickItem.onClickDeleteAddress(address));
        holder.btnClickItem.setOnClickListener(v -> iClickItem.onClickItemAddress(address));
    }

    @Override
    public int getItemCount() {
        return addressList != null ? addressList.size() : 0;
    }

    public static class SavedPlaceViewHolder extends RecyclerView.ViewHolder {
        TextView tvAddress, tvName, tvPhone;
        ImageView btnEdit, btnDelete;
        LinearLayout btnClickItem;

        public SavedPlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById(R.id.addressSelector_address);
            tvName = itemView.findViewById(R.id.addressSelector_name);
            tvPhone = itemView.findViewById(R.id.addressSelector_numberPhone);
            btnEdit = itemView.findViewById(R.id.btn_address_edit);
            btnDelete = itemView.findViewById(R.id.btn_address_delete);
            btnClickItem = itemView.findViewById(R.id.addressSelector_item);
        }
    }
}
