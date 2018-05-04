package com.anhquanha.passkeeper.callback;

import com.anhquanha.passkeeper.model.Account;

public interface OnAccountItemClickListener {
    void onEditAccount(Account account);
    void onDeleteAccount(Account account);
}
