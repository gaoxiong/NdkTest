package com.snaptube.ndktest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xgao on 3/6/2016.
 */
public class TestViewHolder extends RecyclerView.ViewHolder{
  public int position;
  public String item;
  public TextView textView;

  public TestViewHolder(View view) {
    super(view);
    textView = (TextView) view.findViewById(R.id.text_view);
  }
}
