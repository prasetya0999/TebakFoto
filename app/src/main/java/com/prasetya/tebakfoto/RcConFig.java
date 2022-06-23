package com.prasetya.tebakfoto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RcConFig {
    private Context mContext;
    private UserAdapter mUserAdapter;
    public void setConfig(@NonNull RecyclerView recyclerView, Context context, List<User> users, List<String>keys){
        mContext = context;
        mUserAdapter = new UserAdapter(users,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mUserAdapter);
    };

    class UserItemView extends RecyclerView.ViewHolder{
        private TextView mNama;
        private TextView mEmail;
        //    private TextView mPass;

        private String key;

        public UserItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false));

            mNama = (TextView) itemView.findViewById(R.id.tv_nama);
            mEmail = (TextView) itemView.findViewById(R.id.tv_Email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, UserDetail.class);
                    intent.putExtra("username", key);
                    intent.putExtra("name",mNama.getText().toString());
                    intent.putExtra("email",mEmail.getText().toString());

                    mContext.startActivity(intent);
                }
            });
            //          mPass = (TextView) itemView.findViewById(R.id.tv_Pass);
        }
        public void bind(@NonNull User user, String key){
            mNama.setText(user.getName());
            mEmail.setText(user.getEmail());
//            mPass.setText(user.getPassword());
            this.key=key;
        }
    }
    class UserAdapter extends RecyclerView.Adapter<UserItemView>{
        private List<User> mUserLisst;
        private List<String> mKeys;

        public UserAdapter(List<User> mUserLisst, List<String> mKeys) {
            this.mUserLisst = mUserLisst;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public UserItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserItemView holder, int position) {
            holder.bind(mUserLisst.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mUserLisst.size();
        }
    }

}
