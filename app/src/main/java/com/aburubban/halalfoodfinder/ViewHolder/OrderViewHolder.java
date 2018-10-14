package com.aburubban.halalfoodfinder.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aburubban.halalfoodfinder.Interface.ItemClickListener;
import com.aburubban.halalfoodfinder.R;

/**
 * Created by Abu Rubban on 6/5/2561.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOrderAddress;
    private ItemClickListener itemClickListener;

    public Button btnColor;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderAddress = (TextView)itemView.findViewById(R.id.order_address);
        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);

        btnColor = (Button) itemView.findViewById(R.id.btnColor1);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);



    }
}
