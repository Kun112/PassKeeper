package com.anhquanha.passkeeper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.callback.OnAccountItemClickListener;
import com.anhquanha.passkeeper.model.Account;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    List<Account> accountList = new ArrayList<>();
    Context context;
    OnAccountItemClickListener listener;

    public AccountAdapter(Context context, List<Account> list, OnAccountItemClickListener listener){
        this.context = context;
        this.accountList.addAll(list);
        this.listener = listener;
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

        holder.itemView.setLongClickable(true);
        holder.categoryTv.setText(account.getCategory());
        holder.idTv.setText(account.getLoginId());
        holder.passTv.setText(account.getPassword());
        holder.timeTv.setText(account.getCreatedAt());

        holder.menuTv.setOnClickListener(v->{
            PopupMenu popup = new PopupMenu(context, holder.menuTv);
            popup.inflate(R.menu.account_long_click_menu);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.edit_account:
                        listener.onEditAccount(account);
                        break;
                    case R.id.delete_account:
                        listener.onDeleteAccount(account);
                        break;
                }
                return false;
            });
            popup.show();
        });
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
        @BindView(R.id.account_layout)
        LinearLayout accountLayout;
        @BindView(R.id.textViewOptions)
        TextView menuTv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
