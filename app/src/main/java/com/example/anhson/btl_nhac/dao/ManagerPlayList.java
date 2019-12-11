package com.example.anhson.btl_nhac.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.anhson.btl_nhac.Cost;
import com.example.anhson.btl_nhac.model.AudioPlayer;
import com.example.anhson.btl_nhac.model.ManagerMedia;
import com.example.anhson.btl_nhac.model.Playlist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Son on 4/13/2017.
 */

public class ManagerPlayList {
    private static final String TAG =ManagerPlayList.class.getSimpleName();

    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    private ManagerMedia managerMedia;

    public ManagerPlayList(Context context) {
        //la co so du lieu cua playlist
        mContext=context;
        copyDataBase();
    }
    private void copyDataBase() {
        managerMedia=new ManagerMedia(mContext);
        try {
            new File(Cost.PATH_DATA).mkdir();
            File fileName=new File(Cost.PATH_DATA+File.separator+Cost.DATABASE);
            if (fileName.exists()){
                return;
            }
            fileName.createNewFile();
            AssetManager assetManager=mContext.getAssets();
            InputStream in=assetManager.open("qlPlaylist");
            OutputStream out=new FileOutputStream(fileName);
            byte b[]=new byte[1024];
            int le=in.read(b);
            while (le>0){
                out.write(b);
                le=in.read(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //mo 1 data base
    public void openDataBase(){
        if (mSqLiteDatabase==null||mSqLiteDatabase.isOpen()==false){
            mSqLiteDatabase=SQLiteDatabase.openDatabase(Cost.PATH_DATA+File.separator+Cost.DATABASE,null,SQLiteDatabase.OPEN_READWRITE);
        }
    }
    //dong 1 dataBase
    public void closeDataBase(){
        if (mSqLiteDatabase!=null){
            mSqLiteDatabase.close();
            mSqLiteDatabase=null;
        }
    }

    public List<AudioPlayer> getListSings(Playlist playlist) {
        List <AudioPlayer> audioPlayers=new ArrayList<>();

        openDataBase();
        String sql="SELECT * FROM tblSing WHERE idList="+playlist.getId();
        Cursor c=mSqLiteDatabase.rawQuery(sql,null);
        c.moveToFirst();

        while (c.isAfterLast()==false){
            String s=new String(c.getString(2));
            AudioPlayer audioPlayer=managerMedia.getAudioPlayser(s);
            if (audioPlayer!=null){
                audioPlayers.add(audioPlayer);
            }
            c.moveToNext();
        }
        return audioPlayers;
    }

    public Playlist getListYeuThich(){
        openDataBase();
        Cursor c=mSqLiteDatabase.query("tblPlaylist",null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Log.d(TAG,"getListYeuThich");
            Playlist playlist=new Playlist(c.getInt(0),c.getString(1));
            if (playlist.getId()==1){
                //playlist co id==0 la list yeu thich
                playlist.setAudioPlayers(getListSings(playlist));
                return playlist;
            }
            c.moveToNext();
        }
        return null;
    }
    public void deleteSingOfPlayList(AudioPlayer audioPlayer,Playlist playlist){
        openDataBase();
        mSqLiteDatabase.delete("tblSing","idList=? AND nameSing=?",new String[]{playlist.getId()+"",audioPlayer.getName()});

    }

    public List<Playlist> getPlaylists() {
        openDataBase();
        List <Playlist> playlists=new ArrayList<>();
        Cursor c=mSqLiteDatabase.query("tblPlaylist",null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Playlist playlist=new Playlist(c.getInt(0),c.getString(1));
            Log.d(TAG,"getPlaylists:"+playlist.getId());
            if (playlist.getId()==1){
                //playlist co id==0 la list yeu thich
                c.moveToNext();
            }else {
                playlist.setAudioPlayers(getListSings(playlist));
                playlists.add(playlist);
                c.moveToNext();
            }
        }
        return playlists;
    }

    public void addPlaylist(Playlist playlist) {
        openDataBase();
        //du lieu chua co . them moi
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",playlist.getName());

        String msg="";
        if (mSqLiteDatabase.insert("tblplaylist",null,contentValues)==-1){
            msg="ko thanh cong";
        }else{
            msg="them thanh cong";
        }
        Toast.makeText(mContext,msg+"",Toast.LENGTH_LONG).show();
    }
    public boolean checkYeuThich(AudioPlayer audioPlayer){
        for (AudioPlayer audio:getListYeuThich().getAudioPlayers()){
            if (audio.getName().equals(audioPlayer.getName())){
                return true;
            }
        }
        return false;
    }
    public void addAudioOnPlaylist(Playlist playlist,AudioPlayer audioPlayer){
        openDataBase();
        //du lieu chua co . them moi
        ContentValues contentValues=new ContentValues();
        contentValues.put("idList",playlist.getId());
        contentValues.put("nameSing",audioPlayer.getName());

        String msg="";
        if (mSqLiteDatabase.insert("tblSing",null,contentValues)==-1){
            msg="ko thanh cong";
        }else{
            msg="them thanh cong";
        }
        Toast.makeText(mContext,msg+"",Toast.LENGTH_LONG).show();
    }

    public void deletePlaylist(Playlist playlist) {
        openDataBase();
        mSqLiteDatabase.delete("tblPlaylist","id=? ",new String[]{playlist.getId()+""});

    }


    //them 1 doi tuong vao csdl
//    public void updateSanPham(Product product){
//        openDataBase();
//        if (checkSanPham(product.getBarcode())!=null){
//            //update du lieu moi
//            updateSP(product);
//            return;
//        }
//        //du lieu chua co . them moi
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("barCode",product.getBarcode());
//        contentValues.put("name",product.getTen());
//        contentValues.put("giaTien",product.getGiaTien());
//        contentValues.put("loai",product.getLoai());
//        contentValues.put("giaTien2",product.getGiaTien2());
//        contentValues.put("loai2",product.getLoai2());
//        contentValues.put("chuThich",product.getChuThich());
//
//        String msg="";
//        if (mSqLiteDatabase.insert("tblSanPham",null,contentValues)==-1){
//            msg="ko thanh cong";
//        }else{
//            msg="them thanh cong";
//        }
//        Toast.makeText(mContext,msg+"",Toast.LENGTH_LONG).show();
//    }

//    private void updateSP(Product sanPham) {
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("name",sanPham.getTen());
//        contentValues.put("giaTien",sanPham.getGiaTien());
//        contentValues.put("loai",sanPham.getLoai());
//        contentValues.put("giaTien2",sanPham.getGiaTien2());
//        contentValues.put("loai2",sanPham.getLoai2());
//        contentValues.put("chuThich",sanPham.getChuThich());
//        int ret=mSqLiteDatabase.update("tblSanPham",contentValues,"barCode=?",new String[]{sanPham.getBarcode()});
//        String msg="";
//        if (ret==0){
//            msg="ko thanh cong";
//        }else{
//            msg="them thanh cong";
//        }
//        Toast.makeText(mContext,msg+"",Toast.LENGTH_LONG).show();
//    }

//    public Product checkSanPham(String barCode){
//        openDataBase();
//        String sql="SELECT * FROM tblSanPham WHERE barCode="+barCode;
//        Cursor c=mSqLiteDatabase.rawQuery(sql,null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//            Product sanPham=new Product(c.getString(0),c.getString(1),c.getInt(2),c.getString(3),c.getInt(4),c.getString(5),c.getString(6));
//            Log.d(TAG,"barcode="+sanPham.getBarcode());
//            Log.d(TAG,"name="+sanPham.getTen());
//            Log.d(TAG,"name="+sanPham.getGiaTien());
//            Log.d(TAG,"name="+sanPham.getLoai());
//
//            Log.d(TAG,"--------------------");
//            return sanPham;
//        }
//        return null;
//    }

//    public List<Product> getDSSanPham() {
//        openDataBase();
//        List <Product> sanPhams=new ArrayList<>();
//        Cursor c=mSqLiteDatabase.query("tblSanPham",null,null,null,null,null,null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//            Product sanPham=new Product(c.getString(0),c.getString(1),c.getInt(2),c.getString(3),c.getInt(4),c.getString(5),c.getString(6));
//            sanPhams.add(sanPham);
//            Log.d(TAG,"barcode="+sanPham.getBarcode());
//            Log.d(TAG,"name="+sanPham.getTen());
//            Log.d(TAG,"--------------------");
//            c.moveToNext();
//        }
//        return sanPhams;
//    }

//    public List<Product> searchProduct(String name) {
//        openDataBase();
//        List<Product> products = new ArrayList<>();
//        String sql = "SELECT * FROM tblSanPham WHERE name='" + name + "'";
//        Cursor c = mSqLiteDatabase.rawQuery(sql, null);
//        c.moveToFirst();
//        while (c.isAfterLast() == false) {
//            Product sanPham = new Product(c.getString(0), c.getString(1), c.getInt(2), c.getString(3), c.getInt(4), c.getString(5), c.getString(6));
//            Log.d(TAG, "barcode=" + sanPham.getBarcode());
//            Log.d(TAG, "name=" + sanPham.getTen());
//            Log.d(TAG, "--------------------");
//            products.add(sanPham);
//            c.moveToNext();
//        }
//        return products;
//    }

}
