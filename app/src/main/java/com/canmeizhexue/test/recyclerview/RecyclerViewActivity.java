package com.canmeizhexue.test.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
//来源于http://blog.csdn.net/lmj623565791/article/details/45059587
public class RecyclerViewActivity extends BaseActivity
{

	private RecyclerView mRecyclerView;
	private List<String> mDatas;
	private HomeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_recyclerview);

		initData();

		mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
		mAdapter = new HomeAdapter(this, mDatas);

		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
				StaggeredGridLayoutManager.VERTICAL));
		mRecyclerView.setAdapter(mAdapter);
		//添加分割线
		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
		// 设置item动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		initEvent();

	}

	private void initEvent()
	{
		mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener()
		{
			@Override
			public void onItemClick(View view, int position)
			{
				Toast.makeText(RecyclerViewActivity.this, position + " click",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position)
			{
				Toast.makeText(RecyclerViewActivity.this, position + " long click",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	protected void initData()
	{
		mDatas = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++)
		{
			mDatas.add("" + (char) i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.id_action_add:
			mAdapter.addData(1);
			break;
		case R.id.id_action_delete:
			mAdapter.removeData(1);
			break;
		case R.id.id_action_gridview:
			mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
			break;
		case R.id.id_action_listview:
			mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
			break;
		case R.id.id_action_horizontalGridView:
			mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
					StaggeredGridLayoutManager.HORIZONTAL));
			break;

		case R.id.id_action_staggeredgridview:
			Intent intent = new Intent(this , StaggeredGridLayoutActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}

}
