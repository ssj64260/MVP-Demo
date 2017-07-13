package com.cxb.mvp_project.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.config.API;
import com.cxb.mvp_project.config.Config;
import com.cxb.mvp_project.model.GankNewsBean;
import com.cxb.mvp_project.utils.DateTimeUtils;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderFactory;
import com.cxb.mvp_project.widget.imageloader.ImageLoaderWrapper;

import java.util.Date;
import java.util.List;

/**
 * 干货资讯列表适配器
 */

public class GankNewsAdapter extends RecyclerView.Adapter {

    private OnListClickListener mOnListClickListener;

    private Context mContext;
    private List<GankNewsBean> mList;
    private LayoutInflater mInflater;
    private int mItemLayout;
    private ImageLoaderWrapper mImageLoader;
    private final int mPlaceHolder = R.drawable.ic_placeholder;

    public GankNewsAdapter(Context context, List<GankNewsBean> list, String type) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);

        mImageLoader = ImageLoaderFactory.getLoader();

        final String welfare = context.getString(R.string.tab_title_welfare);
        final String video = context.getString(R.string.tab_title_rest_video);
        if (welfare.equals(type)) {
            mItemLayout = R.layout.item_gank_welfare;
        } else if (video.equals(type)) {
            mItemLayout = R.layout.item_gank_video;
        } else {
            mItemLayout = R.layout.item_gank_image_news;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(mInflater.inflate(mItemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindNoHeadItem((ListViewHolder) holder, position);
    }

    private void bindNoHeadItem(ListViewHolder holder, final int position) {
        final GankNewsBean gankNews = mList.get(position);
        final List<String> imageList = gankNews.getImages();
        final String description = gankNews.getDesc();
        final String datetime = gankNews.getPublishedAt();
        final Date date = DateTimeUtils.StringToDateTime(datetime);
        final String author = gankNews.getWho();

        if (holder.ivPicture != null) {
            if (mItemLayout == R.layout.item_gank_welfare) {
                final String imageUrl = API.getCankImageURL(gankNews.getUrl(), Config.GANK_IMAGE_MAX_WIDTH);
                mImageLoader.loadImageFitCenter(mContext, holder.ivPicture, imageUrl, 0, R.drawable.ic_placeholder);
            } else {
                final String imageUrl;
                if (imageList != null && imageList.size() > 0) {
                    imageUrl = API.getCankImageURL(imageList.get(0), Config.GANK_IMAGE_MAX_WIDTH);
                } else {
                    imageUrl = "";
                }
                mImageLoader.loadImageCenterCrop(mContext, holder.ivPicture, imageUrl);
            }
        }

        if (holder.tvDescription != null) {
            holder.tvDescription.setText(description);
        }

        if (date != null) {
            holder.tvDatetime.setText(DateTimeUtils.getFriendDatetime(date));
        }
        holder.tvAuthor.setText(author);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mClick);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private ImageView ivPicture;
        private TextView tvDescription;
        private TextView tvDatetime;
        private TextView tvAuthor;

        private ListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivPicture = (ImageView) itemView.findViewById(R.id.iv_picture);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            tvDatetime = (TextView) itemView.findViewById(R.id.tv_datetime);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
        }
    }

    public void setOnListClickListener(OnListClickListener onListClickListener) {
        this.mOnListClickListener = onListClickListener;
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mOnListClickListener != null) {
                final int position = (int) view.getTag();
                final View clickView;
                if (mItemLayout == R.layout.item_gank_welfare) {
                    clickView = view.findViewById(R.id.iv_picture);
                } else {
                    clickView = view;
                }
                mOnListClickListener.onItemClick(clickView, position);
            }
        }
    };
}
