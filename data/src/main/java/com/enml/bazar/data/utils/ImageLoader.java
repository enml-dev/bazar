package com.enml.bazar.data.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;
import com.squareup.pollexor.Thumbor;
import com.squareup.pollexor.ThumborUrlBuilder;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.util.Date;

import androidx.annotation.NonNull;

public class ImageLoader {

    private static Thumbor thumbor = Thumbor.create("http://69.89.12.148:8888/");

    private static File getLocalImage(final String id, final String size, final int roundCorner) {
        final File direct = new File(
                Environment.getExternalStorageDirectory() + "/bazar/Images/");

        boolean existDir = direct.exists();

        if (!direct.exists()) {
            existDir = direct.mkdirs();
        }

        if (existDir) {
            File[] files = direct.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (file.getName().startsWith(id + roundCorner + size)) {
                        return true;
                    }
                    return false;
                }
            });

            File file = null;
            if (files != null && files.length > 0) {
                file = files[0];
            }

            return file;
        }
        return null;
    }

    private static void saveImage(Bitmap image, @NonNull String id, final String size, final int roundCorner) {
        final File direct = new File(
                Environment.getExternalStorageDirectory() + "/bazar/Images/");

        boolean existDir = direct.exists();

        if (!direct.exists()) {
            existDir = direct.mkdirs();
        }

        if (existDir) {

            File file = new File(direct, id + roundCorner + size + new Date().getTime());

            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(file);

                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.flush();
                fOut.close();

                File[] files = direct.listFiles();

                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().startsWith(id + roundCorner + size)
                            && !files[i].getName().equalsIgnoreCase(file.getName())) {
                        files[i].delete();
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void loadImage(final Context context, final String imageId, String imageURL, final String size, final int roundCorner, final ImageBitmapResult imageBitmapResult) {
        loadImage(context, imageId, imageURL, size, roundCorner, imageBitmapResult, null);
    }

    public static void loadImage(final Context context, final String imageId, String imageURL, final String size, final int roundCorner, final ImageView contactImg) {
        loadImage(context, imageId, imageURL, size, roundCorner, null, contactImg);
    }

    public static void loadImage(final Context context, final String imageId, String imageURL, final ImageBitmapResult imageBitmapResult) {
        loadImage(context, imageId, imageURL, "0x0", 0, imageBitmapResult, null);
    }

    public static void loadImage(final Context context, final String imageId, String imageURL, final ImageView contactImg) {
        loadImage(context, imageId, imageURL, "0x0", 0, null, contactImg);
    }

    public static void loadImage(final Context context, final String imageId, String imageURL) {
        loadImage(context, imageId, imageURL, "0x0", 0, null, null);
    }

    private static void loadImage(final Context context, final String imageId, String imageURL, final String size, final int roundCorner, final ImageBitmapResult imageBitmapResult, final ImageView contactImg) {
        try {

            final int defaultImage = android.R.drawable.btn_default;

            if (imageURL != null) {
                File file = getLocalImage(imageId, size, roundCorner);

                if (file == null || !file.exists()) {
                    if (imageURL != null && imageURL.startsWith("http")) {
                        String image = imageURL;
                        if (Integer.parseInt(size.split("x")[0]) != 0) {
                            image = thumbor.buildImage(imageURL)
                                    .resize(Integer.parseInt(size.split("x")[0]), Integer.parseInt(size.split("x")[1]))
                                    .filter(thumbor.buildImage(imageURL).roundCorner(roundCorner),
                                            thumbor.buildImage(imageURL).format(ThumborUrlBuilder.ImageFormat.JPEG))
                                    .toUrl();
                        }

                        Ion.with(context)
                                .load(image)
                                .withBitmap()
                                .animateGif(AnimateGifMode.ANIMATE)
                                .placeholder(defaultImage)
                                .error(defaultImage)
                                .asBitmap()
                                .setCallback(new com.koushikdutta.async.future.FutureCallback<Bitmap>() {
                                    @Override
                                    public void onCompleted(Exception e, Bitmap result) {
                                        if (e == null) {
                                            // Success
                                            if (imageBitmapResult != null) {
                                                imageBitmapResult.bitmapResult(result);
                                            }

                                            if (contactImg != null) {
                                                contactImg.setImageBitmap(result);
                                            }

                                            try {
                                                saveImage(result, imageId, size, roundCorner);
                                            } catch (Exception e1) {
                                                e1.printStackTrace();
                                            }
                                        } else {
                                            // Error
                                            if (contactImg != null) {
                                                contactImg.setImageResource(defaultImage);
                                            }

                                            if (imageBitmapResult != null) {
                                                imageBitmapResult.bitmapResult(BitmapFactory.decodeResource(context.getResources(), defaultImage));
                                            }
                                        }
                                    }
                                });
                    } else {
                        Ion.with(context)
                                .load(imageURL)
                                .withBitmap()
                                .animateGif(AnimateGifMode.ANIMATE)
                                .placeholder(defaultImage)
                                .error(defaultImage)
                                .asBitmap()
                                .setCallback(new com.koushikdutta.async.future.FutureCallback<Bitmap>() {
                                    @Override
                                    public void onCompleted(Exception e, Bitmap result) {
                                        if (e == null) {
                                            // Success
                                            if (contactImg != null) {
                                                contactImg.setImageBitmap(result);
                                            }

                                            if (imageBitmapResult != null) {
                                                imageBitmapResult.bitmapResult(result);
                                            }
                                        } else {
                                            // Error
                                            if (contactImg != null) {
                                                contactImg.setImageResource(defaultImage);
                                            }

                                            if (imageBitmapResult != null) {
                                                imageBitmapResult.bitmapResult(BitmapFactory.decodeResource(context.getResources(), defaultImage));
                                            }
                                        }
                                    }
                                });
                    }
                } else {
                    Ion.with(context)
                            .load(file)
                            .withBitmap()
                            .animateGif(AnimateGifMode.ANIMATE)
                            .placeholder(defaultImage)
                            .error(defaultImage)
                            .asBitmap()
                            .setCallback(new com.koushikdutta.async.future.FutureCallback<Bitmap>() {
                                @Override
                                public void onCompleted(Exception e, Bitmap result) {
                                    if (e == null) {
                                        // Success
                                        if (contactImg != null) {
                                            contactImg.setImageBitmap(result);
                                        }

                                        if (imageBitmapResult != null) {
                                            imageBitmapResult.bitmapResult(result);
                                        }
                                    } else {
                                        // Error
                                        if (contactImg != null) {
                                            contactImg.setImageResource(defaultImage);
                                        }

                                        if (imageBitmapResult != null) {
                                            imageBitmapResult.bitmapResult(BitmapFactory.decodeResource(context.getResources(), defaultImage));
                                        }
                                    }
                                }
                            });
                }
            } else {
                if (contactImg != null) {
                    contactImg.setImageResource(defaultImage);
                }

                if (imageBitmapResult != null) {
                    imageBitmapResult.bitmapResult(BitmapFactory.decodeResource(context.getResources(), defaultImage));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
