package com.phunlh2001.prm392_beverages.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phunlh2001.prm392_beverages.R;
import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.data.entities.enums.OrderStatus;
import com.phunlh2001.prm392_beverages.viewmodel.OrderInfo;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>{
    Context context;
    List<Order> mListOrder;
    String type;
    public IClickItem iClickItem;
    public interface IClickItem{
        void onBindItem(List<OrderInfo> orderinfo, Order order);
    }
    public OrderHistoryAdapter(Context context, List<Order> mListOrder, String type, IClickItem iClickItem) {
        this.context = context;
        this.mListOrder = mListOrder;
        this.type = type;
        this.iClickItem = iClickItem;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(type.equals("DELIVERY")){
            view = LayoutInflater.from(context).inflate(R.layout.orderhistory_delivery_item, parent, false);
        }
        else if(type.equals("STOREPICKUP")){
            view = LayoutInflater.from(context).inflate(R.layout.orderhistory_storepickup_item, parent, false);
        }
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        Order order = mListOrder.get(position);
        User user = AppDatabase.getInstance(context.getApplicationContext()).userDao().getById(order.getUser_id());
        List<OrderInfo> mListproduct = AppDatabase.getInstance(context.getApplicationContext()).orderDetailDao().getProductNameByOrderId(order.getId());
        holder.totalPrice.setText(Double.toString(order.getTotal_price()));
        if(order.getStatus().equals(OrderStatus.DELIVERY_FAILED)){
            holder.status.setText("Delivery failed");
            holder.status.setTextColor(Color.parseColor("#FF424E"));
            holder.status.setBackgroundResource(R.drawable.custom_text_status_failed);
        }

        String proName = "";
        for(OrderInfo s: mListproduct){
            proName += s.getTitle() + "(x" + s.getQuantity() + ")" + " ";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        holder.totalPrice.setText(String.valueOf(order.getTotal_price()) + " đ");
        holder.orderDate.setText(String.valueOf(formatter.format(order.getCreateAt())));
        holder.location.setText(user.getAddress());
        holder.listProduct.setText(String.valueOf(proName));


        holder.linearLayout.setOnClickListener(v -> {
            List<OrderInfo> mListOrder = AppDatabase.getInstance(v.getContext()).orderDetailDao().getProductNameByOrderId(order.getId());
            iClickItem.onBindItem(mListOrder, order);
        });
    }

    @Override
    public int getItemCount() {
        if(mListOrder != null){
            return mListOrder.size();
        }
        return 0;
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView totalPrice, listProduct, orderDate, location;
        TextView status;
        LinearLayout linearLayout;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            totalPrice = itemView.findViewById(R.id.totalPrice);
            status = itemView.findViewById(R.id.status_order);
            listProduct = itemView.findViewById(R.id.listProduct);
            orderDate = itemView.findViewById(R.id.order_date);
            location = itemView.findViewById(R.id.location);
            linearLayout = itemView.findViewById(R.id.order_detail);
        }
    }
}
