package com.snaptube.ndktest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by xgao on 3/6/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {
  private LayoutInflater layoutInflater;
  private ArrayList<String> items = new ArrayList<>();
  private Context context;

  public RecyclerViewAdapter(Context context) {
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
    for (int i = 0; i < 20; i++) {
      items.add("test_" + i);
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.recycler_layout_item, parent, false);
    TestViewHolder viewHolder = new TestViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    TestViewHolder testViewHolder = (TestViewHolder) holder;
    testViewHolder.position = position;
    testViewHolder.item = items.get(position);
    testViewHolder.textView.setText(testViewHolder.item);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }
}
