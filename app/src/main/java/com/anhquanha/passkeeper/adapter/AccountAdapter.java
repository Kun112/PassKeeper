package com.anhquanha.passkeeper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.model.Account;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    List<Account> accountList = new ArrayList<>();
    Context context;

    public AccountAdapter(Context context, List<Account> list){
        this.context = context;
        this.accountList.addAll(list);
    }

    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_item_recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountAdapter.ViewHolder holder, int position) {
        Account account = accountList.get(position);

        holder.categoryTv.setText(account.getCategory());
        holder.idTv.setText(account.getLoginId());
        holder.passTv.setText(account.getPassword());
        holder.timeTv.setText(account.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.categoryTv)
        TextView categoryTv;
        @BindView(R.id.idAccountTv)
        TextView idTv;
        @BindView(R.id.passAccountTv)
        TextView passTv;
        @BindView(R.id.timeAccountTv)
        TextView timeTv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
