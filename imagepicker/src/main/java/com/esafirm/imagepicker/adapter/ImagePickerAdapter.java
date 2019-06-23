package com.esafirm.imagepicker.adapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.esafirm.imagepicker.R;
import com.esafirm.imagepicker.features.imageloader.ImageLoader;
import com.esafirm.imagepicker.features.imageloader.ImageType;
import com.esafirm.imagepicker.helper.ImagePickerUtils;
import com.esafirm.imagepicker.listeners.OnImageClickListener;
import com.esafirm.imagepicker.listeners.OnImageSelectedListener;
import com.esafirm.imagepicker.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class ImagePickerAdapter extends BaseListAdapter<ImagePickerAdapter.ImageViewHolder> {

    private List<Image> images = new ArrayList<>();
    private List<Integer> selectedIndices;

    private OnImageSelectedListener imageSelectedListener;

    public ImagePickerAdapter(Context context, ImageLoader imageLoader) {
        super(context, imageLoader);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(
                getInflater().inflate(R.layout.ef_imagepicker_item_image, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ImageViewHolder viewHolder, int position) {

        final Image image = images.get(position);

        getImageLoader().loadImage(
                image.getImageUri(),
                viewHolder.imageView,
                ImageType.GALLERY
        );

        boolean showFileTypeIndicator = false;
        String fileTypeLabel = "";
        if(ImagePickerUtils.isGifFormat(image)) {
            fileTypeLabel = getContext().getResources().getString(R.string.ef_gif);
            showFileTypeIndicator = true;
        }
        if(ImagePickerUtils.isVideoFormat(image)) {
            fileTypeLabel = getContext().getResources().getString(R.string.ef_video);
            showFileTypeIndicator = true;
        }

        viewHolder.fileTypeIndicator.setText(fileTypeLabel);
        viewHolder.fileTypeIndicator.setVisibility(showFileTypeIndicator
                ? View.VISIBLE
                : View.GONE);

        if (isSelected(position)) {
            viewHolder.selectedLayout.setVisibility(View.VISIBLE);
        } else {
            viewHolder.selectedLayout.setVisibility(View.GONE);
        }

        viewHolder.itemView.setOnClickListener(v -> {
            if (isSelected(position)) {
                setSelected(position, false);
                viewHolder.selectedLayout.setVisibility(View.GONE);
            } else {
                setSelected(position, true);
                viewHolder.selectedLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean isSelected(int position) {
        return selectedIndices.contains(position);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public void setData(List<Image> images) {
        this.images = images;
        selectedIndices = new ArrayList<>();
    }

    private boolean isAllSelected() {
        return selectedIndices.size() == images.size();
    }

    public void toggleSelectAll() {

        if (isAllSelected()) {
            removeAllSelectedSingleClick();
        } else {
            addAllSelectedSingleClick();
        }
    }

    private void setSelected(int position, boolean isSelected) {
        if (isSelected && !selectedIndices.contains(position)) {
            selectedIndices.add(position);
        } else if (!isSelected && selectedIndices.contains(position)) {
            selectedIndices.remove((Integer) position);
        }
        if (imageSelectedListener != null) {
            imageSelectedListener.onSelectionUpdate(selectedIndices.size());
        }
    }

    private void addAllSelectedSingleClick() {
        selectedIndices.clear();
        for (int i = 0; i < images.size(); i++) {
            selectedIndices.add(i);
        }
        notifyItemRangeChanged(0, images.size());

        if (imageSelectedListener != null) {
            imageSelectedListener.onSelectionUpdate(selectedIndices.size());
        }
    }

    private void removeAllSelectedSingleClick() {
        selectedIndices.clear();
        notifyItemRangeChanged(0, images.size());

        if (imageSelectedListener != null) {
            imageSelectedListener.onSelectionUpdate(selectedIndices.size());
        }
    }

    public void setImageSelectedListener(OnImageSelectedListener imageSelectedListener) {
        this.imageSelectedListener = imageSelectedListener;
    }

    public Image getItem(int position) {
        return images.get(position);
    }

    public List<Image> calculateSelectedImages() {
        ArrayList<Image> selected = new ArrayList<>();
        for (Integer index : selectedIndices) {
            if (index < 0) continue;
            Image image = images.get(index);
            selected.add(image);
        }
        return selected;
    }

    public int getSelectedIndicesSize() {
        return selectedIndices.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private View selectedLayout;
        private TextView fileTypeIndicator;
        private FrameLayout container;

        ImageViewHolder(View itemView) {
            super(itemView);

            container = (FrameLayout) itemView;
            imageView = itemView.findViewById(R.id.image_view);
            selectedLayout = itemView.findViewById(R.id.selected_layout);
            fileTypeIndicator = itemView.findViewById(R.id.ef_item_file_type_indicator);
        }
    }

}
