package com.wxb.wanshu.utils;

import android.accounts.Account;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;

/**
 * Created by qiming on 2017/11/22.
 */

public class ImageUtils {

    private static Context mContext;

    private static ImageLoader imageLoader;

    static {
        mContext = ReaderApplication.getsInstance();
        initImageLoader();
    }


    public static void initImageLoader() {
        imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration imageLoaderConfig = new ImageLoaderConfiguration
                .Builder(mContext)
                .imageDownloader(new BaseImageDownloader(mContext))
                .memoryCache(new LRULimitedMemoryCache(2 * 1024 * 1024))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();
        imageLoader.init(imageLoaderConfig);
    }

    public static void showImage(Context context, ImageView imageView, String imgUrl) {
//        Glide.with(context).load(imgUrl).into(imageView);
    }

    //显示圆图
    public static void showCircleImage(Context context, String url, ImageView imageView) {
        if (context != null) {
            RequestOptions placeholder = new RequestOptions().placeholder(R.mipmap.ic_launcher).transform(new CircleCrop());
            Glide.with(ReaderApplication.getsInstance()).load(url).apply(placeholder).into(imageView);
        }
    }

    //显示圆角图
    public static void showRoundImage(Context context, String url, ImageView imageView, int radius) {
        RequestOptions placeholder = new RequestOptions().placeholder(R.mipmap.ic_launcher).transform(new RoundedCorners(radius));
        Glide.with(context).load(url).apply(placeholder).into(imageView);
    }


    /**
     * 显示一张图片
     *
     * @param imageView
     * @param url
     */
    public static void displayImage(Context context,ImageView imageView, String url, int imageOnLoadingRes, int imageOnFailRes) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(imageOnLoadingRes)
                .showImageForEmptyUri(imageOnFailRes)
                .showImageOnLoading(imageOnFailRes)
                .cacheInMemory(true)//内存缓存开启可能会引起图片decode失败，初步怀疑释放有问题
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new SimpleBitmapDisplayer())//是否图片加载好后渐入的动画时间
                .build();
        imageLoader.displayImage(url, imageView, options);
    }

    /**
     * 显示一张图片
     *
     * @param imageView
     * @param url
     */
    public static void displayImage(Context context,ImageView imageView, String url) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//内存缓存开启可能会引起图片decode失败，初步怀疑释放有问题
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new SimpleBitmapDisplayer())//是否图片加载好后渐入的动画时间
                .build();
        imageLoader.displayImage(url, imageView, options);
    }

    /**
     * 显示圆形图片
     * @param imageView
     * @param url
     */
    public static void displayCircleImage(Context context,ImageView imageView, String url) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(imageOnLoadingRes) //设置图片在下载期间显示的图片
//                .showImageForEmptyUri(imageOnFailRes)//设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(imageOnFailRes)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .displayer(new RoundedBitmapDisplayer(360))//是否设置为圆角，弧度为多少
//      .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

        imageLoader.displayImage(url, imageView, options);
    }

    /**
     * 放大显示一张图片
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void displayImageBiger(Context context, ImageView imageView, String url, int imageOnLoadingRes, int imageOnFailRes) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imageOnLoadingRes)
                .showImageForEmptyUri(imageOnFailRes)
                .showImageOnFail(imageOnFailRes)
                .cacheInMemory(true)//内存缓存开启可能会引起图片decode失败，初步怀疑释放有问题
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .postProcessor(new BitmapProcessor() {
                    @Override
                    public Bitmap process(Bitmap bitmap) {
                        Matrix matrix = new Matrix();
                        matrix.postScale(1.5f, 1.5f); //长和宽放大缩小的比例
                        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        return resizeBmp;
                    }
                })
                .displayer(new FadeInBitmapDisplayer(500))//是否图片加载好后渐入的动画时间
                .build();
        imageLoader.displayImage(url, imageView, options);
    }

    /**
     * @param url
     */
    public static Bitmap getImageBitmap(String url) {
        return imageLoader.getInstance().loadImageSync(url);
    }

    /**
     * 显示一张圆角图片
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void displayRoundImage(Context context, ImageView imageView, String url, int imageOnLoadingRes, int imageOnFailRes, int roundPx) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imageOnLoadingRes)
                .showImageForEmptyUri(imageOnFailRes)
                .showImageOnFail(imageOnFailRes)
                .cacheInMemory(true)//内存缓存开启可能会引起图片decode失败，初步怀疑释放有问题
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new FadeInBitmapDisplayer(500))//是否图片加载好后渐入的动画时间
                .displayer(new RoundedBitmapDisplayer(roundPx))
                .build();
        imageLoader.displayImage(url, imageView, options);
    }
}
