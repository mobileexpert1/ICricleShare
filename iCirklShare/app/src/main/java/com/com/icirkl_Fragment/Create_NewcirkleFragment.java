package com.com.icirkl_Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.icirklshare.R;
import com.icrklshare_Helper.custom.EditText_Roboto_Regular;
import com.icrklshare_Helper.custom.ExifUtils;
import com.icrklshare_Helper.custom.InternalStorageContentProvider;
import com.icrklshare_Helper.custom.RoundedImageView;
import com.larswerkman.holocolorpicker.ColorPicker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Typeface;

public class Create_NewcirkleFragment extends Fragment implements ColorPicker.OnColorChangedListener {

    static Activity activity;
    static View view;
    private ImageButton bt_gallery, bt_camera, bt_color;
    private RoundedImageView img_selector;
    private EditText_Roboto_Regular et_cirkl_name;

    private File mFileTemp;
    boolean flag = false;
    public final int REQUEST_CODE_TAKE_PICTURE = 0;
    public final int REQUEST_CODE_GALLERY = 1;
    public final int GALLERY_KITKAT_INTENT_CALLED = 2;
    public String capturepath = "";
    private RadioGroup radioGroup;
    private ImageView btn_next;
    private File main_dir;
    String IMAGE_DIRECTORY_NAME = "iCirklShare";
    private ExifUtils esitutils;
    private ColorPicker picker;
    private RelativeLayout img2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();

        view = inflater.inflate(R.layout.fragment_createnew_cirkle, container, false);

        getUI();
        esitutils = new ExifUtils();


        File direct = new File(Environment.getExternalStorageDirectory() + "/iCirklShare");

        if (!direct.exists()) {
            direct.mkdir();
        }

        String state = Environment.getExternalStorageState();
        long time = System.currentTimeMillis();
        refreshGallery();
        if (Environment.MEDIA_MOUNTED.equals(state)) {

            mFileTemp = new File(Environment.getExternalStorageDirectory() + "/iCirklShare",
                    +time + ".jpg");


            refreshGallery();
        } else {
            mFileTemp = new File(activity.getFilesDir() + "/iCirklShare",
                    +time + ".jpg");
            refreshGallery();
        }


        main_dir = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_DIRECTORY_NAME);
        if (!main_dir.exists()) {
            main_dir.mkdir();

            refreshGallery();
        }


        onClick();

        return view;
    }


    @Override
    public void onColorChanged(int color) {

        Log.e("color", color + "");
        img_selector.setVisibility(View.GONE);


        Drawable drawable = activity.getResources().getDrawable(R.drawable.roundlayout);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        img2.setVisibility(View.VISIBLE);
        img2.setBackgroundDrawable(drawable);

    }


    private void getUI() {

        img_selector = (RoundedImageView) view.findViewById(R.id.img_selector);
        et_cirkl_name = (EditText_Roboto_Regular) view.findViewById(R.id.et_cirkl_name);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        btn_next = (ImageView) view.findViewById(R.id.bt_next);

        img2=(RelativeLayout)view.findViewById(R.id.img2);
    }

    private void onClick() {

        img_selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_dialog_simple(activity);

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.rb_public) {
                    Toast.makeText(activity, "Public", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Private", Toast.LENGTH_SHORT).show();
                }
            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                set_dialog_simple(activity);



            }
        });

    }

    public void set_dialog_simple(final Activity activity) {
        View alet_view = null;

        final Dialog alertDialogBuilder = new Dialog(activity);
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alertDialogBuilder.getWindow().setBackgroundDrawableResource(R.drawable.img);

        alet_view = mInflater.inflate(R.layout.custom_popup, null);

        bt_gallery = (ImageButton) alet_view.findViewById(R.id.bt_gallery);
        bt_camera = (ImageButton) alet_view.findViewById(R.id.bt_camera);
        bt_color = (ImageButton) alet_view.findViewById(R.id.bt_color);


        alertDialogBuilder.setContentView(alet_view);

        alertDialogBuilder.getWindow().setLayout(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        alertDialogBuilder.setCanceledOnTouchOutside(true);
        alertDialogBuilder.show();

        bt_gallery.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                open_gallary();


                alertDialogBuilder.dismiss();
            }
        });

        bt_camera.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                takePicture();


                alertDialogBuilder.dismiss();
            }
        });
        bt_color.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                set_dialog_colorPiker(activity);
                


                alertDialogBuilder.dismiss();
            }
        });



    }

    public void set_dialog_colorPiker(Activity activity) {
        View alet_view = null;

        final Dialog alertDialogBuilder = new Dialog(activity);
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alertDialogBuilder.getWindow().setBackgroundDrawableResource(R.drawable.img);

        alet_view = mInflater.inflate(R.layout.custom_popup_color_picker, null);


        picker = (ColorPicker) alet_view.findViewById(R.id.picker);
        picker.setOldCenterColor(picker.getColor());
        picker.setOnColorChangedListener(this);
        picker.setShowOldCenterColor(false);
        alertDialogBuilder.setContentView(alet_view);

        alertDialogBuilder.getWindow().setLayout(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        alertDialogBuilder.setCanceledOnTouchOutside(true);
        alertDialogBuilder.show();


    }


    private void takePicture() {


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            Uri mImageCaptureUri = null;


            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);


            } else {
                mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;


            }

            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                    mImageCaptureUri);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);


        } catch (ActivityNotFoundException e) {

        }


    }

    public void open_gallary() {

        if (Build.VERSION.SDK_INT <= 19) {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_GALLERY);
        } else

        {


            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED);


        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode != activity.RESULT_OK) {

            return;
        }


        switch (requestCode) {

            case REQUEST_CODE_GALLERY:

                try {


                    img_selector.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    InputStream inputStream = activity.getContentResolver()
                            .openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    //       decodeFile(mFileTemp).compress(Bitmap.CompressFormat.PNG, 10, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();
                    capturepath = mFileTemp.getAbsolutePath();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bitmap = BitmapFactory.decodeFile(capturepath, options);

                    Bitmap bm = esitutils.rotateBitmap(capturepath, bitmap);
                 /*   Bitmap bm = esitutils.rotateBitmap(picturePath, bitmap);
                    Bitmap resizedBitmap = scaleWithAspectRatio(bm);*/
                    // img_tag.setImageBitmap(resizedBitmap);

                    // img_tag.setImageBitmap(getResizedBitmap(bm, 1100));



                    img_selector.setImageBitmap(bm);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case REQUEST_CODE_TAKE_PICTURE:

                try {

                    capturepath = mFileTemp.getAbsolutePath();

                    img_selector.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bitmap = BitmapFactory.decodeFile(capturepath, options);
                    Bitmap bm = esitutils.rotateBitmap(capturepath, bitmap);
                 /*   Bitmap bm = esitutils.rotateBitmap(picturePath, bitmap);
                    Bitmap resizedBitmap = scaleWithAspectRatio(bm);*/
                    // img_tag.setImageBitmap(resizedBitmap);

                   // img_tag.setImageBitmap(getResizedBitmap(bm, 1100));



                    img_selector.setImageBitmap(bm);





                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case GALLERY_KITKAT_INTENT_CALLED:

                try {

                    img_selector.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    InputStream inputStream = activity.getContentResolver()
                            .openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    //    decodeFile(mFileTemp).compress(Bitmap.CompressFormat.PNG, 10, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();
                    final int takeFlags = data.getFlags()
                            & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    activity.getContentResolver().takePersistableUriPermission(data.getData(), takeFlags);
                    capturepath = mFileTemp.getAbsolutePath();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bitmap = BitmapFactory.decodeFile(capturepath, options);
                    Bitmap bm = esitutils.rotateBitmap(capturepath, bitmap);
                 /*   Bitmap bm = esitutils.rotateBitmap(picturePath, bitmap);
                    Bitmap resizedBitmap = scaleWithAspectRatio(bm);*/
                    // img_tag.setImageBitmap(resizedBitmap);

                    // img_tag.setImageBitmap(getResizedBitmap(bm, 1100));

                    img_selector.setImageBitmap(bm);


                } catch (Exception e) {

                }
                break;


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }
    public void refreshGallery() {

        //      sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse(capturepath+ Environment.getExternalStorageDirectory())));


        MediaScannerConnection.scanFile(activity, new String[]{

                        capturepath},

                null, new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri)

                    {


                    }

                });

    }
}
