package com.example.finalproject;



import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

public class AllMarks {

    /// collage,cafeteria;

    private MapView map;
    private ArrayList<Marker> markers ;

    private String MarkerName;
    private String type;
    boolean isVisible ;

    AllMarks(MapView map,String type){
        this.type = type;
        this.map = map;
        this.isVisible = true;
        markers = new ArrayList<>();
//        for(AllMarks mark : AllMarks.values()){
//            mark.setMap(map);
//            for(int i=0;i<10;i++){
//                mark.newMarker(31+i,35+i,mark.name()+i);
//                map.getOverlays().add(mark.markers.get(i));
//            }
//        }

    }

    public ArrayList<Marker> getMarkers(){
        return markers;
    }

    public String getType() {
        return type;
    }


    Marker addMarker(double lat,double lon,String name,String type){
        if(map==null)
            return null;
        Marker temp = new Marker(map);
        temp.setPosition(new GeoPoint(lat,lon));
        temp.setTitle(name);
        MarkerName = name;
        temp.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        markers.add(temp);
        map.getOverlays().add(temp);
        // setMakerIcon(temp);
        return temp;
    }
    public String toString(){
        return type;
    }
    public String getMarkerName(){
        return MarkerName;
    }

//    private void setMakerIcon(Marker temp) {
//        Drawable col = fixDrawableSize(R.drawable.col);
//        Drawable caf = fixDrawableSize(R.drawable.caf);
//        Drawable lib = fixDrawableSize(R.drawable.lib);
//        Drawable park = fixDrawableSize(R.drawable.park);
//        Drawable atm = fixDrawableSize(R.drawable.atm);
//        Drawable bzu = fixDrawableSize(R.drawable.bzu);
//        Drawable redgps = fixDrawableSize(R.drawable.redgps);
//
//        //depending on type set icon
//
//        switch (type){
//            case "collage":
//                temp.setIcon(col);
//                temp.setImage(col);
//                break;
//            case "cafeteria":
//                temp.setIcon(caf);
//                break;
//            case "library":
//                temp.setIcon(lib);
//                break;
//            case "parking":
//                temp.setIcon(park);
//                break;
//            case "atm":
//                temp.setIcon(atm);
//                break;
//            case "bzu":
//                temp.setIcon(bzu);
//                break;
//            default:
//                temp.setIcon(redgps);
//                break;
//        }
//    }

    Drawable fixDrawableSize(int pic) {
        Drawable dr = map.getContext().getResources().getDrawable(pic);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

        return new BitmapDrawable( map.getContext().getResources() ,
                Bitmap.createScaledBitmap(bitmap, 100, 100, true));
    }

    public void toggleMarker(ArrayList<Marker> markers) {

        for (Marker marker : markers) {
            if (this.isVisible) {
                marker.setVisible(false);
            }
            else {
                marker.setVisible(true);
            }
        }
        if (this.isVisible)
            this.isVisible = false;
        else
            this.isVisible = true;

    }

}
