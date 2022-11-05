package com.elitechinc.imageclusstering.Classes;

public class Cluster {
    String cluster,imgurl;


    public Cluster(String cluster, String imgurl) {
        this.cluster = cluster;
        this.imgurl = imgurl;
    }

    public Cluster() {
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
