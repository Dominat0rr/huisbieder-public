package com.domain.fednot_demo_huisbieder.utils;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class CloudinaryAPI {
    private static final String CLOUDINARY_URL= "cloudinary://{api_key}:{api_secret}@{cloud_name}";

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dix4hvvnx",
            "api_key", "916956581987525",
            "api_secret", "tauIjum68mh5BrhyKltpO7JLCNE"));
    Api api = cloudinary.api();

    public void uploadFile(File file) throws IOException {
        String[] imageName = file.getName().split("\\.");
        String tag_id = "";

        if (imageName.length >= 1) {
            tag_id = imageName[0];
        }

        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap("tags", tag_id));
    }

    public void testUpload() throws IOException {
        File toUpload = new File("D:\\Github\\fednot_demo_huisbieder\\src\\main\\resources\\static\\images\\1_1.jpg");
        //Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.asMap( "tags", "1_1"));
        String[] imageName = toUpload.getName().split("\\.");
        String tag_id = imageName[0];
        System.out.println("TAG_ID: " + tag_id);
    }

    public void getImageByTag(String tag) throws Exception {
        JSONObject outerObject = null;
        String jsonNext = null;
        boolean ifWeHaveMoreResources = true;

        ArrayList<String> listRes = new ArrayList<String>();

        while (ifWeHaveMoreResources) {

            outerObject =new JSONObject(api.resources(ObjectUtils.asMap("max_results",500,"next_cursor",jsonNext)));

            if (outerObject.has("next_cursor")) {
                jsonNext = outerObject.get("next_cursor").toString();
                ifWeHaveMoreResources = true;
            }

            else {
                ifWeHaveMoreResources = false;
            }

            JSONArray jsonArray = outerObject.getJSONArray("resources");

            for (int i = 0, size = jsonArray.length(); i < size; i++) {
                JSONObject objectInArray = jsonArray.getJSONObject(i);
                String public_id = objectInArray.get("public_id").toString();

                //Add a if condition here if(public_id.toLowerCase().contains("Folder1".toLowerCase()) then
                String url = objectInArray.get("secure_url").toString();

                //Adding resource URL's to ArrayList
                if (objectInArray.get("tags").equals(tag)) listRes.add(url);

            }
        }
        //System.out.println("LIST: " + listRes.toString());
    }
}
